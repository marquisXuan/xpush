package org.yyx.message.push.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 心跳处理器
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/6 22:49
 */
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    /**
     * HeartbeatHandler 日志输出器
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(HeartbeatHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                LOGGER.info("[userEventTriggered] -> [进入读空闲]");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                LOGGER.info("[userEventTriggered] -> [进入写空闲]");
            } else if (event.state() == IdleState.ALL_IDLE) {
                LOGGER.info("[userEventTriggered] -> [进入读写空闲]");
                Channel channel = ctx.channel();
                // 关闭无用的channel，以防资源浪费
                channel.close();
            }
        }
    }
}
