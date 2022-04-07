package org.yyx.message.push.server.factories;

import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.yyx.message.push.server.strategies.FrameStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息类型处理器的工厂类
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/6 22:37
 */
public class FrameStrategyFactory {

    private static final Map<Class<? extends WebSocketFrame>, FrameStrategy> FRAME_STRATEGY_MAP = new HashMap<>();

    /**
     * 向工厂注册的方法
     *
     * @param webSocketFrame WebSocketFrame
     * @param frameStrategy  个体的策略
     */
    public static void register(Class<? extends WebSocketFrame> webSocketFrame, FrameStrategy frameStrategy) {
        FRAME_STRATEGY_MAP.put(webSocketFrame, frameStrategy);
    }

    /**
     * 建造策略对象的方法
     *
     * @param webSocketFrame webSocketFrame
     * @return 具体策略
     */
    public static FrameStrategy builder(Class<? extends WebSocketFrame> webSocketFrame) {
        return FRAME_STRATEGY_MAP.get(webSocketFrame);
    }
}
