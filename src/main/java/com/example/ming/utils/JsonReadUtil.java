package com.example.ming.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 此方法描述的是解析json
 */
public  class JsonReadUtil {
    public static Map<String,String> jsonRead(JSONObject json){
        Map<String,String> hashMap=new HashMap<>();
        hashMap.put("certNo",json.getString("certNo"));
        hashMap.put("prdCode",json.getString("prdCode"));
        return hashMap;
    }
}
