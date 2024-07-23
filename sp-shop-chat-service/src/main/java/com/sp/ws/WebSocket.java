package com.sp.ws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.sp.config.HttpSessionConfig;

import com.sp.utils.MessageUtils;
import com.sp.ws.pojo.Message;
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
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint(value = "/chat/{userName}", configurator = HttpSessionConfig.class)
public class WebSocket {

    private static final Map<String,Session> onlineUsers = new ConcurrentHashMap<>();

    private Session session;
    private HttpSession httpSession;



//    @Autowired
//    @Lazy
//    public void setCartService(CartService cartService) {
//        WebSocket.cartService = cartService;
//    }
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("userName") String user,
                       EndpointConfig config) {

        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        String user2 = (String) this.httpSession.getAttribute("user");
        System.err.println("user2:"+user2);
        onlineUsers.put(user,session);
        //2，广播消息。需要将登陆的所有的用户推送给所有的用户
        String message = MessageUtils.getMessage(true,null,getFriends());
        broadcastAllUsers(message);


    }

    public Set getFriends() {
        Set<String> set = onlineUsers.keySet();
        return set;
    }

    private void broadcastAllUsers(String message) {
        try {
            //遍历map集合
            Set<Map.Entry<String, Session>> entries = onlineUsers.entrySet();
            for (Map.Entry<String, Session> entry : entries) {
                //获取到所有用户对应的session对象
                Session session = entry.getValue();
                //发送消息
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            //记录日志
        }
    }


    @OnClose
    public void onClose(@PathParam("userName") String user) {
        //1,从onlineUsers中剔除当前用户的session对象

        onlineUsers.remove(user);
        //2,通知其他所有的用户，当前用户下线了
        String message = MessageUtils.getMessage(true,null,getFriends());
        broadcastAllUsers(message);
    }

    @OnMessage
    public void onMessage(String message,@PathParam("userName") String user) {
        try {
            //将消息推送给指定的用户
            Message msg = JSON.parseObject(message, Message.class);
            //获取 消息接收方的用户名
            String toName = msg.getToName();
            String mess = msg.getMessage();
            //获取消息接收方用户对象的session对象
            Session session = onlineUsers.get(toName);

            String msg1 = MessageUtils.getMessage(false, user, mess);
            session.getBasicRemote().sendText(msg1);
        } catch (Exception e) {
            //记录日志
        }
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

    }


    private void sendMessage(String action, String data) {

    }
}



