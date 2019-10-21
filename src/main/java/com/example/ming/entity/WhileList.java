package com.example.ming.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wukun on 2019/10/19
 **/
@Data
@NoArgsConstructor
@Entity
@Table(name = "WHILELIST")
public class WhileList {
    @Id
    private String userName;

    private String idCard;

    private Date inputTime;

    private  int felFlag;
}
