package protocol;

import io.netty.buffer.ByteBuf;
import protocol.packet.Packet;

/**
 * @author weijianyu
 */
public interface Drive {

    /**
     * @param byteBuf
     * @param packet
     * @return
     */
    byte[] encode(ByteBuf byteBuf, Packet packet);

    /**
     * @param byteBuf
     * @return
     */
    Packet decode(ByteBuf byteBuf);
}
