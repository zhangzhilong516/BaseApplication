package com.hengchang.lib_base.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Date;
import java.text.SimpleDateFormat;

import androidx.core.content.PermissionChecker;

/**
 * @author zhangzhilong
 * @date 2019/8/20.
 * description：
 */
public class CrashUtils implements Thread.UncaughtExceptionHandler, Runnable {
    private static final String TAG = "CrashUtils";
    private static final CrashUtils sCrashHelper = new CrashUtils();

    public static void init(boolean isNeverDie) {
        sCrashHelper.initHandler(null);
        if (isNeverDie) {
            new Handler().post(sCrashHelper);
        }
    }

    public static void init(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        sCrashHelper.initHandler(uncaughtExceptionHandler);
    }

    public void initHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (uncaughtExceptionHandler != null) {
            Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        } else {
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        handleThreadException(t, ex);
    }

    public void exit() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    /**
     * 处理子线程异常
     */
    private void handleThreadException(Thread thread, Throwable ex) {
        if (ex == null) {
            return;
        }
        String exceptionMessage = getExceptionMessage(thread, ex);
        saveToFile(exceptionMessage);
        exit();
    }

    /**
     * 处理主线程异常
     */
    private void handleMainThreadException(Thread thread, Throwable ex) {
        if (ex == null) {
            return;
        }
        String exceptionMessage = getExceptionMessage(thread, ex);
        saveToFile(exceptionMessage);
    }

    @Override
    public void run() { // 主线程异常捕获
        while (true) {
            try {
                Looper.loop();
            } catch (Throwable e) {
                handleMainThreadException(Thread.currentThread(), e);
            }
        }
    }


    public static String getExceptionMessage(Thread thread, Throwable ex) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("------------------").append("App Crash").append("------------------------").append("\n");

        long currentTime = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(currentTime));
        stringBuilder.append("发生时间：").append(time).append("\n");
//        stringBuilder.append("用户UserId：").append(AccountManager.getInstance().getUserId()).append("\n");
//        stringBuilder.append("用户手机号：").append(AccountManager.getInstance().getPhone()).append("\n");
//        stringBuilder.append("APP版本：").append(AppUtils.getVersionName()).append("\n");
//        stringBuilder.append("设备IMEI：").append(DeviceUtils.getIMEI()).append("\n");
        stringBuilder.append("系统版本：").append("Android" + Build.VERSION.RELEASE).append("\n");
        stringBuilder.append("SDK版本：").append(Build.VERSION.SDK_INT).append("\n");
        stringBuilder.append("手机品牌").append(Build.MANUFACTURER).append("\n");
        stringBuilder.append("机型：").append(Build.MODEL).append("\n");
        stringBuilder.append("CPU类型：").append(Build.CPU_ABI).append("\n");
        stringBuilder.append("Thread：").append(thread.getName()).append("\n");
        stringBuilder.append("Error类型：").append(ex.getClass().getSimpleName()).append("\n");
        stringBuilder.append("Error信息：").append(ex.toString()).append("\n");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ex.printStackTrace(new PrintStream(baos));
            stringBuilder.append("Error详情：").append(baos.toString()).append("\n");
        } catch (Exception e) {
        } finally {
            IOUtils.close(baos);
        }
        return stringBuilder.toString();
    }

    private void saveToFile(String content) {
        try {
            String fileName = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss").format(System.currentTimeMillis()) + ".log";

            String filePath = AppUtils.getApp().getFilesDir() + File.separator + "Crash" + File.separator + fileName;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (PackageManager.PERMISSION_GRANTED == AppUtils.getApp().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    filePath = AppUtils.getApp().getExternalFilesDir("Crash").getAbsolutePath() + File.separator + fileName;
                }
            }
            File file = new File(filePath);
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(content.getBytes());
            outStream.close();

            LogUtils.e(TAG, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
