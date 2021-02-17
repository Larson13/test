//package cn.com.perf.beihe.pinter.socket;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import java.net.URI;
//import java.net.URISyntaxException;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
//import org.java_websocket.client.WebSocketClient;
//import org.java_websocket.handshake.ServerHandshake;
//
//public class Teacher extends WebSocketClient {
//    private static Logger log = Logger.getLogger(cn.com.perf.beihe.pinter.socket.Teacher.class);
//
//    private String customerUserId;
//
//    private String userId;
//
//    public Teacher(String uri) throws URISyntaxException {
//        super(new URI(uri));
//    }
//
//    public void setCustomerUserId(String customerUserId) {
//        this.customerUserId = customerUserId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getReplyMessage(String msg) {
//        WebSocketMsg msgObject = (WebSocketMsg)JSON.parseObject(msg, WebSocketMsg.class);
//        String recvMsg = msgObject.getMsg();
//        long randomTime = (long)(Math.random() * 2000.0D + 1000.0D);
//        try {
//            Thread.sleep(randomTime);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (StringUtils.isBlank(recvMsg))
//            return WaiterMsg.REPLY_BLANK;
//        if (recvMsg.contains(") || recvMsg.contains("))
//            return WaiterMsg.REPLY_HELLO;
//        if (recvMsg.contains(""))
//        return WaiterMsg.REPLY_BYE;
//        int randomNum = (int)(Math.random() * WaiterMsg.REPLY_MSG_ARRAY.length);
//        return WaiterMsg.REPLY_MSG_ARRAY[randomNum];
//    }
//
//    public void onOpen(ServerHandshake serverHandshake) {
//        log.info("waiter");
//    }
//
//    public void onMessage(String recvMsg) {
//        log.info("waiter+ recvMsg");
//                WebSocketMsg webSocketMsg = (WebSocketMsg)JSONObject.parseObject(recvMsg, WebSocketMsg.class);
//        if ("normal".equals(webSocketMsg.getType())) {
//            String replyMsg = getReplyMessage(recvMsg);
//            String replyMsgJson = JSON.toJSONString(WebSocketMsg.buildTeacherReplyMsg("normal", replyMsg, this.userId, this.customerUserId));
//            send(replyMsgJson);
//            log.info("waiter+ replyMsgJson");
//        }
//    }
//
//    public void onClose(int i, String s, boolean b) {
//        log.info("waiter");
//    }
//
//    public void onError(Exception e) {
//        e.printStackTrace();
//    }
//}