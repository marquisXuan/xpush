package org.yyx.message.push.server.domain.enums;

/**
 * WebSocket命令枚举
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/7 00:11
 */
public enum WebsocketCmdEnum {

    /**
     * 聊天列表
     */
    get_chat_list,
    /**
     * 获取未读消息数
     */
    get_no_read_num,
    /**
     * 清除所有未读消息
     */
    clear_all_no_read,
    /**
     * 移除一个聊天
     */
    remove_one_chat,
    /**
     * 移除一条聊天记录
     */
    remove_one_chat_log,
    /**
     * 移除所有聊天记录
     */
    remove_all_chat_log,
    /**
     * 移除与某个人的所有聊天记录
     */
    remove_all_chat_log_whit_one_chat,
    ;

    WebsocketCmdEnum() {

    }
}
