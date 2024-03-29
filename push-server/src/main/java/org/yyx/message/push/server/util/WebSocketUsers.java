package org.yyx.message.push.server.util;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.internal.PlatformDependent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * WebSocket用户集
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

    private static final WebSocketUsers OUR_INSTANCE = new WebSocketUsers();

    private WebSocketUsers() {
    }

    public static WebSocketUsers getInstance() {
        return OUR_INSTANCE;
    }

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
     * @return 移除结果
     */
    public static boolean remove(Channel channel) {
        String key = null;
        boolean b = USERS.containsValue(channel);
        if (b) {
            key = getKeyByChannel(channel);
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
        Channel remove = USERS.remove(key);
        boolean containsValue = USERS.containsValue(remove);
        LOGGER.info("\n\t⌜⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓\n" + "\t├ [移出结果]: {}\n" + "\t⌞⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓", containsValue ? "失败"
                : "成功");
        return containsValue;
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
     * 群发消息
     *
     * @param message 消息内容
     */
    public static void sendMessageToUsers(String message) {
        Collection<Channel> values = USERS.values();
        for (Channel value : values) {
            value.write(new TextWebSocketFrame(message));
            value.flush();
        }
    }

    /**
     * 给某个人发送消息
     *
     * @param userName key
     * @param message  消息
     */
    public static void sendMessageToUser(String userName, String message) {
        Channel channel = USERS.get(userName);
        if (channel != null) {
            channel.write(new TextWebSocketFrame(message));
            channel.flush();
        }
    }

    /**
     * 通过channel获取用户
     *
     * @param channel 通道
     * @return 用户Key
     */
    public static String getUser(Channel channel) {
        String key = null;
        boolean b = USERS.containsValue(channel);
        if (b) {
            key = getKeyByChannel(channel);
        }
        return key;
    }

    /**
     * 通过Value找Key
     *
     * @param channel 通道
     * @return key
     */
    private static String getKeyByChannel(Channel channel) {
        Set<Map.Entry<String, Channel>> entries = USERS.entrySet();
        for (Map.Entry<String, Channel> entry : entries) {
            Channel value = entry.getValue();
            if (value.equals(channel)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 判断一个人是否在线
     *
     * @param key key
     * @return true 在线
     */
    public static boolean isOnline(String key) {
        Channel channel = USERS.get(key);
        return channel != null;
    }
}