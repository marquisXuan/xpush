package org.yyx.message.push.push.client.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.internal.PlatformDependent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * WebSocket客户端用户集
 */
public class WebSocketUsers {

    /**
     * 用户集
     */
    private static final ConcurrentMap<String, Channel> USERS = PlatformDependent.newConcurrentHashMap();
    /**
     * WebSocketUsers 日志控制器
     * Create by 叶云轩 at 2018/5/15 下午5:41
     * Concat at tdg_yyx@foxmail.com
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketUsers.class);

    /**
     * 存储通道
     *
     * @param key     唯一键
     * @param channel 通道
     */
    public static void put(String key, Channel channel) {
        USERS.put(key, channel);
    }

    /**
     * 移除通道
     *
     * @param channel 通道
     *
     * @return 移除结果
     */
    public static boolean remove(Channel channel) {
        String key = null;
        boolean b = USERS.containsValue(channel);
        if (b) {
            Set<Map.Entry<String, Channel>> entries = USERS.entrySet();
            for (Map.Entry<String, Channel> entry : entries) {
                Channel value = entry.getValue();
                if (value.equals(channel)) {
                    key = entry.getKey();
                    break;
                }
            }
        } else {
            return true;
        }
        return remove(key);
    }

    /**
     * 移出通道
     *
     * @param key 键
     */
    public static boolean remove(String key) {
        LOGGER.info("\n\t⌜⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓\n" +
                "\t├ [移出用户]: {}\n" +
                "\t⌞⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓", key);
        Channel remove = USERS.remove(key);
        if (remove != null) {
            boolean containsValue = USERS.containsValue(remove);
            LOGGER.info("\n\t⌜⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓\n" +
                    "\t├ [移出结果]: {}\n" +
                    "\t⌞⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓", containsValue ? "失败" : "成功");
            return containsValue;
        } else
            return true;

    }

    /**
     * 获取在线用户列表
     *
     * @return 返回用户集合
     */
    public static ConcurrentMap<String, Channel> getUSERS() {
        return USERS;
    }

    /**
     * 群发消息 通过二进制的方式
     *
     * @param message 消息内容
     */
    public static void sendMessageToUsersByBinary(ByteBuf message) {
        Collection<Channel> values = USERS.values();
        for (Channel value : values) {
            value.write(new BinaryWebSocketFrame(message));
            value.flush();
        }
    }

    /**
     * 给某个人发送消息
     *
     * @param userName 当前客户端已经存储的用户key
     * @param message  消息
     */
    public static void sendMessageToUserByBinary(String userName, ByteBuf message) {
        Channel channel = USERS.get(userName);
        if (channel != null) {
            channel.write(new BinaryWebSocketFrame(message));
            channel.flush();
        } else {
            LOGGER.info("\n\t⌜⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓\n" +
                    "\t├ [你已离线]\n" +
                    "\t⌞⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓");
        }
    }

    /**
     * 发送文本消息
     *
     * @param userName 自己的用户名
     * @param message  消息内容
     */
    public static void sendMessageToUserByText(String userName, String message) {
        Channel channel = USERS.get(userName);
        if (channel != null) {
            channel.write(new TextWebSocketFrame(message));
            channel.flush();
        } else {
            LOGGER.info("\n\t⌜⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓\n" +
                    "\t├ [你已离线]\n" +
                    "\t⌞⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓");
        }
    }
}