package com.hengchang.lib_base.net.http.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author zhangzhilong
 * @date 2019/8/29.
 * description：
 */
public class HttpLogger {
    private static final String TAG = "HttpLogger";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final char TOP_LEFT_CORNER = '┌';
    private static final char BOTTOM_LEFT_CORNER = '└';
    private static final char HORIZONTAL_DOUBLE_LINE = '│';
    private static final String DOUBLE_DIVIDER = "────────────────────────────────────────────────────────";

    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER
            + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER
            + DOUBLE_DIVIDER + DOUBLE_DIVIDER;

    private HttpLogger() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    public static void print(Object message) {
        print(Log.DEBUG, TAG, message);
    }

    public static void d(String tag, Object message) {
        print(Log.DEBUG, tag, message);
    }

    public static void e(String tag, Object message) {
        print(Log.ERROR, tag, message);
    }

    public static void w(String tag, Object message) {
        print(Log.WARN, tag, message);
    }

    public static void i(String tag, Object message) {
        print(Log.INFO, tag, message);
    }

    public static void v(String tag, Object message) {
        print(Log.VERBOSE, tag, message);
    }

    public static void wtf(String tag, Object message) {
        print(Log.ASSERT, tag, message);
    }

    public static String formatJson(String jsonStr) {
        try {
            String result = "";
            if (jsonStr.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(jsonStr);
                result = jsonObject.toString(4);
            } else if (jsonStr.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(jsonStr);
                result = jsonArray.toString(4);
            } else {
                result = jsonStr;
            }
            String[] lines = result.split(LINE_SEPARATOR);
            StringBuilder stringBuilder = new StringBuilder();
            for (String line : lines) {
                stringBuilder.append(HORIZONTAL_DOUBLE_LINE + line).append("\n");
            }
            return stringBuilder.toString();
        } catch (JSONException e) {
            return jsonStr;
        }
    }

    private static void print(int logType, String tag, Object obj) {
        log(logType, tag, TOP_BORDER);
        String[] lines = obj.toString().split(LINE_SEPARATOR);
        for (String line : lines) {
            log(logType, tag, HORIZONTAL_DOUBLE_LINE + " " + line);
        }
        log(logType, tag, BOTTOM_BORDER);
    }

    /**
     * 输出Log到控制台
     *
     * @param logType
     * @param tag
     * @param msg
     */
    private static void log(int logType, String tag, String msg) {
        switch (logType) {
            case Log.VERBOSE:
                Log.v(tag, msg);
                break;
            case Log.DEBUG:
                Log.d(tag, msg);
                break;
            case Log.INFO:
                Log.i(tag, msg);
                break;
            case Log.WARN:
                Log.w(tag, msg);
                break;
            case Log.ERROR:
                Log.e(tag, msg);
                break;
            case Log.ASSERT:
                Log.wtf(tag, msg);
                break;
            default:
                Log.d(tag, msg);
                break;
        }
    }
}
