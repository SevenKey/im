package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * Netty Client 启动服务
 *
 * @author weijianyu
 */
public class NettyClient {
    private static final int MAX_COUNT = 5;

    /**
     * netty client 启动
     */
    public static void open() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {

                    }
                });
        connectWithRetry(bootstrap, "127.0.0.1", 8000, MAX_COUNT);
    }

    /**
     * 自动重连
     *
     * @param bootstrap netty client 启动引导类
     * @param ip        ip
     * @param port      端口号
     * @param count     重试次数
     */
    private static void connectWithRetry(Bootstrap bootstrap, String ip, int port, int count) {
        bootstrap.connect(ip, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("netty client connect success ip:" + ip + " port:" + port);
            } else {
                if (count == MAX_COUNT) {
                    System.out.println("netty client connect fail ip:" + ip + " port:" + port);
                }
                int current = MAX_COUNT - count + 1;
                int delay = 1 << current;
                bootstrap.config().group().schedule(() -> connectWithRetry(bootstrap, ip, port, count - 1), delay, TimeUnit.SECONDS);
            }
        });
    }
}
