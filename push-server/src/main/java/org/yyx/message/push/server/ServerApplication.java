package org.yyx.message.push.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于Netty开发的WebSocketServer端启动类
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/6/28 - 上午10:17
 */
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
