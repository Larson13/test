//package cn.com.perf.beihe.pinter.conf;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.web.socket.server.standard.ServerEndpointExporter;
//
//@Configuration
//public class WebSocketConfig {
//    public static int wsPort;
//
//    @Value("${server.port}")
//    public void setPort(int port) {
//        wsPort = port;
//    }
//
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }
//}
