package protocol.serializer;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

/**
 * json编码方式
 *
 * @author weijianyu
 */
@Component
public class JsonSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON.getCode();
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
