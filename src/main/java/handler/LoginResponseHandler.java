package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.packet.LoginPacketRequest;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponseHandler> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponseHandler msg) throws Exception {

    }
}
