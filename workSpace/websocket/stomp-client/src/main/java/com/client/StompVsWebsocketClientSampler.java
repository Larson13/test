package com.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.messaging.simp.stomp.StompSessionHandler;


import java.io.Serializable;


@Slf4j
public class StompVsWebsocketClientSampler extends AbstractJavaSamplerClient implements Serializable {

    private static final long serialVersionUID = 1L;
    private ChatWebsocketClient chatWebsocketClient = null;

    @Override
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument("ip", "127.0.0.1");
        arguments.addArgument("port", "80");
        arguments.addArgument("path", "/websocket");
        arguments.addArgument("username", "admin");
        arguments.addArgument("password", "admin");
        arguments.addArgument("destination", "/topic");
        // arguments.addArgument("type", "send or receive");
        arguments.addArgument("msg", "");
        arguments.addArgument("headers", "{}");
        // arguments.addArgument("receiveTopic", "/topic");
        return arguments;
    }

    @Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {
        super.setupTest(javaSamplerContext);
        String ip = javaSamplerContext.getParameter("ip");
        String port = javaSamplerContext.getParameter("port");
        String path = javaSamplerContext.getParameter("path");
        String username = javaSamplerContext.getParameter("username");
        String password = javaSamplerContext.getParameter("password");
        String destination = javaSamplerContext.getParameter("destination");
        String chatEndpoint = "http://" + ip + ":" + port + path;
        String userId = "11";
        log.info("url: {}", chatEndpoint);

        chatWebsocketClient = new ChatWebsocketClient(chatEndpoint, userId, destination);
        chatWebsocketClient.connect();


    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        super.teardownTest(context);
        if (chatWebsocketClient != null) {
            chatWebsocketClient.close();
            log.info("关闭连接");
        }
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.setSampleLabel("Stopm Client");
        //调用上文中实现的post请求
        String destination = javaSamplerContext.getParameter("destination");
        // String type = javaSamplerContext.getParameter("type");
        String msg = javaSamplerContext.getParameter("msg");
        String headers = javaSamplerContext.getParameter("headers");
        //     String receiveTopic = javaSamplerContext.getParameter("receiveTopic");
        log.info("destination : {}, msg : {}", destination, msg);
        sampleResult.sampleStart();
        String response = null;
        try {

            chatWebsocketClient.sendMessage(destination, msg);
            response = "发送完毕";


            log.info(response);
            sampleResult.setSuccessful(true);
            sampleResult.setResponseCodeOK();
            sampleResult.setResponseData(response, "utf-8");

        } catch (Exception e) {
            sampleResult.setSuccessful(false);
            sampleResult.setResponseData(e.getMessage(), "utf-8");
            throw new RuntimeException(e);

        }
        sampleResult.sampleEnd();
        return sampleResult;
    }

    public static void main(String[] args) {
        String chatEndpoint = "http://127.0.0.1:8072/websocket";
//        String userId = "100";
//        String topic = "/queue/test1";
//        ChatWebsocketClient  chatWebsocketClient = new ChatWebsocketClient(chatEndpoint,userId,topic);
//        chatWebsocketClient.connect();
//        String msg = "{\"fromUserId\":\"100\",\"toUserId\":\"2\",\"msg\":\"我很开心rr呀\"}";
//        String destination ="/queue/test1";
//    //   chatWebsocketClient.sendMessage(destination,msg);
//        StompSessionHandler chatStompSessionHandler = new ChatStompSessionHandler(destination);
//        chatWebsocketClient.getStompSession().subscribe(topic, chatStompSessionHandler);
//        //chatWebsocketClient.reciveMessage(topic, chatStompSessionHandler);
//        //   System.out.println(chatWebsocketClient.sendMessage(destination,msg));
//        StompVsWebsocketClientSampler stompVsWebsocketClientSampler = new StompVsWebsocketClientSampler();
//        Arguments arguments = new Arguments();
//        JavaSamplerContext javaSamplerContext = new JavaSamplerContext(arguments);
//        stompVsWebsocketClientSampler.runTest(javaSamplerContext);
    }
}
