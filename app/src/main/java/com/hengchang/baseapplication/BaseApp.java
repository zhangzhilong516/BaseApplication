package com.hengchang.baseapplication;

import android.app.Application;

import com.hengchang.lib_base.utils.AppUtils;
import com.hengchang.lib_base.utils.CrashUtils;

/**
 * @author zhangzhilong
 * @date 2019/8/28.
 * description：
 */
public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
        CrashUtils.init(true);
    }
}
