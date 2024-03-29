package org.yyx.message.push.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yyx.message.push.server.handler.FirstHandshakeHandler;
import org.yyx.message.push.server.service.FirstHandshakeHandlerService;
import org.yyx.message.push.server.strategies.*;

/**
 * Netty配置
 * <p>
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/6/28 - 上午10:17
 */
@Data
@Configuration
public class NettyConfig {

    /**
     * WebSocket-netty Server port
     */
    @Value("${netty.web.socket.port}")
    private int port;

    /**
     * WebSocket Url
     */
    @Value("${netty.web.socket.url}")
    private String url;

    /**
     * 没有文本消息处理策略时，使用默认策略
     *
     * @return 文本消息策略
     */
    @Bean
    @ConditionalOnMissingBean(TextWebSocketFrameDealStrategy.class)
    public TextWebSocketFrameStrategy textWebSocketFrameStrategy() {
        return new TextWebSocketFrameStrategy();
    }

    /**
     * 没有二进制消息处理策略时，使用默认策略
     *
     * @return 二进制消息数据处理策略
     */
    @Bean
    @ConditionalOnMissingBean(BinaryWebSocketFrameDealStrategy.class)
    public BinaryWebSocketFrameStrategy binaryWebSocketFrameStrategy() {
        return new BinaryWebSocketFrameStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(PongWebSocketFrameDealStrategy.class)
    public PongWebSocketFrameStrategy pongWebSocketFrameStrategy() {
        return new PongWebSocketFrameStrategy();
    }

    /**
     * 在没有第一次握手处理的情况下，使用默认的处理机制
     *
     * @return 默认的处理第一次握手情况
     */
    @Bean
    @ConditionalOnMissingBean(FirstHandshakeHandlerService.class)
    public FirstHandshakeHandlerService firstHandshakeHandler() {
        return new FirstHandshakeHandler();
    }
}
