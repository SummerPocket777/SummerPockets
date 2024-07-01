package com.sp.pojo;

import lombok.Data;


@Data
public class Voucher {

  private long id;
  private long dishId;
  private String title;
  private String subTitle;
  private String rules;
  private long payValue;
  private long actualValue;
  private long type;
  private long status;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


}
