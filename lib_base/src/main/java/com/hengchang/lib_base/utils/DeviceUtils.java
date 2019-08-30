package com.hengchang.lib_base.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

/**
 * @author zhangzhilong
 * @date 2019/4/23.
 * description：
 */
public class DeviceUtils {
    /**
     * 获取设备的IMEI号
     */
    public static String getIMEI() {
        if (ActivityCompat.checkSelfPermission(AppUtils.getApp(), Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            LogUtils.print("获取IMEI权限拒绝");
            return "00000000000000";
        }
        TelephonyManager telephonyMgr = (TelephonyManager) AppUtils.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyMgr.getDeviceId();
    }

    /**
     * 获取设备品牌
     * @return
     */
    public static String getBrand(){
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机的型号 设备名称。如：SM-N9100（三星Note4）
     * @return
     */
    public static String getModel(){
        return android.os.Build.MODEL;
    }

    /**
     * 获取设备制造商。如：samsung
     */
    public static String getManufacturer(){
        return android.os.Build.MANUFACTURER;
    }


    /**
     * 整个产品的名称
     */
    public static String getProduct(){
        return android.os.Build.PRODUCT;
    }
    /**
     * 获取系统版本字符串。如4.1.2
     */
    public static String getVersionRelease(){
        return "Android_" + android.os.Build.VERSION.RELEASE;
    }
}
