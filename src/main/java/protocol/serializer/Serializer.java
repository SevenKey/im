package protocol.serializer;

/**
 * @author weijianyu
 */
public interface Serializer {

    /**
     * 获取序列化算法
     *
     * @return 算法 code
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     *
     * @param object java对象
     * @return 二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     *
     * @param clazz java对象
     * @param bytes 二进制
     * @param <T>   泛型
     * @return java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
