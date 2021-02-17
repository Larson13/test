//package cn.com.perf.beihe.pinter.mode;
//import java.io.Serializable;
//import java.util.UUID;
//
//public class WebSocketMsg implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    private String msgId;
//
//    private String code;
//
//    private String type;
//
//    private String msg;
//
//    private String from;
//
//    private String to;
//
//    private String timestamp;
//
//    public String getCode() {
//        return this.code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getMsgId() {
//        return this.msgId;
//    }
//
//    public void setMsgId(String msgId) {
//        this.msgId = msgId;
//    }
//
//    public String getType() {
//        return this.type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getMsg() {
//        return this.msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public String getFrom() {
//        return this.from;
//    }
//
//    public void setFrom(String from) {
//        this.from = from;
//    }
//
//    public String getTo() {
//        return this.to;
//    }
//
//    public void setTo(String to) {
//        this.to = to;
//    }
//
//    public String getTimestamp() {
//        return this.timestamp;
//    }
//
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public static cn.com.perf.beihe.pinter.model.WebSocketMsg buildBaseMsg(String type, String msg, String from, String to) {
//        cn.com.perf.beihe.pinter.model.WebSocketMsg webSocketMsg = new cn.com.perf.beihe.pinter.model.WebSocketMsg();
//        webSocketMsg.setMsgId(UUID.randomUUID().toString());
//        webSocketMsg.setType(type);
//        webSocketMsg.setMsg(msg);
//        webSocketMsg.setFrom(from);
//        webSocketMsg.setTo(to);
//        webSocketMsg.setTimestamp(String.valueOf(System.currentTimeMillis()));
//        return webSocketMsg;
//    }
//
//    public static cn.com.perf.beihe.pinter.model.WebSocketMsg buildSystemFailMsg(String type, String msg, String to) {
//        cn.com.perf.beihe.pinter.model.WebSocketMsg failMsg = buildBaseMsg(type, msg, "system", to);
//        failMsg.setCode("1");
//        return failMsg;
//    }
//
//    public static cn.com.perf.beihe.pinter.model.WebSocketMsg buildSystemSuccMsg(String type, String msg, String to) {
//        cn.com.perf.beihe.pinter.model.WebSocketMsg succMsg = buildBaseMsg(type, msg, "system", to);
//        succMsg.setCode("0");
//        return succMsg;
//    }
//
//    public static cn.com.perf.beihe.pinter.model.WebSocketMsg buildTeacherReplyMsg(String type, String msg, String from, String to) {
//        cn.com.perf.beihe.pinter.model.WebSocketMsg succMsg = buildBaseMsg(type, msg, from, to);
//        succMsg.setCode("0");
//        return succMsg;
//    }
//
//    public String toString() {
//        return "WebSocketMsg{msgId='" + this.msgId + '\'' + ", type='" + this.type + '\'' + ", msg='" + this.msg + '\'' + ", from='" + this.from + '\'' + ", to='" + this.to + '\'' + '}';
//    }
//}
