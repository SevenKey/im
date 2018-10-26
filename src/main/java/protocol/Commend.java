package protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * 指令
 *
 * @author weijianyu
 */
public enum Commend {
    /**
     * 登录请求
     */
    LOGIN_REQUEST((byte) 1),
    /**
     * 登录返回
     */
    LOGIN_RESPONSE((byte) 2),;

    private byte code;
    private static final Map<Byte, Commend> COMMEND_MAPS = new HashMap<>();

    static {
        for (Commend commend : Commend.values()) {
            COMMEND_MAPS.put(commend.getCode(), commend);
        }
    }

    Commend(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public static Commend getEnumByCode(byte code) {
        return COMMEND_MAPS.get(code);
    }
}
