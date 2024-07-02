package com.sp.pojo;

import lombok.Data;

@Data
public class OrderDetail {

  private long id;
  private String imageUrl;
  private long orderId;
  private long dishId;
  private long setmealId;
  private String dishFlavor;
  private long number;
  private double amount;
}
