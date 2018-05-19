package com.hfc.config;


import com.hfc.handler.WebSocketHandler;
import com.hfc.interceptor.WebSocketHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;




/**
 * @author fuchun.hu@hand-china.com(胡馥春)
 * @version 1.0
 * @name WebSocketConfig
 * @description
 * @date 2018/5/15
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter  implements WebSocketConfigurer  {


    @Autowired
    private WebSocketHandler webSocketHandler;


    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //注册处理拦截器，拦截url为socketServer的请求
        webSocketHandlerRegistry.addHandler(webSocketHandler,"/socketServer").addInterceptors(new WebSocketHandshakeInterceptor());

        //注册SocketJs的处理拦截器，拦截url为/sockjs/socketServer的请求
        webSocketHandlerRegistry.addHandler(webSocketHandler,"/sockjs/socketServer").addInterceptors(new WebSocketHandshakeInterceptor()).withSockJS();
    }





}

