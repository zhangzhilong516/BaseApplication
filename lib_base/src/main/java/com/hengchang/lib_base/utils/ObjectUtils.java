package com.hengchang.lib_base.utils;

import java.util.Collection;

/**
 * @author zhangzhilong
 * @date 2019/4/28.
 * description：
 */
public class ObjectUtils {
    public static boolean isEmpty(Collection collection){
        return collection == null ? true : collection.isEmpty();
    }

    public static boolean isNull(Object obj) {
        return null == obj;
    }
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }
}
