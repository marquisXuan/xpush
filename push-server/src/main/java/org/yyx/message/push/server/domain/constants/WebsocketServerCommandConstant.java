package org.yyx.message.push.server.domain.constants;

/**
 * 服务器返回客户端的命令常量
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/12 01:46
 */
public class WebsocketServerCommandConstant {
    /**
     * 响应 获取聊天列表的命令
     */
    public static final String RESPONSE_GET_CHAT_LIST = "RESPONSE_GET_CHAT_LIST";
    /**
     * 响应 删除一个聊天对象的命令
     */
    public static final String RESPONSE_REMOVE_CHAT_ITEM = "RESPONSE_REMOVE_CHAT_ITEM";
    /**
     * 响应 查询历史聊天记录的命令
     */
    public static final String RESPONSE_QUERY_CHAT_HISTORY = "RESPONSE_QUERY_CHAT_HISTORY";
    /**
     * 响应 发送者发送消息的响应
     */
    public static final String RESPONSE_SEND_MESSAGE_TO_OTHER = "RESPONSE_SEND_MESSAGE_TO_OTHER";
    /**
     * 响应 发送者发送消息给接收者
     */
    public static final String RESPONSE_RECEIVE_MESSAGE_FROM_OTHER = "RESPONSE_RECEIVE_MESSAGE_FROM_OTHER";
    /**
     * 伪心跳响应PONG
     */
    public static final String RESPONSE_PONG = "RESPONSE_PONG";
}
