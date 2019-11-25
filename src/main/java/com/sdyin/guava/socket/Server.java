package com.sdyin.guava.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: liuye
 * @time: 2019/11/25$ 下午9:17$
 */
public class Server {

    public static void main(String[] args) throws IOException {

        ExecutorService es = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(4444);
        System.out.println("服务端启动..");
        while(true){
            System.out.println("获取socket");
            Socket socket = serverSocket.accept();
            System.out.println("得到socket");
            es.execute(() -> {
                try {
                    hand(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    private static void hand(Socket socket) throws IOException {

        InputStream is = socket.getInputStream();
        byte[] b = new byte[1024];

        while(true){
            int read = is.read(b);
            System.out.println("接收到信息:" + new String(b,0,read));
        }
    }

}
