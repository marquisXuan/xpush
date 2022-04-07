package org.yyx.message.push.server.strategies;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.yyx.message.push.server.factories.FrameStrategyFactory;

/**
 * 文本消息策略 一般用于处理文本消息
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/6 23:07
 */
public class TextWebSocketFrameStrategy implements FrameStrategy, InitializingBean {

    /**
     * PongWebSocketFrameStrategy 日志输出器
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(TextWebSocketFrameStrategy.class);

    public TextWebSocketFrameStrategy() {
        LOGGER.info("[TextWebSocketFrameStrategy] -> [注册纯文本消息策略]");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        FrameStrategyFactory.register(TextWebSocketFrame.class, this);
    }

    @Override
    public void dealMessage(Channel channel, WebSocketFrame frame) {
        String text = ((TextWebSocketFrame) frame).text();
        LOGGER.info("[dealMessage] -> [接收到来自客户端 {} 的 纯文本 消息] 内容是 {}", channel.id(), text);
        channel.write(new TextWebSocketFrame("你发的消息是：" + text));
    }
}
