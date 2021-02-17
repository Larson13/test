//package cn.com.perf.beihe.pinter.socket;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//
//public class TestfanTcpServer {
//    public static void main(String[] args) {
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(8888);
//        } catch (IOException e) {
//            System.out.println("TcpServer");
//        }
//        System.out.println("TcpServer");
//                ServerSocket finalServerSocket = serverSocket;
//        (new Thread((Runnable)new Object(finalServerSocket)))
//
//                .start();
//    }
//}
