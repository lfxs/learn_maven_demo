package org.apache.demo2.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 操作数组集合工具
 */
public class ArrayUtil {
    /**
     *
     * 判断数值是否为非空
     */
    public static boolean isNotEmpty(Object[] array){
        return !ArrayUtils.isEmpty(array);
    }

    /**
     *
     * 判断数值是否空
     */
    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }
}
