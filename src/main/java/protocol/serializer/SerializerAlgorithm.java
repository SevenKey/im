package protocol.serializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weijianyu
 */
public enum SerializerAlgorithm {
    /**
     * json编码格式
     */
    JSON((byte) 1),;

    private byte code;
    private static final Map<Byte, SerializerAlgorithm> SERIALIZER_MAPS = new HashMap<>();

    static {
        for (SerializerAlgorithm serializer : SerializerAlgorithm.values()) {
            SERIALIZER_MAPS.put(serializer.getCode(), serializer);
        }
    }

    SerializerAlgorithm(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public static SerializerAlgorithm getSerializerAlgorithmByCode(byte code) {
        return SERIALIZER_MAPS.get(code);
    }
}
