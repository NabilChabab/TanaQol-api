//package com.example.tanaqolapi.config;
//
//import com.corundumstudio.socketio.SocketIOServer;
//import org.springframework.context.annotation.Bean;
//import com.corundumstudio.socketio.SocketIOServer;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SocketIOConfig {
//
//    @Bean
//    public SocketIOServer socketIOServer() {
//        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
//        config.setHostname("localhost");
//        config.setOrigin("http://localhost:4200");
//        config.setPort(9092);
//        return new SocketIOServer(config);
//    }
//}