package com.example.ming.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;

/**
 * 生成验证码
 */
public class GetCode {

    public static void main(String[] args) {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
//CircleCaptcha captcha = new CircleCaptcha(200, 100, 4, 20);
//图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write("d:/circle.png");
//验证图形验证码的有效性，返回boolean值
        boolean a=captcha.verify("1234");
        System.out.println(a);
    }
}
