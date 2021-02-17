package com.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.DefaultStompSession;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class StompClient {
    private StompSession stompSession = null;
    private StompHeaders stompHeaders;
    private String userId;
    private String topic;
    String chatEndpoint;
    ChatStompSessionHandler customHandler;

    public StompClient(String chatEndpoint, String userId, String topic, StompHeaders stompHeaders) {
        this.userId = userId;
        this.topic = topic;
        this.chatEndpoint = chatEndpoint;
        this.stompHeaders = stompHeaders;
    }

    public StompClient() {

    }

    public StompSession getStompSession() {
        return stompSession;
    }

    public static void main(String[] args) throws Exception {



        StompHeaders stompHeaders = new StompHeaders();
        stompHeaders.add("username", "admin");
        stompHeaders.add("password", "admin");
        String chatEndpoint = "ws://10.1.14.194:15674/ws";
        String topic = "/queue/test";
        String msg = "我是好人呀";
        String userid = "001";
        StompClient stompClient = new StompClient(chatEndpoint, userid, topic, stompHeaders);
        stompClient.connect();
        stompClient.sendMessage(topic, msg);
//        stompClient.subscribe(topic);
//        stompClient.close();

    }

    /**
     * 关闭连接
     */
    public void connect() {
        try {
            WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
            stompClient.setMessageConverter(new MappingJackson2MessageConverter());
            // 接收大小限制
            stompClient.setInboundMessageSizeLimit(1024 * 1024);
            // 处理心跳
            ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
            taskScheduler.afterPropertiesSet();
            // for heartbeats
            stompClient.setTaskScheduler(taskScheduler);
            customHandler = new ChatStompSessionHandler(topic);
            URI uri = URI.create(chatEndpoint);
            log.info("开始连接");
//            stompSession = stompClient.connect(uri, null, stompHeaders, customHandler).get();
            ListenableFuture<StompSession> future = stompClient.connect(uri, null,
                    stompHeaders, customHandler);
            stompSession = future.get();
            stompSession.setAutoReceipt(true);
            log.info("连接成功");
        } catch (Exception e) {
            log.info("连接失败");
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送消息
     * @param topic
     * @param msg
     */
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
     * 接收消息
     * @param topic
     */
    public void subscribe(String topic) {

        if (customHandler == null) {
            customHandler = new ChatStompSessionHandler(topic);
        }
        try {
            stompSession.subscribe(topic, customHandler);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
