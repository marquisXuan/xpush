package org.yyx.message.push.server.context;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.yyx.message.push.server.factories.FrameStrategyFactory;

/**
 * 消息处理策略上下文
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/6 23:15
 */
public class FrameStrategyContext {

    /**
     * 策略入口
     *
     * @param channel        消息通道
     * @param webSocketFrame 消息类型
     */
    public static void handlerWebSocketFrame(Channel channel, WebSocketFrame webSocketFrame) {
        FrameStrategyFactory.builder(webSocketFrame.getClass()).dealMessage(channel, webSocketFrame);
    }

}
