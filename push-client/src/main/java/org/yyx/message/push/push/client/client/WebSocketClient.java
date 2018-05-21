package org.yyx.message.push.push.client.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yyx.message.push.push.client.config.WebSocketConfig;
import org.yyx.message.push.push.client.handler.WebSocketClientHandler;
import org.yyx.message.push.push.client.handler.WebSocketHandlerClient;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WebSocket客户端
 * <p>
 * create by 叶云轩 at 2018/5/21-下午1:43
 * contact by tdg_yyx@foxmail.com
 */
@Component
public class WebSocketClient {
    /**
     * WebSocketClient 日志控制器
     * Create by 叶云轩 at 2018/5/21 下午6:09
     * Concat at tdg_yyx@foxmail.com
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketClient.class);

    private Bootstrap bootstrap;
    /**
     * Work线程组
     */
    private EventLoopGroup worker;

    /**
     * 线程池
     */
    private ExecutorService executorService = Executors.newFixedThreadPool(3);

    /**
     * WebSocket客户端配置类
     */
    @Resource
    private WebSocketConfig webSocketConfig;


    public WebSocketClient() {
        bootstrap = new Bootstrap();
        worker = new NioEventLoopGroup();
        bootstrap.group(worker);
        bootstrap.channel(NioSocketChannel.class);
    }

    public void run(String host, int port) {
        executorService.submit(() -> {
            // 配置客户端的NIO线程组
            EventLoopGroup clientGroup = new NioEventLoopGroup();
            try {
                WebSocketClientHandler webSocketClientHandler = new WebSocketClientHandler(
                        WebSocketClientHandshakerFactory.newHandshaker(new URI("ws://" + webSocketConfig.getHost() + ":" + webSocketConfig.getPort()
                                        + "/" + webSocketConfig.getUrl()
                                        + "/" + webSocketConfig.getUserName())
                                , WebSocketVersion.V13
                                , null
                                , false
                                , new DefaultHttpHeaders()));
                // 配置bootstrap
                bootstrap.handler(
                        new WebSocketHandlerClient(webSocketClientHandler));
                // 发起异步连接操作  同步方法待成功
                Channel channel = bootstrap.connect(host, port).sync().channel();
                // 等待客户端链路关闭
                channel.closeFuture().sync();
            } catch (InterruptedException | URISyntaxException e) {
                e.printStackTrace();
            } finally {
                // 优雅退出，释放NIO线程组
                clientGroup.shutdownGracefully();
            }
        });
    }

    /**
     * 关闭与netty服务器的连接
     */
    @PreDestroy
    public void close() {
        LOGGER.info("\n\t⌜⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓\n" +
                "\t├ [关闭链接]\n" +
                "\t⌞⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓⎓");
        worker.shutdownGracefully();
    }
}