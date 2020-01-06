package com.tjh.request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟发布服务
 */
public class ServerRequest {

    public void publishServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        while (true) {
            System.out.println("wait conn");
            Socket socket = serverSocket.accept();
            System.out.println("conncted success");
            executorService.execute(new ServerHandler(socket));
        }

    }
}
