package com.sdyin.guava.websocket.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author liuye
 * @Date 2019/11/22 15:48
 **/
@Service
@ServerEndpoint(value = "/echo")
public class WebSocketService {

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketSet对象。
     */
    private static CopyOnWriteArraySet<WebSocketService> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 客户端用户id
     */
    private String userId = "12345";

    private static final Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        this.userId = userId;
        logger.info("[执行onOpen方法] session:{}", JSON.toJSONString(session));
        if (userId != null) {
            //加入set中
            webSocketSet.add(this);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        logger.info("[执行onClose方法]");
        //从set中删除
        webSocketSet.remove(this);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("[onMessage,接收到消息]message:{}",message);
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("[执行onError方法] session:{},error:{}");
        error.printStackTrace();
    }

    /**
     * 向客户端发送消息
     *
     * @param message 消息内容
     * @param userIds 需要发送的客户端用户id列表
     */
    public void sendMessage(String message, List<String> userIds) {
        // 需要发送消息的客户端
        List<WebSocketService> services = webSocketSet.stream()
                .filter(temp -> userIds.contains(temp.userId))
                .collect(Collectors.toList());
        services.forEach(temp -> {
            try {
                temp.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
