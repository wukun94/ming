package com.example.ming.testUtils;

import cn.hutool.core.util.EnumUtil;

import java.util.List;
import java.util.Map;

public class testEnumUtils {
    public static void main(String[] args) {
        //获取枚举类中所有枚举对象的name列表
        List<String> names = EnumUtil.getNames(BatchCode.class);
        //DAILY, SEASON, HS_DAILY, HS_SEASON, HS_MONTH
        System.out.println("获取枚举类中所有枚举对象的name列表");
        System.out.println(names.toString());

        //获得枚举类中各枚举对象下指定字段的值。栗子：
         List<Object> types = EnumUtil.getFieldValues(BatchCode.class, "id");
         //DAILY, SEASON, HSDAILY, HSSEASON, HSMONTH
        System.out.println("获得枚举类中各枚举对象下指定字段的值。栗子");
        System.out.println(types.toString());

         //获取枚举字符串值和枚举对象的Map对应，
        // 使用LinkedHashMap保证有序，结果中键为枚举名，值为枚举对象。栗子：
        Map<String,BatchCode> enumMap = EnumUtil.getEnumMap(BatchCode.class);
        enumMap.get("HS_MONTH");
        //DAILY=DAILY, SEASON=SEASON, HS_DAILY=HS_DAILY, HS_SEASON=HS_SEASON, HS_MONTH=HS_MONTH
        System.out.println("获取枚举字符串值和枚举对象的Map对应");
        System.out.println(enumMap.toString());


        //获得枚举名对应指定字段值的Map，键为枚举名，值为字段值。栗子：
        //HS_SEASON=HSSEASON, HS_MONTH=HSMONTH, DAILY=DAILY, SEASON=SEASON, HS_DAILY=HSDAILY
        Map<String, Object> enumMap1 = EnumUtil.getNameFieldMap(BatchCode.class, "id");
        System.out.println("获得枚举名对应指定字段值的Map，键为枚举名，值为字段值");
        System.out.println(enumMap1.toString());

        String aa=enumMap.get("HS_MONTH").toString();
        System.out.println(aa);
    }
}

enum BatchCode {
    DAILY("DAILY", "贷中日跑批"),
    SEASON("SEASON", "贷中季跑批"),
    HS_DAILY("HSDAILY", "惠商物流贷中日跑批"),
    HS_SEASON("HSSEASON", "惠商物流贷中季跑批"),
    HS_MONTH("HSMONTH", "惠商物流贷中月跑批"),
    ;

    BatchCode(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

