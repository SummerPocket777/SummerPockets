package com.sp.pojo;

import lombok.Data;

@Data
public class Cart {

  private String id;
  private String userId;
  private String productId;
  private long quantity;
}
