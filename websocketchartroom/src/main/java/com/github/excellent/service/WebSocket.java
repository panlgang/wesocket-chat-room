package com.github.excellent.service;
import com.github.excellent.entity.Message2Clinet;
import com.github.excellent.entity.MessageFromClient;
import com.github.excellent.utils.CommonUtils;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.io.*;
/**
 * 服务器端
 * @auther plg
 * @date 2019/8/3 11:54
 */

@ServerEndpoint("/websocket")
public class WebSocket {
    //存储所有在线的websocket
    private static CopyOnWriteArraySet<WebSocket> set = new CopyOnWriteArraySet<>();

    // 所有登录过的用户
    private static Map<WebSocket,File> client = new ConcurrentHashMap<>();
    // 用户列表
    private static Map<String,String> map = new ConcurrentHashMap<>();

    // 浏览器的会话
    private Session session;
    // 用户名
    private String userName;

    @OnOpen
    // 建立连接调用
    public void onOpen(Session session) throws IOException {
        this.session = session;
        this.userName = session.getQueryString().split("=")[1];
        set.add(this);
        File file = new File(userName + ".txt");
        if(!file.exists()){
            file.createNewFile();
        }
        String s = file.getAbsolutePath();
        client.put(this,file);
        map.put(session.getId(),userName);


        System.out.println("新的连接，SessionId为" + session.getId());
        System.out.println("当前共有" + set.size() + "人");
        Message2Clinet message2Clinet = new Message2Clinet();
        message2Clinet.setContent(userName + "上线了");
        // 更新用户列表
        message2Clinet.setNames(map);
        String message = CommonUtils.object2Json(message2Clinet);
        allSend(message);
    }

    @OnError
    public void onError(Throwable e){
        System.err.println("webSocket连接失败");
        e.printStackTrace();
    }

    @OnMessage  // 收到信息调用
    public void onMessage(String message){
       MessageFromClient client = (MessageFromClient) CommonUtils.Json2Object(message,MessageFromClient.class);
       String type = client.getType();
       String content = userName + "说：" +  client.getMsg();
       // "0-1-2-3-"
       String to = client.getTo();
       Message2Clinet message2Clinet = new Message2Clinet();
       message2Clinet.setContent(content);
       message2Clinet.setNames(map);
       String mess = CommonUtils.object2Json(message2Clinet);
       if("1".equals(type)){
          allSend(mess);
       }else if("2".equals(type)){
           List<String> list = Arrays.asList(to.split("-"));
           notAllSend(mess,list);
       }
    }

    @OnClose
    // 关闭连接时调用
    public void onClose(){
        set.remove(this);
        map.remove(userName);
        System.out.println("有连接下线，SessionId为" + session.getId());
        System.out.println("当前共有" + set.size() + "人");

        Message2Clinet message2Clinet = new Message2Clinet();
        message2Clinet.setContent(userName + "下线了");

        message2Clinet.setNames(map);

        String message = CommonUtils.object2Json(message2Clinet);
        allSend(message);
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

    /**
     * 群聊
     * @param message
     */
    private void allSend(String message){
        for(WebSocket webSocket : set){
            webSocket.sendMessage(message);
        }
    }

    /**
     * 给部分用户发送（包含私聊）
     * @param message
     * @param list
     */
    private void notAllSend(String message,List<String> list){
        for(WebSocket webSocket : set){
            if(list.contains(webSocket.session.getId())){
                webSocket.sendMessage(message);
            }
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebSocket webSocket = (WebSocket) o;
        return Objects.equals(userName, webSocket.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
