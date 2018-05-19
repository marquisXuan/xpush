package org.yyx.message.push.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Netty配置
 * <p>
 * create by 叶云轩 at 2018/5/18-下午7:46
 * contact by tdg_yyx@foxmail.com
 */
@Configuration
@Data
public class NettyConfig {

    @Value("${netty.websocket.port}")
    private int port;
}
