package org.yyx.message.push.server.service;

import org.yyx.message.push.server.domain.entity.WebSocketUserEntity;

/**
 * 第一次握手的处理业务接口
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/7 21:00
 */
public interface FirstHandshakeHandlerService {

    /**
     * 用于处理从请求连接中封装的用户信息
     *
     * @param requestUri 请求URI
     * @return 封装用户的实体对象
     */
    WebSocketUserEntity encapsulationUniqueId(String requestUri);
}
