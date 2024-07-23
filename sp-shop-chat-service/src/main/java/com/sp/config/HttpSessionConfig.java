package com.sp.config;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * http会话配置类，用于配置和管理HttpSession与WebSocket的关联。
 *
 * @author 罗汉
 * @date 2023/07/28
 */
@Component
public class HttpSessionConfig extends ServerEndpointConfig.Configurator implements ServletRequestListener {

    /**
     * 当ServletRequest事件被触发时，初始化请求。
     * 主要用于在所有的request请求中强制携带HttpSession。
     *
     * @param sre ServletRequestEvent对象，封装了ServletRequest触发的事件信息。
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // 获取HttpSession，并确保所有的request请求都与HttpSession相关联
        HttpSession session = ((HttpServletRequest) sre.getServletRequest()).getSession();
    }

    /**
     * 修改WebSocket的握手请求，以实现与HttpSession的绑定。
     *
     * @param sec      ServerEndpointConfig对象，表示WebSocket的端点配置。
     * @param request  HandshakeRequest对象，封装了WebSocket握手请求的信息。
     * @param response HandshakeResponse对象，用于构建和设置WebSocket握手响应的信息。
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 尝试获取HttpSession，并将其与WebSocket连接关联
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        if (httpSession != null) {
            // 将HttpSession对象保存到serverEndpointConfig的用户属性中，以便在WebSocket会话中访问
            sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
        }
        super.modifyHandshake(sec, request, response);
    }
}
