package protocol.serializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author weijianyu
 */
@Component
public class SerializerFactory {
    @Autowired
    private JsonSerializer jsonSerializer;

    public Serializer getSerializer(byte serializerCode) {
        switch (SerializerAlgorithm.getSerializerAlgorithmByCode(serializerCode)) {
            case JSON:
                return jsonSerializer;
            default:
                return null;
        }

    }
}
