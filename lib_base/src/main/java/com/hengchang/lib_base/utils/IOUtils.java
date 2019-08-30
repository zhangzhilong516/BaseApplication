package com.hengchang.lib_base.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author zhangzhilong
 * @date 2019/7/31.
 * description：
 */
public class IOUtils {

    public static void close(Closeable closeable){
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
