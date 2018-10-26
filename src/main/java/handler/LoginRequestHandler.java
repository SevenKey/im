package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.packet.LoginPacketRequest;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginPacketRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginPacketRequest msg) throws Exception {

    }
}
