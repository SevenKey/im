package protocol.packet;

import protocol.Commend;

/**
 * @author weijianyu
 */
public class PacketFactory {
    /**
     * 根据指令获取对应packet
     *
     * @param commendCode 指令code
     * @return 对应packet
     */
    public static Class<? extends Packet> getPacketByCommendCode(byte commendCode) {
        switch (Commend.getEnumByCode(commendCode)) {
            case LOGIN_REQUEST:
                return LoginPacketRequest.class;
            case LOGIN_RESPONSE:
                return LoginPacketResponse.class;
            default:
                return null;
        }
    }
}
