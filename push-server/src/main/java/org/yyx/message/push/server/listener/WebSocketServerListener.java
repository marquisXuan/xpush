package org.yyx.message.push.server.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.yyx.message.push.server.config.NettyConfig;
import org.yyx.message.push.server.main.WebSocketServer;

import javax.annotation.Resource;

/**
 * 消息推送监听器
 * <p>
 * create by 叶云轩 at 2018/5/18-下午7:45
 * contact by tdg_yyx@foxmail.com
 */
@Component
@Order(1)
public class WebSocketServerListener implements CommandLineRunner {

    @Resource
    private NettyConfig nettyConfig;

    @Resource
    private WebSocketServer webSocketServer;

    @Override
    public void run(String... args) throws Exception {
        webSocketServer.run(nettyConfig.getPort());
    }
}