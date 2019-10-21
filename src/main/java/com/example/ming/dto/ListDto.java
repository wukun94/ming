package com.example.ming.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

/**
 * Created by wukun on 2019/10/19
 **/
@Data
@NoArgsConstructor
public class ListDto {
    @Id
    private String userName;

    private String idCard;

    private String inputTime;

    private  int felFlag;
}
