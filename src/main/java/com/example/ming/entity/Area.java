package com.example.ming.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "area")
@Data
@NoArgsConstructor
public class Area {
  private static final long serialVersionUID = 1L;

  @Id
  private long areaid;

  private String areacode;

  private String areaname;

  private long level;

  private String citycode;

  private String center;

  private long parentid;


}
