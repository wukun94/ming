package com.example.ming.controller;


import com.example.ming.common.bean.ResponseCode;
import com.example.ming.common.bean.ResponseResult;
import com.example.ming.utils.ReadExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by wukun on 2019/10/20
 **/
@RestController
@RequestMapping(value = {"/api"})
@Api(tags = {"我的网站"})
@Slf4j
public class ListController {
    /***
     * 上传文件 用@RequestParam注解来指定表单上的file为MultipartFile
     *
     * @param file
     * @return
     */
    @PostMapping(value = {"/selectlist"})
    @ApiOperation(value = "选择文件")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletResponse response, Model model) throws IOException {

        String filePath=null;
        String filename=file.getOriginalFilename();
        if (!file.getOriginalFilename().contains("xlsx")){
            model.addAttribute("msg","请选择excel文件");
        }
        File folder = new File("D:/whitelist/"+filename);
        //如果文件夹不存在创建对应的文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                filePath = "D:/whitelist/"+filename;
                // 转存文件
                file.transferTo(new File(filePath));
                System.out.println(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("3.jpg", "UTF-8"));
            Files.copy(Paths.get("D:/whitelist/"+filename), outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
        }*/
        return ResponseResult.e(ResponseCode.OK, ReadExcelUtil.list(filePath));
    }

}
