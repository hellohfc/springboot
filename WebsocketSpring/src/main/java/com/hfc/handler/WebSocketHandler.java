package com.hfc.handler;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author fuchun.hu@hand-china.com(胡馥春)
 * @version 1.0
 * @name WebSocketHandler
 * @description
 * @date 2018/5/15
 */
@Service
public class WebSocketHandler extends TextWebSocketHandler {

    //建立连接用户
    private static final ArrayList<WebSocketSession> USERS = new ArrayList<WebSocketSession>();

    /**
     * 处理前端发送的文本信息
     * 调用websocket.send的时候会被调用
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");

        // 获取提交过来的消息详情
        System.out.println("收到用户"+username+"的消息："+message.toString());

        //回复一条信息，
        session.sendMessage(new TextMessage("reply msg: 我收到啦！" ));
    }

    /**
     * 当新连接建立的时候会被调用
     * 连接成功时，触发界面的onOpen方法
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        USERS.add(session);
        String username=(String)session.getAttributes().get("WEBSOCKET_USERNAME");
        System.out.println("用户"+username+"Connection Established");
        session.sendMessage(new TextMessage(username+"connect"));
        session.sendMessage(new TextMessage("hello welcome"));
    }

    /**
     * 当连接关闭时被调用
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws  Exception{
        String username = (String)session.getAttributes().get("WEBSOCKET_USERNAME");
        System.out.println("用户 "+username+"Connection close.Status:"+status);
        USERS.remove(session);
    }

    /**
     * 传输错误时调用
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws  Exception{
        String username = (String)session.getAttributes().get("WEBSOCKET_USERNAME");
        if(session.isOpen()){
            session.close();
        }

        System.out.println("用户:"+username+"websocket connection closed....");
        USERS.remove(session);
    }

    /**
     * 给所有在线用户发送消息
     * @param message
     */
    public void sendMessageToAllUser(TextMessage message){
        for(WebSocketSession user:USERS){
            try {
            if(user.isOpen()){
                    user.sendMessage(message);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给指定的用户发送消息
     * @param userName
     * @param message
     */
    public void sendMessageToSimpleUser(String userName,TextMessage message){
        for(WebSocketSession user:USERS){
            if(userName.equals(user.getAttributes().get("WEBSOCKET_USERNAME"))){
                try {
                if(user.isOpen()){
                    user.sendMessage(message);
                }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

