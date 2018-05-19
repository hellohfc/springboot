package com.hfc.controller;

import com.hfc.handler.WebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author fuchun.hu@hand-china.com(胡馥春)
 * @version 1.0
 * @name PageController
 * @description
 * @date 2018/5/15
 */

@Controller
public class PageController {
    @Bean//这个注解会从Spring容器拿出Bean
    public WebSocketHandler infoHandler() {
        return new WebSocketHandler();
    }

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        System.out.println(username + "登录");
        HttpSession session = request.getSession();
        session.setAttribute("SESSION_USERNAME", username);
        response.sendRedirect("websocket.jsp");
    }

    @RequestMapping("/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String username = request.getParameter("username");
        infoHandler().sendMessageToSimpleUser(username, new TextMessage("你好，欢迎测试！！！！"));
        return null;
    }
}

