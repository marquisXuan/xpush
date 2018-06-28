package org.yyx.message.push.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Netty配置
 * <p>
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/6/28 - 上午10:17
 */
@Configuration
@Data
public class NettyConfig {

    @Value("${netty.web.socket.port}")
    private int port;
}
