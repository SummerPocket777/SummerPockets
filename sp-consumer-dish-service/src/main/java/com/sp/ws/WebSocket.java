package com.sp.ws;

import com.alibaba.fastjson2.JSONObject;
import com.sp.config.HttpSessionConfig;
import com.sp.model.vo.ShoppingCartVO;
import com.sp.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint(value = "/websocket/{businessId}/{tableId}/{userId}", configurator = HttpSessionConfig.class)
public class WebSocket {
    private  static CartService cartService;
    private static final Map<String, ConcurrentHashMap<String, WebSocket>> ROOMS = new ConcurrentHashMap<>();//房间
    private static final Map<String,Integer> onlineCount = new ConcurrentHashMap<>();//在线人数

    private Session session;
    private HttpSession httpSession;
    private String roomKey;
    private String userId;


    @Autowired
    @Lazy
    public void setCartService(CartService cartService) {
        WebSocket.cartService = cartService;
    }
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("businessId") String businessId,
                       @PathParam("tableId") String tableId,
                       @PathParam("userId") String userId,
                       EndpointConfig config) {
        this.session = session;
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.userId = userId;
        this.roomKey = businessId + "-" + tableId;

        ROOMS.computeIfAbsent(roomKey, k -> new ConcurrentHashMap<>()).put(userId, this);

        // 增加在线人数
        onlineCount.merge(roomKey, 1, Integer::sum);
        broadcastOnlineCount();

        // 加载 Redis 中的购物车数据并发送给新连接的用户
        this.sendMessage("initial_cart", JSONObject.toJSONString(cartService.showCart(Long.valueOf(tableId), Long.valueOf(businessId))));

        log.info("用户 {} 连接到房间 {}", userId, roomKey);
    }

    @OnClose
    public void onClose(
            @PathParam("businessId") String businessId,
            @PathParam("tableId") String tableId,
            @PathParam("userId") String userId) {
        String roomKey = businessId + "-" + tableId;
        if (ROOMS.containsKey(roomKey)) {
            ROOMS.get(roomKey).remove(userId);
            if (ROOMS.get(roomKey).isEmpty()) {
                ROOMS.remove(roomKey);
            }
        }

        // 减少在线人数
        onlineCount.merge(roomKey, -1, (oldValue, value) -> oldValue == 1 ? null : oldValue + value);
        broadcastOnlineCount();

        log.info("用户 {} 断开连接，房间 {}", userId, roomKey);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("businessId") String businessId, @PathParam("tableId") String tableId) {
        log.info("收到消息，房间: {}，消息: {}", roomKey, message);

        // 反序列化 JSON 字符串到 ShoppingCartVO 对象
        JSONObject jsonObject = JSONObject.parseObject(message);

        String action=jsonObject.getString("action");
        if (action.equals("ping")) {
            this.sendMessage("pong", null);
            return;
        }
        JSONObject dataObject = jsonObject.getJSONObject("data");

        //判断
        if (action.equals("add_cart")) {
            ShoppingCartVO shoppingCartVO = dataObject.toJavaObject(ShoppingCartVO.class);
            cartService.add(shoppingCartVO);
            this.sendMessage("add_cart", JSONObject.toJSONString(cartService.showCart(Long.valueOf(tableId), Long.valueOf(businessId))));
        } else if (action.equals("clean")) {
            cartService.cleanCart(Long.valueOf(tableId), Long.valueOf(businessId));
            this.sendMessage("clean_cart", "");
        } else if (action.equals("show")) {
            this.sendMessage("initial_cart", JSONObject.toJSONString(cartService.showCart(Long.valueOf(tableId), Long.valueOf(businessId))));
        } else if (action.equals("del_cart")) {
            ShoppingCartVO shoppingCartVO = dataObject.toJavaObject(ShoppingCartVO.class);
            cartService.del(shoppingCartVO);
            this.sendMessage("initial_cart", JSONObject.toJSONString(cartService.showCart(Long.valueOf(tableId), Long.valueOf(businessId))));
        }

        // 广播消息到该房间的所有用户 并且排除自己
//        broadcastToRoom(Long.valueOf(businessId), Long.valueOf(tableId), action, dataObject);
//        ROOMS.get(roomKey).values().forEach(webSocket -> {
//            try {
//                webSocket.session.getBasicRemote().sendText(message);
//            } catch (IOException e) {
//                log.error("发送消息失败", e);
//            }
//        });
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("WebSocket发生错误", error);
    }

    /**
     * 向房间广播
     *
     * @param businessId 业务标识
     * @param tableId    表id
     * @param action     行动
     * @param data       数据
     */
    public void broadcastToRoom(Long businessId, Long tableId, String action, Object data) {
        String roomKey = businessId + "-" + tableId;
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("action", action);
        objectObjectHashMap.put("data", data);
        String message = JSONObject.toJSONString(objectObjectHashMap);
        ROOMS.getOrDefault(roomKey, new ConcurrentHashMap<>()).values().forEach(webSocket -> {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("发送消息失败", e);
            }
        });
    }

    private void broadcastOnlineCount() {
        ROOMS.values().forEach(room -> room.values().forEach(webSocket -> {
            try {
                String roomKey = webSocket.roomKey;
                Integer count = onlineCount.getOrDefault(roomKey, 0);
                HashMap<String, Object> hashMap = new HashMap<String, Object>() {{
                    put("action", "online_count");
                    put("data", count);
                }};
                webSocket.session.getBasicRemote().sendText(JSONObject.toJSONString(hashMap));
            } catch (IOException e) {
                log.error("发送在线人数消息失败", e);
            }
        }));
    }
    private void sendMessage(String action, String data) {
        log.info("发送消息，房间: {}，发送类型,{},消息: {}", roomKey,action, data);
        try {
            HashMap<String, Object> hashMap = new HashMap<String, Object>() {{
                put("action", action);
                put("data", data);
            }};
            this.session.getBasicRemote().sendText(JSONObject.toJSONString(hashMap));
        } catch (IOException e) {
            log.error("发送消息失败", e);
        }
    }
}


class Message {
    private String action;
    private ShoppingCartVO data;
}
