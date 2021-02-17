package com.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.Serializable;
import java.net.URI;

@Slf4j
public class StompClientSampler1 extends AbstractJavaSamplerClient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument("ip", "127.0.0.1");
        arguments.addArgument("port", "80");
        arguments.addArgument("path", "/websocket");
        arguments.addArgument("username", "admin");
        arguments.addArgument("password", "admin");
        arguments.addArgument("destination", "/topic");
        arguments.addArgument("msg", "");
        arguments.addArgument("headers", "{}");
        return arguments;
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.setSampleLabel("StompClientSampler");
        String ip = javaSamplerContext.getParameter("ip");
        String port = javaSamplerContext.getParameter("port");
        String path = javaSamplerContext.getParameter("path");
        String username = javaSamplerContext.getParameter("username");
        String password = javaSamplerContext.getParameter("password");
        String destination = javaSamplerContext.getParameter("destination");
        String msg = javaSamplerContext.getParameter("msg");
        String headers = javaSamplerContext.getParameter("headers");
        String url = "ws://"+ip+":"+port+path;
        log.info("url: {}", url);
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        sampleResult.sampleStart();
        log.info("destination : {}, msg : {}", destination, msg);
        try {

            // 接收大小限制
            stompClient.setInboundMessageSizeLimit(1024 * 1024);
            // 处理心跳
            ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
            taskScheduler.afterPropertiesSet();
            // for heartbeats
            stompClient.setTaskScheduler(taskScheduler);
            StompSessionHandler customHandler = new CustomStompSessionHandler();
            // 可以发送请求头
            StompHeaders stompHeaders = new StompHeaders();
//            stompHeaders.add("username", username);
//            stompHeaders.add("password", password);

            stompHeaders.add("username", "admin");
            stompHeaders.add("password", "admin");
            log.info("开始建立连接");
          URI uri = URI.create("ws://10.1.14.194:15674/ws");
         //   URI uri = URI.create(url);
            StompSession session = stompClient.connect(uri, null, stompHeaders, customHandler).get();

            log.info("连接成功");
             ClientMessage message = new  ClientMessage("userXyz", "s我");

           // session.send(destination, msg);
           session.send("/queue/test", message);
            session.disconnect();
            log.info("发送完毕");
            sampleResult.setSuccessful(true);
            sampleResult.setResponseCodeOK();
            sampleResult.setResponseData("发送完毕", "utf-8");
        } catch (Exception e) {
            sampleResult.setSuccessful(false);
            sampleResult.setResponseData(e.getMessage(), "utf-8");
            throw new RuntimeException(e);

        }
        sampleResult.sampleEnd();
        return sampleResult;
    }

    public static void main(String[] args) {
        StompClientSampler1 stompClientSampler = new StompClientSampler1();
        Arguments arguments = new Arguments();
        JavaSamplerContext javaSamplerContext = new JavaSamplerContext(arguments);
        stompClientSampler.runTest(javaSamplerContext);
    }
}
