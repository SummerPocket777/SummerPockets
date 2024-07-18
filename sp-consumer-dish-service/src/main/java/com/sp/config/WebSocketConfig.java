package com.sp.config;

import com.sp.service.CartService;
import com.sp.ws.WebSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.Resource;

/**
 * WebSocket配置
 *
 * @author 罗汉
 * @date 2023/07/28
 */
@Configuration
public class WebSocketConfig {
   /* @Resource
    private CartService cartService;*/
    /**
     * 服务器端点
     * 注入ServerEndpointExporter，
     * 这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     *
     * @return {@link ServerEndpointExporter}
     */
//    @Bean
//    public WebSocket webSocket() {
//        WebSocket webSocket = new WebSocket(cartService);
//        return webSocket;
//    }
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}