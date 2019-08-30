package com.hengchang.lib_base.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;


/**
 * @author zhangzhilong
 * @date 2019/6/25.
 * description：今日头条屏幕适配方案
 * https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
 */
public class DensityUtils {
    private static float sOriginalDensity;
    private static float sOriginalScaledDensity;
    private static int sOriginalDensityDpi;
    private static final int sTargetWidthDp = 360;
    private static float sCompatDensity;
    private static float sCompatScaledDensity;

    public static void init(Application app) {
        final DisplayMetrics appDisplayMetrics = app.getResources().getDisplayMetrics();
        sOriginalDensity = appDisplayMetrics.density;
        sOriginalScaledDensity = appDisplayMetrics.scaledDensity;
        sOriginalDensityDpi = appDisplayMetrics.densityDpi;
    }

    /**
     * 今日头条适配
     *
     * @param activity
     * @param app
     */
    public static void setCompatDensity(Activity activity, Application app) {
        final Application application = app;
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (sCompatDensity == 0) {
            sCompatDensity = appDisplayMetrics.density;
            sCompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sCompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }
        final float targetDensity = appDisplayMetrics.widthPixels / sTargetWidthDp;
        final float targetScaleDensity = targetDensity * (sCompatScaledDensity / sCompatDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaleDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;


        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

    /**
     * 还原原始Density
     *
     * @param application
     */
    public static void setOriginalDensity(Application application) {
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        appDisplayMetrics.density = sOriginalDensity;
        appDisplayMetrics.scaledDensity = sOriginalScaledDensity;
        appDisplayMetrics.densityDpi = sOriginalDensityDpi;
    }


}
