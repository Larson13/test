package com.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ChatWebsocketClient {
    //连接端口，http://localhost:8072/websocket
    String chatEndpoint;
    private StompSessionHandler chatStompSessionHandler;
    // 定义全局变量，代表一个session
    private static StompSession stompSession;
    private String userId;
    //订阅主题
    private String topic;

    public ChatWebsocketClient(String chatEndpoint, String userId, String topic) {
        this.chatEndpoint = chatEndpoint;
        this.userId = userId;
        this.topic = topic;
    }

    public ChatWebsocketClient() {

    }

    public StompSession getStompSession() {
        return stompSession;
    }

    /**
     *   建立连接
     */
    public void connect() {// 定义连接函数
        if (stompSession == null || !stompSession.isConnected()) {
            log.info("连接中...");
            List<Transport> transports = new ArrayList<>();
            transports.add(new WebSocketTransport(new StandardWebSocketClient()));
            SockJsClient sockJsClient = new SockJsClient(transports);
            WebSocketStompClient webSocketStompClient = new WebSocketStompClient(sockJsClient);
            webSocketStompClient.setMessageConverter(new StringMessageConverter());
            webSocketStompClient.setDefaultHeartbeat(new long[]{20000, 0});
            ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
            taskScheduler.afterPropertiesSet();
            webSocketStompClient.setTaskScheduler(taskScheduler);
            WebSocketHttpHeaders webSocketHttpHeaders = null;
            StompHeaders stompHeaders = new StompHeaders();
            stompHeaders.add("token", "token");
            stompHeaders.add("admin", "admin");
            chatStompSessionHandler = new ChatStompSessionHandler(topic);
            try {
                ListenableFuture<StompSession> future = webSocketStompClient.connect(chatEndpoint, webSocketHttpHeaders,
                        stompHeaders, chatStompSessionHandler);
                stompSession = future.get();
                stompSession.setAutoReceipt(true);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            log.info("连接成功");
            System.out.println("连接成功");
        } else {
            log.info("已连接状态");
        }
    }

    public void sendMessage(String topic, String msg) {

        try {


                stompSession.send(topic, new String(msg.getBytes("UTF-8"), "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            if (stompSession != null && stompSession.isConnected()) {
                stompSession.disconnect();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  接收订阅消息
     * @param topic  订阅主题
     * @param msg
     */
    public void subscribe(String topic) {

        if (chatStompSessionHandler == null) {
            chatStompSessionHandler = new ChatStompSessionHandler(topic);

        }

        try {
            stompSession.subscribe(topic, chatStompSessionHandler);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String chatEndpoint = "http://localhost:8072/websocket";
        String userId = "100";
        String topic = "/exchange/sendToUser/user";
        ChatWebsocketClient chatWebsocketClient = new ChatWebsocketClient(chatEndpoint, userId, topic);
        chatWebsocketClient.connect();
        String msg = "{\"fromUserId\":\"100\",\"toUserId\":\"2\",\"msg\":\"我很开\"}";
        String destination = "/exchange/sendToUser/user";
        chatWebsocketClient.sendMessage(destination, msg);
        chatWebsocketClient.subscribe(topic);
        //chatWebsocketClient.close();


    }

}
