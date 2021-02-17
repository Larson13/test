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
import java.util.concurrent.ExecutionException;

@Slf4j
public class StompClientSampler extends AbstractJavaSamplerClient implements Serializable {

    private static final long serialVersionUID = 1L;
    private StompClient stompClient;

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
    public void setupTest(JavaSamplerContext javaSamplerContext) {

        //        String chatEndpoint = "ws://10.1.14.194:15674/ws";
//        String destination ="/queue/test";
//        String msg = "dsfd地一";
//        String destination ="/queue/test";
//        String msg = "dsfd地一";

        String ip = javaSamplerContext.getParameter("ip");
        String port = javaSamplerContext.getParameter("port");
        String path = javaSamplerContext.getParameter("path");
        String username = javaSamplerContext.getParameter("username");
        String password = javaSamplerContext.getParameter("password");
        String chatEndpoint = "ws://" + ip + ":" + port + path;
        log.info("url: {}", chatEndpoint);
        StompHeaders stompHeaders = new StompHeaders();
        stompHeaders.add("username", username);
        stompHeaders.add("password", password);
        String destination = javaSamplerContext.getParameter("destination");
        String userid = "001";
        stompClient = new StompClient(chatEndpoint, userid, destination, stompHeaders);
        stompClient.connect();

        super.setupTest(javaSamplerContext);
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        log.info("关闭连接");
        if (stompClient != null) {
            stompClient.close();
        }
        super.teardownTest(context);
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.setSampleLabel("StompClientSampler");

        String destination = javaSamplerContext.getParameter("destination");
        String msg = javaSamplerContext.getParameter("msg");
        String headers = javaSamplerContext.getParameter("headers");


        sampleResult.sampleStart();
        //log.info("destination : {}, msg : {}", destination, msg);
        try {


            // ClientMessage message = new  ClientMessage("userXyz", "s我");

            stompClient.sendMessage(destination, msg);
            //     StompSessionHandler customHandler = new CustomStompSessionHandler();
            //  stompClient.getStompSession().subscribe(destination,customHandler);
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
        StompClientSampler stompClientSampler = new StompClientSampler();
        Arguments arguments = new Arguments();
        JavaSamplerContext javaSamplerContext = new JavaSamplerContext(arguments);
        stompClientSampler.runTest(javaSamplerContext);
    }
}
