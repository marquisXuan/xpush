package org.yyx.message.push.server.strategies;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * 策略接口
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/6 23:09
 */
public interface FrameStrategy {

    /**
     * 处理消息的方法
     *
     * @param channel 消息通道
     */
    void dealMessage(Channel channel, WebSocketFrame frame);
}
