package org.yyx.message.push.server.strategies;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.msgpack.MessagePack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.yyx.message.push.server.domain.entity.WebSocketMessageEntity;
import org.yyx.message.push.server.factories.FrameStrategyFactory;
import org.yyx.message.push.server.util.WebSocketUsers;

import java.io.IOException;

/**
 * 二进制消息策略 一般用于传文件
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/6 23:07
 */
public class BinaryWebSocketFrameStrategy implements FrameStrategy, InitializingBean {

    /**
     * PongWebSocketFrameStrategy 日志输出器
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(BinaryWebSocketFrameStrategy.class);

    public BinaryWebSocketFrameStrategy() {
        LOGGER.info("[BinaryWebSocketFrameStrategy] -> [注册二进制消息策略]");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        FrameStrategyFactory.register(BinaryWebSocketFrame.class, this);
    }

    @Override
    public void dealMessage(Channel channel, WebSocketFrame frame) {
        LOGGER.info("[dealMessage] -> [接收到来自客户端 {} 的 二进制 消息] ", channel.id());
        BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) frame;
        ByteBuf content = binaryWebSocketFrame.content();
        LOGGER.info("├ [二进制数据]:{}", content);
        final int length = content.readableBytes();
        final byte[] array = new byte[length];
        content.getBytes(content.readerIndex(), array, 0, length);
        MessagePack messagePack = new MessagePack();
        WebSocketMessageEntity webSocketMessageEntity = null;
        try {
            webSocketMessageEntity = messagePack.read(array, WebSocketMessageEntity.class);
        } catch (IOException e) {
            e.printStackTrace();

        }
        LOGGER.info("├ [解码数据]: {}", webSocketMessageEntity);
        WebSocketUsers.sendMessageToUser(webSocketMessageEntity.getAcceptName(), webSocketMessageEntity.getContent());
    }
}
