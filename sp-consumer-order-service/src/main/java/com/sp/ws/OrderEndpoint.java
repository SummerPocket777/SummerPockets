package com.sp.ws;

import com.alibaba.fastjson.JSON;

import com.sp.config.HttpSessionConfig;
import com.sp.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.sp.ws.pojo.Message;

@Slf4j
@Component
@ServerEndpoint(value = "/webOrderInfo/{shopId}", configurator = HttpSessionConfig.class)
public class OrderEndpoint {


    private static final Map<String,Session> onlineUsers = new ConcurrentHashMap<>();

    private Session session;
    private HttpSession httpSession;

    /**
     * 建立websocket连接后，被调用
     * @param session
     */
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("shopId") String user,
                       EndpointConfig config) {
        //1，将session进行保存
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        onlineUsers.put(user,session);
        //2，广播消息。需要将登陆的所有的用户推送给所有的用户
//        String message = MessageUtils.getMessage(true,null,getFriends());
//        broadcastAllUsers(message);
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

    /**
     * 浏览器发送消息到服务端，该方法被调用
     *
     * 张三  -->  李四
     * @param message
     */
    @OnMessage
    public void onMessage(String message,@PathParam("shopId") String user) {
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

    /**
     * 断开 websocket 连接时被调用
     *
     * @param user
     */
    @OnClose
    public void onClose(@PathParam("shopId") String user) {
        //1,从onlineUsers中剔除当前用户的session对象
        onlineUsers.remove(user);
        //2,通知其他所有的用户，当前用户下线了
//        String message = MessageUtils.getMessage(true,null,);
//        broadcastAllUsers(message);
    }
}
