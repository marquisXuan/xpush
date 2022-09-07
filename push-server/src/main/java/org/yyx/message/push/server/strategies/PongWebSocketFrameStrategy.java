package org.yyx.message.push.server.strategies;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.yyx.message.push.server.factories.FrameStrategyFactory;

/**
 * Pong策略
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/6 23:07
 */
public class PongWebSocketFrameStrategy implements FrameStrategy, InitializingBean, PongWebSocketFrameDealStrategy {

    /**
     * PongWebSocketFrameStrategy 日志输出器
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(PongWebSocketFrameStrategy.class);

    public PongWebSocketFrameStrategy() {
        LOGGER.info("[PongWebSocketFrameStrategy] -> [注册Pong消息策略]");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        FrameStrategyFactory.register(PongWebSocketFrame.class, this);
    }

    @Override
    public void dealMessage(Channel channel, WebSocketFrame frame) {
        LOGGER.info("[dealMessage] -> [接收到来自客户端 {} 的ping 消息]", channel.id());
        channel.write(new PongWebSocketFrame(frame.content().retain()));
    }
}
