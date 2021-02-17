package com.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.TestStateListener;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.util.StopWatch;

@Slf4j
public class StompSampler extends AbstractSampler implements TestStateListener {
    private static StompClient stompClient;
    private static  ChatWebsocketClient chatWebsocketClient;


    /**
     * private final JLabeledTextField serverIp = new JLabeledTextField("ServerIp");
     * private final JLabeledTextField port = new JLabeledTextField("Port");
     * private final JLabeledTextField path = new JLabeledTextField("Path");
     * private final JLabeledTextField username = new JLabeledTextField("Username");
     * private final JLabeledTextField destination = new JLabeledTextField("Destination");
     * private final JLabeledTextField password = new JLabeledTextField("Password");
     * private final JLabeledTextField msg = new JLabeledTextField("Msg");
     * private final JLabeledTextField headers = new JLabeledTextField("Headers");
     */
    private static final String SERVER_IP = "server_ip";
    private static final String PORT = "port";
    private static final String PATH = "path";
    private static final String DESTINATION = "destination";
    private static final String PASSWORD = "password";
    private static final String CONTENT = "content";
    private static final String HEADERS = "headers";
    private static final String USERNAME = "username";
    private static final String TYPE = "type";

    public StompSampler() {
        setName("StompSampler");
    }

    @Override
    public SampleResult sample(Entry entry) {
        SampleResult sampleResult = new SampleResult();
        // 开始统计响应时间标记
        sampleResult.sampleStart();


        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            String ip = getServerIp().trim();
            Integer port = getPort();
            String path = getPath().trim();
            String destination = getDestination().trim();
            String username = getUsername().trim();
            String password = getPassword().trim();
            String msg = getContent().trim();
            String headers =getHeaders().trim();
            String type = getType().trim();

            log.info("ip : {}",ip);
            log.info("port : {}",port);
            log.info("path : {}",path);
            log.info("destination : {}",destination);
            log.info("username : {}",username);
            log.info("password : {}",password);
            log.info("msg : {}",msg);
            log.info("headers : {}",headers);
            log.info("type : {}",type);

            String chatEndpoint =null;
            if("http".equals(type)){
                chatEndpoint= "http://" + ip + ":" + port + path;
                log.info("url: {}", chatEndpoint);
                chatWebsocketClient = new ChatWebsocketClient(chatEndpoint, "userId", destination);
                synchronized (ChatWebsocketClient.class) {
                    chatWebsocketClient.connect();
                    chatWebsocketClient.sendMessage(destination, msg);
                }
            } else {
                chatEndpoint= "ws://" + ip + ":" + port + path;
                log.info("url: {}", chatEndpoint);
                StompHeaders stompHeaders = new StompHeaders();
                stompHeaders.add("username", username);
                stompHeaders.add("password", password);
                stompClient = new StompClient(chatEndpoint, "userId", destination, stompHeaders);

                synchronized (StompClient.class) {
                    stompClient.connect();
                    stompClient.sendMessage(destination,msg);
                }

            }
            //log.info("url: {}", chatEndpoint);
            log.info("destination : {}, msg : {}", destination, msg);
            log.info("发送成功");
            sampleResult.setSuccessful(true);
            sampleResult.setResponseCodeOK();
            sampleResult.setResponseData("请求成功", "utf-8");
            sampleResult.setResponseMessage("发送成功");
            sampleResult.setResponseCode("200");
        } catch (Exception e) {
            sampleResult.setSuccessful(false);
            sampleResult.setResponseMessage("请求失败....请求参数：" + e.getMessage());
            sampleResult.setResponseCode("500");
            sampleResult.setResponseData("请求失败".getBytes());
            throw new RuntimeException(e);
        } finally {
            if (stompClient != null) {
             stompClient.close();
            }

            if (chatWebsocketClient != null) {
                chatWebsocketClient.close();

            }
            log.info("关闭连接");
            // 结束统计响应时间标记
            sampleResult.sampleEnd();
        }
        return sampleResult;
    }

    @Override
    public void testStarted() {

    }

    @Override
    public void testStarted(String s) {

    }

    @Override
    public void testEnded() {
        this.testEnded("local");

    }

    @Override
    public void testEnded(String s) {

    }

    public void setServerIp(String serverIp) {
        setProperty(SERVER_IP, serverIp);
    }

    public String getServerIp() {
        return getPropertyAsString(SERVER_IP);
    }

    public void setPort(Integer port) {
        setProperty(PORT, port);
    }

    public Integer getPort() {
        return getPropertyAsInt(PORT);
    }

    public void setPath(String path) {
        setProperty(PATH, path);
    }

    public String getPath() {
        return getPropertyAsString(PATH);
    }

    public void setDestination(String destination) {
        setProperty(DESTINATION, destination);
    }

    public String getDestination() {
        return getPropertyAsString(DESTINATION);
    }

    public void setPassword(String password) {
        setProperty(PASSWORD, password);
    }

    public String getPassword() {
        return getPropertyAsString(PASSWORD);
    }

    public void setContent(String content) {
        setProperty(CONTENT, content);
    }

    public String getContent() {
        return getPropertyAsString(CONTENT);
    }

    public void setHeaders(String headers) {
        setProperty(HEADERS, headers);
    }

    public String getHeaders() {
        return getPropertyAsString(HEADERS);
    }

    public void setUsername(String username) {
        setProperty(USERNAME, username);
    }

    public String getUsername() {
        return getPropertyAsString(USERNAME);
    }

    public void setType(String type) {
        setProperty(TYPE, type);
    }

    public String getType() {
        return getPropertyAsString(TYPE);
    }
}
