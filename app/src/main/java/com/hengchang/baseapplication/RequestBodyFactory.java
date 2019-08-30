package com.hengchang.baseapplication;

import com.alibaba.fastjson.JSONObject;
import com.hengchang.lib_base.utils.AES;
import com.hengchang.lib_base.utils.LogUtils;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author zhangzhilong
 * @date 2019/4/30.
 * description：
 */
public class RequestBodyFactory {
    private static final MediaType MEDIA_TYPE = MediaType.get("application/json");
    private static final String SYS_NAME = "cashloan";
    private static final String API_VER = "v1";


    /**
     * 创建RequestBody
     */
    public static RequestBody create(String apiName, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sysName", SYS_NAME);
        jsonObject.put("apiName", apiName);
        jsonObject.put("apiVer", API_VER);
        jsonObject.put("data", data == null ? null : data);

        String jsonParam = JSONObject.toJSONString(jsonObject);

        LogUtils.json("OkHttp","RequestBody : " + jsonParam);
        return RequestBody.create(MEDIA_TYPE, AES.encrypt(jsonParam));
    }


    /**
     * 创建RequestBody
     */
    public static RequestBody create(String apiName) {
        return create(apiName, null);
    }

    /**
     * 创建入参Body
     */
    public static ParamBody createBody(String apiName, Object params) {
        return new ParamBody(SYS_NAME, apiName, API_VER, params);
    }

    /**
     * 请求参数Body
     */
    public static class ParamBody {
        private String sysName;
        private String apiName;
        private String apiVer;
        private Object data;

        public ParamBody(String sysName, String apiName, String apiVer, Object data) {
            this.sysName = sysName;
            this.apiName = apiName;
            this.apiVer = apiVer;
            this.data = data;
        }

        public String getSysName() {
            return sysName;
        }

        public void setSysName(String sysName) {
            this.sysName = sysName;
        }

        public String getApiName() {
            return apiName;
        }

        public void setApiName(String apiName) {
            this.apiName = apiName;
        }

        public String getApiVer() {
            return apiVer;
        }

        public void setApiVer(String apiVer) {
            this.apiVer = apiVer;
        }

        public Object getData() {
            return data;
        }

        public void setData(Map<String, Object> data) {
            this.data = data;
        }
    }
}
