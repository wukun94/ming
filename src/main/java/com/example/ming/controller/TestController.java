package com.example.ming.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.alibaba.fastjson.JSONObject;
import com.example.ming.common.bean.ResponseCode;
import com.example.ming.common.bean.ResponseResult;
import com.example.ming.dto.applyNoDto;
import com.example.ming.service.AreaService;
import com.example.ming.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/rule"})
@Api(tags = {"调用风控"})
@Slf4j
public class TestController {

    @Autowired
    AreaService areaService;

    @PostMapping(value = {"/data"})
    @ApiOperation(value = "显示数据")
    public ResponseResult<String> getPageGroup(@RequestBody applyNoDto applyNoDto) {
        System.out.println("*****************开始显示数据*********************");
        /*map.put("certNo","110115199003075785s");
        map.put("prdCode","3004");*/
        System.out.println(applyNoDto.toString());
        String a=HttpUtil.postJson("http://10.200.9.13:8089/getDuebillFile",applyNoDto);
        System.out.println(a);
        System.out.println("*******************数据显示结束*******************");
        return ResponseResult.e(ResponseCode.OK, a);
    }

    @PostMapping(value = {"/data1"})
    @ApiOperation(value = "JSON显示数据")
    public ResponseResult<String> getPageGroupByJson(@RequestBody JSONObject jsonObject) {
        System.out.println("*****************开始显示数据*********************");
        /*map.put("certNo","110115199003075785s");
        map.put("prdCode","3004");*/
        System.out.println(jsonObject.toString());
        Map<String,String> hashMap=new HashMap<>();
        hashMap=JsonReadUtil.jsonRead(jsonObject);
        String a=HttpUtil.postJson("http://10.200.9.13:8089/getDuebillFile",hashMap);
        System.out.println(a);
        System.out.println("*******************数据显示结束*******************");
        return ResponseResult.e(ResponseCode.OK, a);
    }

    @PostMapping(value = {"/testJson"})
    @ApiOperation(value = "JSON显示数据")
    public ResponseResult<String> testJson(@RequestBody JSONObject jsonObject) {
        System.out.println("*****************开始显示数据*********************");
        String a=JsonAnalysisUtil.JsonRead(jsonObject);
        System.out.println(a);
        System.out.println("*******************数据显示结束*******************");
        return ResponseResult.e(ResponseCode.OK, a);
    }

    @PostMapping(value = {"/pdfToPng"})
    @ApiOperation(value = "PDF转PNG")
    public ResponseResult<String> pdfToPng(@ApiParam(value = "待上传文件") MultipartFile file) throws IOException {
        System.out.println("*****************开始显示数据*********************");
        //PdfToPng.pdftopng(file);
        MultiFileToEcsUtils.upload(file);
        String name=new String(file.getOriginalFilename().getBytes("gbk"),"utf-8");
        PdfToPngGoodU.pdftopng(name);
        System.out.println("*******************数据显示结束*******************");
        return ResponseResult.e(ResponseCode.OK, "");
    }

    @PostMapping(value = {"/finddata"})
    @ApiOperation(value = "读数据")
    public ResponseResult<List> finddata() throws Exception {
        List lsit=areaService.findData();
        ListToExcelUtil.stuList2Excel(lsit);
        System.out.println(lsit.toString());
        return ResponseResult.e(ResponseCode.OK, lsit);
    }

    @PostMapping(value = {"/login"})
    @ApiOperation(value = "登陆")
    public ResponseResult<List> login() {
        List lsit=areaService.findData();
        System.out.println(lsit.toString());
        return ResponseResult.e(ResponseCode.OK, lsit);
    }

    @PostMapping(value = {"/finddata1"})
    @ApiOperation(value = "继承page的数据读取")
    public ResponseResult<List> finddata1() {
        return ResponseResult.e(ResponseCode.OK, areaService.findData1());
    }

    @PostMapping(value = {"/getCode"})
    @ApiOperation(value = "生成验证码")
    public ResponseResult<List> getCode(@RequestParam String code) {
         //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write("d:/shear.png");
        //验证图形验证码的有效性，返回boolean值
        boolean a=captcha.verify(code);
        if (a){
            return ResponseResult.e(ResponseCode.CODE);
        }else{
        return ResponseResult.e(ResponseCode.CODEFAIL);
        }
    }


}
