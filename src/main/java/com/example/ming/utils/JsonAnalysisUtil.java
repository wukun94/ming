package com.example.ming.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonAnalysisUtil {
    public static void main(String[] args) {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("certNo","c1er231tN85746o");
        hashMap.put("prdCode","12423545346");
        System.out.println(hashMap.toString());
    }
/*简单json的解析
*{
 "certNo": "110115199003075785s",
 "prdCode": "3004"
}
 */
    public static String JsonRead(JSONObject jsonObject){
        String jsonString=jsonObject.toString();
        String jsonData=jsonString.substring(1,jsonString.length()-1);
        String[] keyValue=jsonData.split(",");
        Map<String,String> hashMap=new HashMap<>();
        Map<String,String> hashMap1=new HashMap<>();
        for (int i=0;i<keyValue.length-1;i++){
            String[] a=keyValue[i].split(":");
            hashMap.put(a[0],a[1]);
            hashMap1.putAll(hashMap);
        }
        return hashMap1.toString();
    }
}
