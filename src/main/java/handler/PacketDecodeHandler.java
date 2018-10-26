package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import protocol.CommonDrive;

import java.util.List;

public class PacketDecodeHandler extends ByteToMessageDecoder {

    private final CommonDrive drive;

    @Autowired
    public PacketDecodeHandler(CommonDrive commonDrive) {
        this.drive = commonDrive;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(drive.decode(in));
    }
}
