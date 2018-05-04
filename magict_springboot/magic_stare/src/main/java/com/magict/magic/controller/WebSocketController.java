package com.magict.magic.controller;


import com.magict.magic.component.GetHttpSessionConfigurator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZP
 * @date 2018/4/2215:21
 * @description:
 */
@ServerEndpoint(value = "/online",configurator = GetHttpSessionConfigurator.class)
@Component
public class WebSocketController extends BaseController{
    private static final Map<String, WebSocketController> connection = new HashMap<String, WebSocketController>();
    private String userIP;
    private Session session;

    @OnOpen
    public void start(Session session, EndpointConfig config) {
        //获取HttpSession对象
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        this.session = session;
        this.userIP = (String) httpSession.getAttribute("ip");
        /*判断链接是否已经在链接队列中，存在就不加*/
        if(!connection.containsKey(userIP)){
            connection.put(userIP, this);
            String message = "* 你已经链接";
            sendCommonRecord(message);
        }
    }

    @OnClose
    public void end() {
        connection.remove(this.userIP);
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        logger.error("Chat Error: " + t.toString(), t);
    }

    @OnMessage
    public void incoming(String message){
        sendCommonRecord(message);
    }



    //给聊天的双方发共同消息
    public void sendCommonRecord(String msg) {
        logger.debug("收发消息：【"+msg+"】");
        connection.forEach((ip,client)->{
            synchronized (client){
                try {
                    client.session.getBasicRemote().sendText(userIP+":"+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
