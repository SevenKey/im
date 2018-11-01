package version;

import com.meituan.train.braveheart.util.VersionControlUtil;

/**
 * @author weijianyu
 */
public class VersionUpdate {
    /**
     * 大于
     */
    private static final int GT = 1;
    /**
     * 小于
     */
    private static final int LT = -1;
    /**
     * 等于
     */
    private static final int ET = 0;

    /**
     * source > target
     *
     * @param source 源版本号
     * @param target 目标版本号
     * @return source > target?true:false
     */
    public static boolean greaterThan(String source, String target) {
        return compare(source, target) == GT;
    }

    /**
     * source < target
     *
     * @param source 源版本号
     * @param target 目标版本号
     * @return source < target?true:false
     */
    public static boolean lessThan(String source, String target) {
        return compare(source, target) == LT;
    }


    /**
     * source == target
     *
     * @param source 源版本号
     * @param target 目标版本号
     * @return source == target
     */
    public static boolean equalTo(String source, String target) {
        return compare(source, target) == ET;
    }

    /**
     * source >= target
     *
     * @param source 源版本号
     * @param target 目标版本号
     * @return source >= target?true:false
     */
    public static boolean greaterThanOrEqual(String source, String target) {
        return compare(source, target) != LT;
    }

    /**
     * source <= target
     *
     * @param source 源版本号
     * @param target 目标版本号
     * @return source <= target?true:false
     */
    public static boolean lessThanOrEqual(String source, String target) {
        return compare(source, target) != GT;
    }

    /**
     * 期间 不包含
     *
     * @param source 源版本
     * @param start  开始
     * @param end    结束
     * @return true/false
     */
    public static boolean between(String source, String start, String end) {
        return greaterThan(source, start) && lessThan(source, end);
    }

    /**
     * 期间 包含
     *
     * @param source 源版本
     * @param start  开始
     * @param end    结束
     * @return true/false
     */
    public static boolean betweenEqual(String source, String start, String end) {
        return greaterThanOrEqual(source, start) && lessThanOrEqual(source, end);
    }

    /**
     * 对比 source和target大小
     *
     * @param source 源版本
     * @param target 目标版本
     * @return source和target大小 1,0,-1
     */
    private static int compare(String source, String target) {
        return VersionControlUtil.compareVersion(source, target);
    }

    public static void main(String[] args) {
        System.out.println(compare("9.1.0", "9.1"));
        System.out.println(compare("9.1", "9.1.0"));
        System.out.println(compare("9.2.0", "9.1.0"));
        System.out.println(compare("9.3", "9.1.5"));
        System.out.println(compare("9.1", "9.1.6"));
        System.out.println(compare("10.1.115", "9.1.6"));
    }
}
