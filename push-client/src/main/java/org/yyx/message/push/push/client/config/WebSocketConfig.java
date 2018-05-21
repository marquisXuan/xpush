package org.yyx.message.push.push.client.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * WebSocket配置类
 * <p>
 * create by 叶云轩 at 2018/5/21-下午6:26
 * contact by tdg_yyx@foxmail.com
 */
@Configuration
@Data
public class WebSocketConfig {

    @Value("${netty.port}")
    private int port;
    @Value("${netty.host}")
    private String host;
    @Value("${netty.websocket.url}")
    private String url;
    @Value("${netty.websocket.user_name}")
    private String userName;
}
