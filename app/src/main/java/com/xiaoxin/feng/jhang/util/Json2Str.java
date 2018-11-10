package com.xiaoxin.feng.jhang.util;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author: xiaoxin
 * date: 2018/8/18
 * describe:
 * 修改内容:
 */

public class Json2Str {

    /**
     * 构造钉钉消息格式
     * @param message 内容
     * @return
     */
    public static String messageStr(String message) {
        if(TextUtils.isEmpty(message)) {
            throw new IllegalArgumentException("message should not be blank");
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("msgtype", "text");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("content", message);
            jsonObject.put("text", jsonObject1.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 构造钉钉消息格式
     * @param message 内容
     * @return
     */
    public static String messageStr(String message,String phone) {
        if(TextUtils.isEmpty(message)) {
            throw new IllegalArgumentException("message should not be blank");
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("msgtype", "text");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("content", message);
            jsonObject.put("text", jsonObject1.toString());

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(phone);
            JSONObject atJson = new JSONObject();
            atJson.put("atMobiles",jsonArray);
            atJson.put("isAtAll",false);
            jsonObject.put("at",atJson);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
