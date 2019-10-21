package com.example.ming.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.ming.dto.ListDto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wukun on 2019/10/19
 **/
public class ReadExcelUtil {
    public static List<ListDto> list(String filepath) {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(filepath));
        List<List<Object>> readAll = reader.read(0);
        List<ListDto> whileLists =new <ListDto>LinkedList();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
        for (int i = 0; i < readAll.size(); i++) {
            String msg=readAll.get(i).toString();
            ListDto whilelist=new ListDto();
            whilelist.setUserName(msg.substring(1,msg.length()-21));
            whilelist.setIdCard(msg.substring(msg.length()-20,msg.length()-1));
            whilelist.setInputTime(df.format(new Date()));
            whilelist.setFelFlag(1);
            //System.out.println(whilelist);
            whileLists.add(whilelist);
        }
        return whileLists;
    }

    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("C:/Users/成就系列/Desktop/白名单测试数据.xlsx"));
        List<List<Object>> readAll = reader.read(0);
        List<ListDto> whileLists =new <ListDto>LinkedList();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
        for (int i = 0; i < readAll.size(); i++) {
            String msg=readAll.get(i).toString();
            ListDto whilelist=new ListDto();
            whilelist.setUserName(msg.substring(1,msg.length()-21));
            whilelist.setIdCard(msg.substring(msg.length()-20,msg.length()-1));
            whilelist.setInputTime(df.format(new Date()));
            whilelist.setFelFlag(1);
            //System.out.println(whilelist);
            whileLists.add(whilelist);
        }
        System.out.println(whileLists);
       // System.out.println(readAll);
    }
}
