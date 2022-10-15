package com.yang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
@CrossOrigin
@ServerEndpoint("/websocket/{username}") //暴露的ws应用的路径
public class WebSocketController {
    Session session;
    /**
     * 客户端与服务端连接成功
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("username") String username){
        this.session = session;
        log.info("连接成功 {},{}", username, session);
    }

    /**
     * 客户端与服务端连接关闭
     */
    @OnClose
    public void onClose(Session session,@PathParam("username") String username){
        log.info("关闭连接 {}", username);
    }

    /**
     * 客户端与服务端连接异常
     */
    @OnError
    public void onError(Throwable error,Session session,@PathParam("username") String username) {
        log.info("连接出错 {}", username);
    }

    /**
     * 客户端向服务端发送消息
     */
    @OnMessage
    public void onMsg(Session session,String message,@PathParam("username") String username) throws IOException {
        String str = "哈哈哈";
        this.session.getAsyncRemote().sendText(str);
        log.info("接收数据 {}, {}, {}", session, message, username);
    }
}

