package com.example.ming.Mapper;


import com.example.ming.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface FindAllMapper {

    /**
     *自定义查询方法
     * @return list
     */
    @Select("select * from area limit 10")
    List<Area> findall();


}
