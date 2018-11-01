package annotation;

import lombok.Data;

/**
 * 测试类
 *
 * @author weijianyu
 */
@Data
@Description("I am class annotation")
public class MyAnnotationClass {
    @Description("I am fild annotation")
    private String name;

    @Description("I am method annotation")
    public String getMyName() {
        return name;
    }
}
