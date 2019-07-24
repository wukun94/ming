package com.example.ming.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ming.Mapper.FindAllMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AreaService {

    @Autowired
    FindAllMapper findAllMapper;

    public List findData(){
        List list=findAllMapper.findall();
        return  list;
    }




}
