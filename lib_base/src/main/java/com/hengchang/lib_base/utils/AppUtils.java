package com.hengchang.lib_base.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * @author zhangzhilong
 * @date 2019/4/30.
 * description：
 */
public class AppUtils {
    private static Application application;

    public static void init(Application app) {
        application = app;
    }

    public static Application getApp() {
        if (application == null) {
            throw new NullPointerException("please init in Application");
        }
        return application;
    }

    /**
     * 获取当前应用程序名称
     */
    public static synchronized String getAppName() {
        try {
            PackageManager packageManager = getApp().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    getApp().getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return getApp().getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取当前应用的版本名称
     */
    public static synchronized String getVersionName() {
        try {
            PackageManager packageManager = getApp().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    getApp().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取当前应用的版本号
     */
    public static synchronized int getVersionCode() {
        try {
            PackageManager packageManager = getApp().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    getApp().getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取包名
     */
    public static String getPackageName() {
        try {
            PackageManager packageManager = getApp().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    getApp().getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String getProcessName(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return "";
    }


    /**
     * 检测手机是否装了某个应用
     */
    public static boolean isPackageAvailable(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
