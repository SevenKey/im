package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author weijianyu
 */
public class ReadAnnotationClass {
    public static void main(String[] args) {
        try {
            Class clazz = MyAnnotationClass.class;
            if (clazz.isAnnotationPresent(Description.class)) {
                Description classAnnotation = (Description) clazz.getAnnotation(Description.class);
                System.out.println(classAnnotation.value());
            }
            Method[] ms = clazz.getDeclaredMethods();
            for (Method m : ms) {
                if (m.isAnnotationPresent(Description.class)) {
                    Description methodAnnotation = m.getAnnotation(Description.class);
                    System.out.println(methodAnnotation.value());
                }
            }
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                if (f.isAnnotationPresent(Description.class)) {
                    Description fieldAnnotation = f.getAnnotation(Description.class);
                    System.out.println(fieldAnnotation.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
