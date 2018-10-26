package protocol;

import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import protocol.packet.Packet;
import protocol.packet.PacketFactory;
import protocol.serializer.JsonSerializer;
import protocol.serializer.Serializer;
import protocol.serializer.SerializerAlgorithm;
import protocol.serializer.SerializerFactory;

/**
 * @author weijianyu
 */
@Component
public class CommonDrive implements Drive {

    private static final int MAGIC_NUMBER = 0X12345678;

    private final SerializerFactory serializerFactory;
    private final PacketFactory packetFactory;
    private final JsonSerializer jsonSerializer;

    @Autowired
    public CommonDrive(SerializerFactory serializerFactory,PacketFactory packetFactory,
                       JsonSerializer jsonSerializer) {
        this.serializerFactory = serializerFactory;
        this.packetFactory = packetFactory;
        this.jsonSerializer = jsonSerializer;
    }


    @Override
    public void encode(ByteBuf byteBuf, Packet packet) {
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(jsonSerializer.getSerializerAlgorithm());
        byte[] dataList = jsonSerializer.serialize(packet);
        byteBuf.writeInt(dataList.length);
        byteBuf.writeBytes(dataList);
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


        Class<? extends Packet> packetClass = packetFactory.getPacketByCommendCode(commendCode);
        Serializer serializer = serializerFactory.getSerializer(serializeAlgorithmCode);
        return serializer.deserialize(packetClass, dataList);
    }
}
