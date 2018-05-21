package org.yyx.message.push.push.client.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.yyx.message.push.push.client.client.WebSocketClient;
import org.yyx.message.push.push.client.config.WebSocketConfig;

import javax.annotation.Resource;

/**
 * WebSocket客户端监听器
 * <p>
 * create by 叶云轩 at 2018/5/21-下午1:38
 * contact by tdg_yyx@foxmail.com
 */
@Component
@Order(1)
public class WebSocketClientListener implements CommandLineRunner {
    @Resource
    private WebSocketConfig webSocketConfig;

    @Resource
    private WebSocketClient webSocketClient;

    @Override
    public void run(String... args) throws Exception {
        webSocketClient.run(webSocketConfig.getHost(), webSocketConfig.getPort());
    }
}
