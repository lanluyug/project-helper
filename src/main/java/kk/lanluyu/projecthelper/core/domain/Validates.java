package kk.lanluyu.projecthelper.core.domain;

import java.util.Collection;
import java.util.Map;


/**
 * 校验辅助类
 * @author zzh
 */
public class Validates {

    private Validates(){
        throw new UnsupportedOperationException("不允许构建Validates对象");
    }

    public static void isTrue(final boolean expression, final String message) {
        if (!expression) {
            throw new CommonException(message);
        }
    }

    public static void notNull(final Object object, final String message) {
        if (object == null) {
            throw new CommonException(message);
        }
    }

    /**
     * 字符序列判空
     */
    public static <T extends CharSequence> void notEmpty(final T chars, final String message) {
        if (chars == null || chars.length() == 0) {
            throw new CommonException(message);
        }
    }

    /**
     * 数组判空
     */
    public static <T> void notEmpty(final T[] array, final String message) {
        if (array == null || array.length == 0) {
            throw new CommonException(message);
        }
    }

    /**
     * 集合判空
     */
    public static <T extends Collection<?>> void notEmpty(final T collection, final String message) {
        if (collection == null || collection.isEmpty()) {
            throw new CommonException(message);
        }
    }

    /**
     * map判空
     */
    public static <T extends Map<?, ?>> void notEmpty(final T map, final String message) {
        if (map == null || map.isEmpty()) {
            throw new CommonException(message);
        }
    }
}
