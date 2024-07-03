package com.sp.pojo;

import lombok.Data;

@Data
public class ConsumerUser {

  private long id;//1
  private String openid;
  private String name;
  private String phone;
  private String sex;
  private String idNumber;
  private String avatar;
  private java.sql.Timestamp createTime;

}
