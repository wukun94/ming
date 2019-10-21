package com.example.ming.mapper;


import com.example.ming.entity.WhileList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface FindAllMapper {

    /**
     *自定义查询方法
     * @return list
     */
    @Select("select * from area")
    List<WhileList> findall();


}
