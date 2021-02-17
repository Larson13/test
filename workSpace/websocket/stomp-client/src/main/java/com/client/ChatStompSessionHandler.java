package com.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
@Slf4j
public class ChatStompSessionHandler extends StompSessionHandlerAdapter {
    //订阅主题
      String destination;

    public ChatStompSessionHandler(String destination) {
        this.destination = destination;
    }

    @Autowired
  //  private  static    ChatWebsocketClient chatWebsocketClient = new ChatWebsocketClient();


//    public ChatStompSessionHandler(ChatWebsocketClient chatWebsocketClient) {
//        this.chatWebsocketClient = chatWebsocketClient;
//    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("接收订阅消息 : {}",(String) payload);
    }

    @Override
    public   void handleTransportError(StompSession stompSession, Throwable exception) {
        System.out.println(exception.getMessage());
        try {
         Thread.sleep(3000);
       //     chatWebsocketClient.connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void afterConnected(final StompSession session, StompHeaders connectedHeaders) {
        //System.out.println("StompHeaders: " + connectedHeaders.toString());
        log.info("StompHeaders: {} " , connectedHeaders.toString());
        //订阅地址，发送端前面没有/user
        //订阅消息
        session.subscribe(destination, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return byte[].class;
            }
            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                //todo 只能接收到byte[]数组，没时间研究原因
             //   System.out.println(new String((byte[])payload));
                log.info("接收订阅消息handleFrame : {}",(String) payload);
                System.out.println("接收订阅消息=" + (String) payload);
            }
        });
    }

}
