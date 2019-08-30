package com.hengchang.lib_base.common;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * @author zhangzhilong
 * @date 2019/8/21.
 * description：
 */
public final class ActivityManager {

    private static final Stack<Activity> sActivityStack = new Stack<>();// Activity栈

    /**
     * 移除所有activity
     *
     * @Description:
     */
    public static void popAll() {
        while (!sActivityStack.isEmpty()) {
            pop();
        }
    }

    public static int stackSize() {
        return sActivityStack.size();
    }

    /**
     * 移除位于栈顶的activity
     *
     * @Description:
     */
    public static void pop() {
        Activity activity = sActivityStack.pop();
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }


    public static <T extends Activity> boolean isContain(Class<T> tClass) {
        for (Activity a : sActivityStack) {
            if (a.getClass().equals(tClass)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 移除指定activity
     *
     * @param activity
     * @Description:
     */
    public static void pop(Activity activity) {
        sActivityStack.remove(activity);
        if (!activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * 移除并关闭指定某一类的activity
     *
     * @param cls
     * @Description:
     */
    public static void popClass(Class<? extends Activity> cls) {
        for (Activity a : sActivityStack) {
            if (a.getClass().equals(cls)) {
                if (!a.isFinishing()) {
                    sActivityStack.remove(a);
                    a.finish();
                    break;
                }
            }
        }
    }

    /**
     * 获取在栈顶的activity
     *
     * @return
     * @Description:
     */
    public static Activity current() {
        if (sActivityStack.isEmpty()) {
            return null;
        }
        return sActivityStack.peek();
    }

    /**
     * 添加activity到栈中
     *
     * @param activity
     * @Description:
     */
    public static void push(Activity activity) {
        sActivityStack.push(activity);
    }

    /**
     * 保留某一类的activity，其它的都关闭并移出栈
     *
     * @param cls
     * @Description:
     */
    public static void retain(Class<? extends Activity> cls) {
        int size = sActivityStack.size();
        for (int i = 0; i < size ; i++) {
            Activity activity = sActivityStack.get(i);
            if(!activity.getClass().equals(cls)){
                activity.finish();
                size--;
                i--;
            }
        }
    }


    /**
     * 退出应用程序
     */
    public void exitApp(Context context) {
        popAll();
        android.os.Process.killProcess(android.os.Process.myPid());
        Runtime.getRuntime().exit(0);
    }
}
