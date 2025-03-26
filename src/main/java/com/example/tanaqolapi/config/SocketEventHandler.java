//package com.example.tanaqolapi.config;
//
//import com.corundumstudio.socketio.SocketIOClient;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.listener.ConnectListener;
//import com.corundumstudio.socketio.listener.DisconnectListener;
//import com.corundumstudio.socketio.listener.DataListener;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class SocketEventHandler {
//
//    private final SocketIOServer socketIOServer;
//
//    public SocketEventHandler(SocketIOServer socketIOServer) {
//        this.socketIOServer = socketIOServer;
//    }
//
//    @PostConstruct
//    public void setupListeners() {
//        // Handle client connection
//        socketIOServer.addConnectListener(new ConnectListener() {
//            @Override
//            public void onConnect(SocketIOClient client) {
//                System.out.println("Client connected: " + client.getSessionId());
//            }
//        });
//
//        // Handle client disconnection
//        socketIOServer.addDisconnectListener(new DisconnectListener() {
//            @Override
//            public void onDisconnect(SocketIOClient client) {
//                System.out.println("Client disconnected: " + client.getSessionId());
//            }
//        });
//
//        // Handle ride-request event
//        socketIOServer.addEventListener("ride-request", String.class, new DataListener<String>() {
//            @Override
//            public void onData(SocketIOClient client, String data, com.corundumstudio.socketio.AckRequest ackRequest) {
//                System.out.println("Received ride-request: " + data);
//                // Broadcast the ride request to all drivers
//                socketIOServer.getBroadcastOperations().sendEvent("ride-request", data);
//            }
//        });
//    }
//}