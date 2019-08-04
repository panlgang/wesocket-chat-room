package com.github.excellent.service;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 服务器端
 * @auther plg
 * @date 2019/8/3 11:54
 */

@ServerEndpoint("/websocket")
public class WebSocket {
    //存储所有的websocket
    private static CopyOnWriteArraySet<WebSocket> set = new CopyOnWriteArraySet<>();
    // 浏览器的会话
    private Session session;

    @OnOpen
    // 建立连接调用
    public void onOpen(Session session){
        this.session = session;
        set.add(this);
        System.out.println("新的连接，当前SessionId为" + session.getId());
        System.out.println("当前共有" + set.size() + "人");
    }

    @OnError
    public void onError(Throwable e){
        System.err.println("webSocket连接失败");
        e.printStackTrace();
    }

    @OnMessage  // 收到信息调用
    public void onMessage(String message){
        for(WebSocket webSocket : set){
            webSocket.sendMessage(message);
        }
    }

    @OnClose
    // 关闭连接时调用
    public void onClose(){
        System.out.println("有用户退出连接");
        set.remove(this);
        System.out.println("当前聊天室还剩下：" + set.size() + "人");
    }

    /**
     * 向浏览器发送信息
     * @param message
     */
    private void sendMessage(String message)  {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
