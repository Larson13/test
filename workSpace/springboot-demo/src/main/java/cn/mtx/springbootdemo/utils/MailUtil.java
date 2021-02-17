//package cn.testfan.ptp.util;
//
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.*;
//
//public class MailUtil {
//
//    public static String myEmailAccount = "****@163.com";
//    public static String myEmailPassword = "****";
//    public static String myEmailSMTPHost = "smtp.163.com";
//
//	/**
//	*	Map<String,String> 包含：subject、content、receiveAddr
//	*
//	*/
//    public static Boolean send (Map<String,String> map){
//        Boolean success = false;
//        try {
//            // 1. 创建参数配置, 用于连接邮件服务器的参数配置
//            Properties props = new Properties();                    // 参数配置
//            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
//            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
//            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
//
//            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
//            Session session = Session.getDefaultInstance(props);
//    //        session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log
//
//            // 3. 创建一封邮件
//            MimeMessage message = createMimeMessage(session, myEmailAccount, map);
//
//            // 4. 根据 Session 获取邮件传输对象
//            Transport transport = session.getTransport();
//
//            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
//            transport.connect(myEmailSMTPHost,myEmailAccount, myEmailPassword);
//
//            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
//            transport.sendMessage(message, message.getAllRecipients());
//
//            // 7. 关闭连接
//            transport.close();
//            success = true;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return success;
//    }
//    /**
//     * 创建一封只包含文本的简单邮件
//     *
//     * @param session 和服务器交互的会话
//     * @param sendMail 发件人邮箱
//     * @return
//     * @throws Exception
//     */
//    public static MimeMessage createMimeMessage(Session session, String sendMail, Map<String,String> map) throws Exception {
//        // 1. 创建一封邮件
//        MimeMessage message = new MimeMessage(session);
//        // 2. From: 发件人
//        message.setFrom(new InternetAddress(sendMail, "PTP压测平台", "UTF-8"));
//        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
//        String[] receiveAddr = removeRepeat(map);
//        InternetAddress[] addresses = new InternetAddress[receiveAddr.length];
//        for (int i=0;i<receiveAddr.length;i++){
//            addresses[i] = new InternetAddress(receiveAddr[i]);
//        }
//        message.setRecipients(MimeMessage.RecipientType.TO,addresses);
//        // 4. Subject: 邮件主题
//        message.setSubject(map.get("subject"), "UTF-8");
//        // 5. Content: 邮件正文（可以使用html标签）
//        message.setContent(map.get("content"), "text/html;charset=UTF-8");
//        // 6. 设置发件时间
//        message.setSentDate(new Date());
//        // 7. 保存设置
//        message.saveChanges();
//        return message;
//    }
//
//    private static String[] removeRepeat(Map<String, String> map) {
//        String[] recvAddr = map.get("receiveAddr").trim().split(";");
//        HashSet<String> set = new HashSet<>(Arrays.asList(recvAddr));
//        String[] newArray = new String[set.size()];
//        set.toArray(newArray);
//        return newArray;
//    }
//}
