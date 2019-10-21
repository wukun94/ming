package com.example.ming.service;

import com.example.ming.entity.WhileList;
import com.example.ming.mapper.FindAllMapper;
import com.example.ming.repository.WhileListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WhileListService {

    @Autowired
    FindAllMapper findAllMapper;

    @Autowired
    WhileListRepository areaRepository;

    public List findData(){
        List list=findAllMapper.findall();
        return  list;
    }

    public List findData1(){
        return (List<WhileList>)areaRepository.findAll();
    }




}
