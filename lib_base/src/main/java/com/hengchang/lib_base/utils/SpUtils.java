package com.hengchang.lib_base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author zhangzhilong
 * @date 2019/8/29.
 * description：
 */
public final class SpUtils {

    private static final String SP_NAME = "default_preferences";

    public static SharedPreferences getSharedPreferences() {
        return getSharedPreferences(SP_NAME);
    }

    public static SharedPreferences getSharedPreferences(String spName) {
        return AppUtils.getApp().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(SP_NAME, key);
    }

    public static boolean getBoolean(String spName, String key) {
        return getBoolean(spName, key, false);
    }

    public static boolean getBoolean(String spName, String key, boolean defValue) {
        return getSharedPreferences(spName).getBoolean(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        putBoolean(SP_NAME, key, value);
    }

    public static void putBoolean(String spName, String key, boolean value) {
        getSharedPreferences(spName).edit().putBoolean(key, value).commit();
    }

    public static int getInt(String key) {
        return getInt(SP_NAME, key);
    }

    public static int getInt(String spName, String key) {
        return getInt(spName, key, 0);
    }

    public static int getInt(String spName, String key, int defValue) {
        return getSharedPreferences(spName).getInt(key, 0);
    }

    public static void putInt(String key, int value) {
        putInt(SP_NAME, key, value);
    }

    public static void putInt(String spName, String key, int value) {
        getSharedPreferences(spName).edit().putInt(key, value).commit();
    }

    public static long getLong(String key) {
        return getLong(SP_NAME, key);
    }

    public static long getLong(String spName, String key) {
        return getLong(spName, key, 0);
    }

    public static long getLong(String spName, String key, int defValue) {
        return getSharedPreferences(spName).getLong(key, 0);
    }

    public static void putLong(String key, int value) {
        putLong(SP_NAME, key, value);
    }

    public static void putLong(String spName, String key, int value) {
        getSharedPreferences(spName).edit().putLong(key, value).commit();
    }


    public static float getFloat(String key) {
        return getFloat(SP_NAME, key);
    }

    public static float getFloat(String spName, String key) {
        return getFloat(spName, key, 0);
    }

    public static float getFloat(String spName, String key, float defValue) {
        return getSharedPreferences(spName).getFloat(key, 0);
    }

    public static void putFloat(String key, float value) {
        putFloat(SP_NAME, key, value);
    }

    public static void putFloat(String spName, String key, float value) {
        getSharedPreferences(spName).edit().putFloat(key, value).commit();
    }


    public static String getString(String key) {
        return getString(SP_NAME, key);
    }

    public static String getString(String spName, String key) {
        return getString(spName, key, "");
    }

    public static String getString(String spName, String key, String defValue) {
        return getSharedPreferences(spName).getString(key, defValue);
    }

    public static void putString(String key, String value) {
        putString(SP_NAME, key, value);
    }

    public static void putString(String spName, String key, String value) {
        getSharedPreferences(spName).edit().putString(key, value).commit();
    }

    public static void saveObject(Context context, String key, Object object) {
        saveObject(SP_NAME, key, object);
    }

    public static void saveObject(String spName, String key, Object object) {
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
        ) {

            oos.writeObject(object);
            String objectStr = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            putString(spName, key, objectStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T readObject(String key) {
        return (T) readObject(SP_NAME, key);
    }

    public static <T> T readObject(String spName, String key) {
        Object object = null;
        String string = getString(spName, key);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        byte[] base64 = Base64.decode(string.getBytes(), Base64.DEFAULT);
        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(base64);
                ObjectInputStream bis = new ObjectInputStream(bais);
        ) {
            object = bis.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) object;
    }


    public static void remove(String key) {
        remove(SP_NAME, key);
    }

    public static void remove(String spName, String key) {
        getSharedPreferences(SP_NAME).edit().remove(key).commit();
    }

    public static void clear(String spName) {
        getSharedPreferences(spName).edit().clear().commit();
    }
}
