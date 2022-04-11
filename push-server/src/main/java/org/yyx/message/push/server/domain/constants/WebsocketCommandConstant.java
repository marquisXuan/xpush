package org.yyx.message.push.server.domain.constants;

/**
 * 命令常量
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/8 10:50
 */
public class WebsocketCommandConstant {
    /**
     * 命令 获取聊天列表
     */
    public static final String GET_CHAT_LIST = "GET_CHAT_LIST";
    /**
     * 命令 获取未读消息数量
     */
    public static final String GET_NO_READ_NUMBER = "GET_NO_READ_NUMBER";
    /**
     * 命令 清除所有未读消息
     */
    public static final String CLEAR_ALL_NO_READ = "CLEAR_ALL_NO_READ";
    /**
     * 命令 移除一个聊天
     */
    public static final String REMOVE_ONE_CHAT = "REMOVE_ONE_CHAT";
    /**
     * 移除一条聊天记录
     */
    public static final String REMOVE_ONE_CHAT_LOG = "REMOVE_ONE_CHAT_LOG";
    /**
     * 移除和某人的所有聊天记录
     */
    public static final String REMOVE_ALL_CHAT_LOG_WITH_ONE = "REMOVE_ALL_CHAT_LOG_WITH_ONE";
    /**
     * 与某人聊天的命令
     */
    public static final String CHAT_WITH_ONE = "CHAT_WITH_ONE";
    /**
     * 获取与某人聊天记录的命令
     */
    public static final String GET_CHAT_LOG = "GET_CHAT_LOG";
}
