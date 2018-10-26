package handler;

import org.springframework.beans.factory.annotation.Autowired;
import protocol.CommonDrive;
import protocol.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncodeHandler extends MessageToByteEncoder<Packet> {

    private final CommonDrive drive;

    @Autowired
    public PacketEncodeHandler(CommonDrive commonDrive) {
        this.drive = commonDrive;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        drive.encode(out, msg);
    }
}
