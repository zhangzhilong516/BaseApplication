package com.hengchang.lib_base.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.hengchang.lib_base.utils.DensityUtils;

/**
 * @author zhangzhilong
 * @date 2019/8/29.
 * description：
 */
public class DefaultActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks
{
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ActivityManager.push(activity);
        DensityUtils.setCompatDensity(activity, activity.getApplication());
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ActivityManager.pop(activity);
    }
}
