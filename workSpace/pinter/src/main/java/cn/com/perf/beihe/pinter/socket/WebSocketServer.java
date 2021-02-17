//package cn.com.perf.beihe.pinter.socket;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import java.io.IOException;
//import java.util.UUID;
//import java.util.concurrent.ConcurrentHashMap;
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Component;
//
//@ServerEndpoint("/imserver/{userId}")
//@Component
//public class WebSocketServer {
//    private static Logger log = Logger.getLogger(cn.com.perf.beihe.pinter.socket.WebSocketServer.class);
//
//    private static ConcurrentHashMap<String, cn.com.perf.beihe.pinter.socket.WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
//
//    private static ConcurrentHashMap<String, String> userWaiterMap = new ConcurrentHashMap<>();
//
//    private Session session;
//
//    private String userId;
//
//    private Teacher teacher;
//
//    @OnOpen
//    public void onOpen(Session session, @PathParam("userId") String userId) {
//        this.session = session;
//        this.userId = userId;
//        if (webSocketMap.containsKey(userId)) {
//            webSocketMap.remove(userId);
//            webSocketMap.put(userId, this);
//        } else {
//            webSocketMap.put(userId, this);
//        }
//        try {
//            if (!userId.endsWith("__mtx")) {
//                String waiterId = UUID.randomUUID().toString().replaceAll("-", "") + "__mtx";
//                String uri = "ws://127.0.0.1:" + WebSocketConfig.wsPort + "/pinter/imserver/" + waiterId;
//                this.teacher = new Teacher(uri);
//                this.teacher.setCustomerUserId(userId);
//                this.teacher.setUserId(waiterId);
//                userWaiterMap.put(userId, waiterId);
//                userWaiterMap.put(waiterId, userId);
//                this.teacher.connect();
//                log.info("+ userId + "+ waiterId);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        log.info("+ userId + "+ session);
//    }
//
//    @OnClose
//    public void onClose() {
//        if (webSocketMap.containsKey(this.userId))
//            webSocketMap.remove(this.userId);
//        log.info("+ this.userId");
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        log.info("+ this.userId + "+ message);
//        if (StringUtils.isBlank(message)) {
//            sendMessage(JSON.toJSONString(WebSocketMsg.buildSystemFailMsg("normal", ", this.userId")));
//            return;
//        }
//        try {
//            WebSocketMsg webSocketMsg = (WebSocketMsg)JSONObject.parseObject(message, WebSocketMsg.class);
//            String type = webSocketMsg.getType();
//            String from = webSocketMsg.getFrom();
//            String to = webSocketMsg.getTo();
//            if (StringUtils.isBlank(type) ||
//                    StringUtils.isBlank(from) ||
//                    StringUtils.isBlank(to)) {
//                sendMessage(JSON.toJSONString(WebSocketMsg.buildSystemFailMsg("normal", ", this.userId")));
//                        log.info(""+ this.userId);
//                return;
//            }
//            if ("match".equals(webSocketMsg.getType())) {
//                processMatchMsg();
//            } else if ("normal".equals(webSocketMsg.getType())) {
//                processNormalMsg(from, to, webSocketMsg);
//            }
//        } catch (Exception e) {
//            sendMessage(JSON.toJSONString(WebSocketMsg.buildSystemFailMsg("receipt", "消息为空", this.userId)));
//                    log.error(e);
//        }
//    }
//
//    @OnError
//    public void onError(Session session, Throwable error) {
//        log.error("+ this.userId + ", + error.getMessage());
//        error.printStackTrace();
//    }
//
//    public void sendMessage(String message) {
//        try {
//            this.session.getBasicRemote().sendText(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void sendMessage(WebSocketMsg webSocketMsg) {
//        try {
//            this.session.getBasicRemote().sendText(JSON.toJSONString(webSocketMsg));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void processMatchMsg() {
//        String waiterId = userWaiterMap.get(this.userId);
//        if (waiterId != null) {
//            sendMessage(WebSocketMsg.buildSystemSuccMsg("match", waiterId, this.userId));
//        } else {
//            sendMessage(WebSocketMsg.buildSystemFailMsg("match", "消息为空", this.userId));
//                    log.info("消息为空"+ this.userId);
//        }
//    }
//
//    private void processNormalMsg(String from, String to, WebSocketMsg webSocketMsg) {
//        if (StringUtils.isBlank(webSocketMsg.getMsg())) {
//            sendMessage(WebSocketMsg.buildSystemFailMsg("receipt", "消息为空", this.userId));
//            return;
//        }
//        if (!userWaiterMap.containsKey(from)) {
//            sendMessage(WebSocketMsg.buildSystemFailMsg("receipt", "消息为空", this.userId));
//            return;
//        }
//        if (!((String)userWaiterMap.get(from)).equals(to)) {
//            sendMessage(WebSocketMsg.buildSystemFailMsg("receipt", "from", this.userId));
//                    log.info("from"+ from + ":"+ to);
//            return;
//        }
//        if (webSocketMap.containsKey(to)) {
//            ((cn.testfan.perf.beihe.pinter.socket.WebSocketServer)webSocketMap.get(to)).sendMessage(webSocketMsg);
//            log.info("+ from + "+ to);
//        }
//        sendMessage(JSON.toJSONString(WebSocketMsg.buildSystemSuccMsg("receipt", "push success", this.userId)));
//        log.info("消息为空"+ this.userId);
//    }
//
//    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
//        log.info("+ userId + "+ message);
//        if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
//            ((cn.com.perf.beihe.pinter.socket.WebSocketServer)webSocketMap.get(userId)).sendMessage(message);
//        } else {
//            log.error("+ userId + ","不在线");
//        }
//    }
//}
