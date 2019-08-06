package com.github.excellent.entity;

import lombok.Data;

/**
 * @auther plg
 * @date 2019/8/6 21:18
 */
@Data
public class MessageFromClient {
    // 信息
    private String msg;
    // 群/私
    private String type;
    // 目标
    private String to;
}
