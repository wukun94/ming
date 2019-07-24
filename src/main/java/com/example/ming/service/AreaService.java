package com.example.ming.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ming.Mapper.FindAllMapper;
import com.example.ming.entity.Area;
import com.example.ming.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AreaService {

    @Autowired
    FindAllMapper findAllMapper;

    @Autowired
    AreaRepository areaRepository;

    public List findData(){
        List list=findAllMapper.findall();
        return  list;
    }

    public List findData1(){
        return (List<Area>)areaRepository.findAll();
    }




}
