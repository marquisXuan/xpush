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
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/6/28 - 上午10:20
 */
@Component
@Order(1)
public class WebSocketServerListener implements CommandLineRunner {

    /**
     * Netty配置类
     */
    @Resource
    private NettyConfig nettyConfig;

    /**
     * WebSocketServer端
     */
    @Resource
    private WebSocketServer webSocketServer;

    /**
     * 在指定端口启动服务器
     *
     * @param args 参数
     *
     * @throws Exception Exception
     */
    @Override
    public void run(String... args) throws Exception {
        webSocketServer.run(nettyConfig.getPort());
    }
}