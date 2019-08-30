package com.hengchang.lib_base.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author zhangzhilong
 * @date 2019/8/29.
 * description：
 */
public class ReflectUtils {

    /**
     * 获取类泛型参数
     * @param obj
     * @param index
     * @param <T>
     */
    public static <T> T getActualTypeArguments(Object obj, int index) {
        try {
            Type[] params = ((ParameterizedType) obj.getClass().getGenericSuperclass())
                    .getActualTypeArguments();
            return (T) ((Class) params[index]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
