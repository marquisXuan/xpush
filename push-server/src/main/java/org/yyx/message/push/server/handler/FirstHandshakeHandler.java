package org.yyx.message.push.server.handler;

import org.yyx.message.push.server.domain.entity.WebSocketUserEntity;
import org.yyx.message.push.server.service.FirstHandshakeHandlerService;

/**
 * 第一次握手的处理业务
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/7 21:07
 */
public class FirstHandshakeHandler implements FirstHandshakeHandlerService {
    @Override
    public WebSocketUserEntity encapsulationUniqueId(String requestUri) {
        int i = requestUri.lastIndexOf("/");
        String uniqueKey = requestUri.substring(i + 1, requestUri.length());
        return new WebSocketUserEntity(uniqueKey);
    }
}
