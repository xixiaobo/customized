package com.swj.customized.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xxb on 2018/11/19 11:31
 */
@Slf4j
public class JSONTool {
    public static JSONArray ListToJSONArray(List list){
        return JSONArray.parseArray(JSON.toJSONString(list));
    }

    public static JSONObject ObjectToJSONObject(Object u){
        return JSON.parseObject(JSON.toJSONString(u));
    }

    public static JSONArray sortJsonArraybyValue(JSONArray array, String KEY_NAME, boolean sorttype) {
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < array.size(); i++) {
            jsonValues.add(array.getJSONObject(i));
        }
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();
                try {
                    valA = a.getString(KEY_NAME);
                    valB = b.getString(KEY_NAME);

                } catch (JSONException e) {

                }
                if (sorttype == true) {
                    return valA.compareTo(valB);
                } else {
                    return -valA.compareTo(valB);
                }
            }
        });

        for (int i = 0; i < jsonValues.size(); i++) {
            sortedJsonArray.add(jsonValues.get(i));
        }
        return sortedJsonArray;
    }

    public static JSONArray sortJsonArraybyObjectValue(JSONArray array, String object_name, String KEY_NAME, boolean sorttype) {
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < array.size(); i++) {
            jsonValues.add(array.getJSONObject(i));
        }
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject a, JSONObject b) {
                Double valA = 0.0;
                Double valB = 0.0;
                try {
                    if(a.containsKey(object_name)&&a.getJSONObject(object_name).containsKey(KEY_NAME)){
                        valA = a.getJSONObject(object_name).getDouble(KEY_NAME);
                    }
                    if(b.containsKey(object_name)&&b.getJSONObject(object_name).containsKey(KEY_NAME)){
                        valB = b.getJSONObject(object_name).getDouble(KEY_NAME);
                    }
                } catch (JSONException e) {

                }
                if (sorttype == true) {
                    return valA.compareTo(valB);
                } else {
                    return -valA.compareTo(valB);
                }
            }
        });

        for (int i = 0; i < jsonValues.size(); i++) {
            JSONObject rs=jsonValues.get(i);
            rs.put("index",i);
            sortedJsonArray.add(rs);
        }
        return sortedJsonArray;
    }
}
