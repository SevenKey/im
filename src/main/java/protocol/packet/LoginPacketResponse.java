package protocol.packet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import protocol.Commend;

/**
 * 登录请求
 *
 * @author weijianyu
 */
@Setter
@Getter
@ToString
public class LoginPacketResponse extends Packet {

    /**
     * 登录成功态
     */
    private boolean success;

    /**
     * userId
     */
    private String userId;

    /**
     * message
     */
    private String message;

    @Override
    public byte getCommend() {
        return Commend.LOGIN_RESPONSE.getCode();
    }
}
