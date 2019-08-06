package com.github.excellent.entity;

import lombok.Data;

import java.util.Map;

/**
 * @auther plg
 * @date 2019/8/6 21:16
 * 向前端发送信息
 * jsonStr
 *
 *  群聊:{"msg":"777","type":1}
 *  私聊:{"to":"0-","msg":"33333","type":2}
 */

@Data
public class Message2Clinet {
    // 内容
    private String content;
    // 用户列表
    private Map<String,String> names;

}
