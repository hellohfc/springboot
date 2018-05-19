package com.hfc.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author fuchun.hu@hand-china.com(胡馥春)
 * @version 1.0
 * @name WebSocketHandshakeInterceptor
 * @description
 * @date 2018/5/15
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {


    /**
     * 在执行客户端服务器握手之前，在该方法中，将HttpSession中登录后存储的对象
     * 放到webSocketSession中，以此实现定向发送消息
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param map
     * @return
     * @throws Exception
     */
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if(serverHttpRequest instanceof ServletServerHttpRequest){
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest)serverHttpRequest;
            HttpSession session = servletServerHttpRequest.getServletRequest().getSession(false);
            if(session!=null){
                String userName = (String)session.getAttribute("SESSION_USERNAME");
                if(userName==null){
                    userName = "System-"+session.getId();
                }

                //区分socket连接以定向发送信息
                map.put("WEBSOCKET_USERNAME",userName);


            }
        }

        return true;
    }


    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("after handshake");
    }
}

