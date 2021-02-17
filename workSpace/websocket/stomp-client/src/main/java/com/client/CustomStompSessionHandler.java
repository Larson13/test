package com.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
@Component
@Slf4j
public class CustomStompSessionHandler extends StompSessionHandlerAdapter {
    @Autowired
    private    StompClient stompClient = new StompClient();
    public CustomStompSessionHandler(){
    }
    @Override
    public   void handleFrame(StompHeaders headers, Object payload) {
        // System.out.println("接收订阅消息=" + (String) payload);
        log.info("接收订阅消息 : {}",(String) payload);
    }

    @Override
    public void afterConnected(final StompSession session, StompHeaders connectedHeaders) {
        System.out.println("StompHeaders: " + connectedHeaders.toString());
        //订阅地址，发送端前面没有/user
        String destination = "/queue/notifications";
        //订阅消息
        session.subscribe(destination, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return byte[].class;
            }
            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                //todo 只能接收到byte[]数组，没时间研究原因
                System.out.println(new String((byte[])payload));
            }
        });
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
                                Throwable exception) {
        System.out.println(exception.getMessage());
        try {
            Thread.sleep(3000);
            stompClient.connect();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        System.out.println(exception.getMessage());
        try {
            Thread.sleep(3000);
            stompClient.connect();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}