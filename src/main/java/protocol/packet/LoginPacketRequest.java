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
public class LoginPacketRequest extends Packet {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    @Override
    public byte getCommend() {
        return Commend.LOGIN_REQUEST.getCode();
    }

}
