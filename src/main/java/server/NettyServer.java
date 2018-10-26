package server;

import handler.PacketDecodeHandler;
import handler.PacketEncodeHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Netty Server 启动程序
 *
 * @author weijianyu
 */
public class NettyServer {

    private final PacketDecodeHandler decodeHandler;
    private final PacketEncodeHandler encodeHandler;

    @Autowired
    public NettyServer(PacketDecodeHandler decodeHandler, PacketEncodeHandler encodeHandler) {
        this.decodeHandler = decodeHandler;
        this.encodeHandler = encodeHandler;
    }

    /**
     * netty server 启动
     */
    public void open() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(decodeHandler);
                        ch.pipeline().addLast(encodeHandler);
                    }
                });
        bindWithIncrease(serverBootstrap, 8000);
    }

    /**
     * 端口自增长绑定
     *
     * @param serverBootstrap netty server启动引导类
     * @param port            端口号
     */
    private void bindWithIncrease(ServerBootstrap serverBootstrap, int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("netty server open success port:" + port);
            } else {
                bindWithIncrease(serverBootstrap, port + 1);
            }
        });
    }
}
