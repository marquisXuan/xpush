package org.yyx.message.push.server.handler;

import cn.hutool.extra.spring.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.message.push.server.constant.Constant;
import org.yyx.message.push.server.context.FrameStrategyContext;
import org.yyx.message.push.server.domain.entity.WebSocketUserEntity;
import org.yyx.message.push.server.service.FirstHandshakeHandlerService;
import org.yyx.message.push.server.util.WebSocketUsers;

import java.io.IOException;

/**
 * WebSocketServer端处理器
 * <p>
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/6/29 - 上午9:07
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * WebSocketServerHandler 日志控制器
     * Create by 叶云轩 at 2018/5/11 上午11:50
     * Concat at tdg_yyx@foxmail.com
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServerHandler.class);

    /**
     * webSocketUrl
     */
    private final String webSocketUrl;

    /**
     * 用于打开关闭握手
     */
    private WebSocketServerHandshaker socketServerHandShaker;

    public WebSocketServerHandler(String webSocketUrl) {
        this.webSocketUrl = webSocketUrl;
    }

    /**
     * 异常
     *
     * @param channelHandlerContext channelHandlerContext
     * @param cause                 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        cause.printStackTrace();
        LOGGER.error("├ [服务端捕捉异常]: {}", cause.getMessage());
        channelHandlerContext.close();
    }

    /**
     * 当客户端主动链接服务端的链接后，调用此方法
     *
     * @param channelHandlerContext ChannelHandlerContext
     */
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) {
        LOGGER.info("├ 与客户端[{}]建立连接\n" + "├ [服务器当前在线人数]: {}", channelHandlerContext.channel().remoteAddress(), WebSocketUsers.getUSERS().size() + 1);
    }

    /**
     * 与客户端断开连接时 调用此方法
     *
     * @param channelHandlerContext channelHandlerContext
     */
    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) {
        Channel channel = channelHandlerContext.channel();
        LOGGER.info("├ 与客户端[{}]断开连接", channel.remoteAddress());
        // 从存储结构中移除通道，也可以用缓存来替代
        WebSocketUsers.remove(channel);
        // 关闭通道
        channel.close();
    }

    /**
     * 读完之后调用的方法
     *
     * @param channelHandlerContext ChannelHandlerContext
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.flush();
    }

    /**
     * 接收客户端发送的消息
     *
     * @param channelHandlerContext ChannelHandlerContext
     * @param receiveMessage        消息
     */
//    @Override
//    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object receiveMessage) throws Exception {
//        // 传统http接入 第一次需要使用http建立握手
//        if (receiveMessage instanceof FullHttpRequest) {
//            FullHttpRequest fullHttpRequest = (FullHttpRequest) receiveMessage;
//            LOGGER.info("├ [握手]: {}", fullHttpRequest.uri());
//            // 握手
//            handlerHttpRequest(channelHandlerContext, fullHttpRequest);
//            // 发送连接成功给客户端
//            channelHandlerContext.channel().write(new TextWebSocketFrame("连接成功"));
//        }
//        // WebSocket接入
//        else if (receiveMessage instanceof WebSocketFrame) {
//            WebSocketFrame webSocketFrame = (WebSocketFrame) receiveMessage;
//            handlerWebSocketFrame(channelHandlerContext, webSocketFrame);
//        }
//    }

    /**
     * 第一次握手
     *
     * @param channelHandlerContext channelHandlerContext
     * @param req                   请求
     */
    private void handlerHttpRequest(ChannelHandlerContext channelHandlerContext, FullHttpRequest req) {
        //"ws://localhost:9999/oa/websocket/{uri}"
        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(webSocketUrl, Constant.NULL, Constant.FALSE);
        // region 从连接路径中截取连接用户名
        String uri = req.uri();
        FirstHandshakeHandlerService handshakeHandlerService = SpringUtil.getBean(FirstHandshakeHandlerService.class);
        WebSocketUserEntity webSocketUserEntity = handshakeHandlerService.encapsulationUniqueId(uri);
        // endregion
        Channel connectChannel = channelHandlerContext.channel();
        // 加入在线用户
        WebSocketUsers.put(webSocketUserEntity.uniqueKey(), connectChannel);
        socketServerHandShaker = wsFactory.newHandshaker(req);
        if (socketServerHandShaker == null) {
            // 发送版本错误
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(connectChannel);
        } else {
            // 握手响应
            socketServerHandShaker.handshake(connectChannel, req);
        }
    }

    /**
     * webSocket处理逻辑
     *
     * @param channelHandlerContext channelHandlerContext
     * @param frame                 webSocketFrame
     */
    private void handlerWebSocketFrame(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) throws IOException {
        Channel channel = channelHandlerContext.channel();
        // region 判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            LOGGER.info("├ 关闭与客户端[{}]链接", channel.remoteAddress());
            socketServerHandShaker.close(channel, (CloseWebSocketFrame) frame.retain());
            return;
        }
        // endregion
        FrameStrategyContext.handlerWebSocketFrame(channel, frame);
    }

    /**
     * 接收客户端发送的消息
     *
     * @param channelHandlerContext ChannelHandlerContext
     * @param receiveMessage        消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object receiveMessage) throws Exception {
        // 传统http接入 第一次需要使用http建立握手
        if (receiveMessage instanceof FullHttpRequest) {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) receiveMessage;
            LOGGER.info("├ [握手]: {}", fullHttpRequest.uri());
            // 握手
            handlerHttpRequest(channelHandlerContext, fullHttpRequest);
            // 发送连接成功给客户端
            channelHandlerContext.channel().write(new TextWebSocketFrame("连接成功"));
        }
        // WebSocket接入
        else if (receiveMessage instanceof WebSocketFrame) {
            WebSocketFrame webSocketFrame = (WebSocketFrame) receiveMessage;
            handlerWebSocketFrame(channelHandlerContext, webSocketFrame);
        }
    }
}
