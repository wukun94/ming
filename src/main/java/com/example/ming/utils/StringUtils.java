package com.example.ming.utils;

import static jdk.nashorn.internal.objects.NativeString.substring;

/**
 * Created by lihe on 2016/7/6.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 字符串填充
     * @param code
     * @param num
     * @return
     */
    public static String fillZero(Integer code, int num) {
        if (code == null) {
            return "";
        }
        int length = String.valueOf(code).length();
        if (length > num) {
            return substring(String.valueOf(code), length - num, length);
        } else if (length < num) {
            return String.format("%0" + num + "d", code);
        } else {
            return String.valueOf(code);
        }
    }

    public static void main(String[] args) {
        System.out.println(fillZero(0, 5));
    }
}
