package protocol;

import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import protocol.packet.Packet;
import protocol.packet.PacketFactory;
import protocol.serializer.Serializer;
import protocol.serializer.SerializerFactory;

/**
 * @author weijianyu
 */
@Component
public class CommonDrive implements Drive {

    private final SerializerFactory serializerFactory;

    @Autowired
    public CommonDrive(SerializerFactory serializerFactory) {
        this.serializerFactory = serializerFactory;
    }


    @Override
    public byte[] encode(ByteBuf byteBuf, Packet packet) {
        byteBuf.writeInt();
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeInt();
        byteBuf.writeInt();
        byteBuf.writeInt();
        byteBuf.writeInt();
        byteBuf.writeInt();
        return new byte[0];
    }

    @Override
    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number 跳过版本号
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithmCode = byteBuf.readByte();
        // 指令
        byte commendCode = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();

        byte[] dataList = new byte[length];
        byteBuf.readBytes(dataList);


        Class<? extends Packet> packetClass = PacketFactory.getPacketByCommendCode(commendCode);
        Serializer serializer = serializerFactory.getSerializer(serializeAlgorithmCode);
        return serializer.deserialize(packetClass, dataList);
    }
}
