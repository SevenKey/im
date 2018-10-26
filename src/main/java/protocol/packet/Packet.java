package protocol.packet;

import lombok.Data;

/**
 * 通用封装包
 *
 * @author weijianyu
 */
@Data
public abstract class Packet {
    private byte version;

    public abstract byte getCommend();
}
