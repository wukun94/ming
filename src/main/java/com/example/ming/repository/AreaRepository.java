package com.example.ming.repository;

import com.example.ming.entity.Area;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 注意，用此方法可以省略通用的增删改查语句
 * 在定义实体类的时候，如果数据库中的字段名字有下划线的时候，用驼峰命名法
 * 例如 student_id,则在实体类中定义studentId
 * 如果没有下划线，统一定义为小写，例如 myName，在实体类中定义为myname
 * 注意，如果数据库的字段含有数字，则需要在定义的时候，在实体类的字段上加上colum注释
 * 他的值与数据库字段名一致
 */
public interface AreaRepository extends PagingAndSortingRepository<Area, String> {


}
