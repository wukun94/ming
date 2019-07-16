package com.example.ming.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class MultiFileToEcsUtils {
    private static String name1;
    public static void upload(MultipartFile multipartFile) throws IOException {
        String name=new String(multipartFile.getOriginalFilename().getBytes("GBK"),"utf-8");
        multipartFile.transferTo(new File("/upload/"+name));
        name1=name;
    }
}
