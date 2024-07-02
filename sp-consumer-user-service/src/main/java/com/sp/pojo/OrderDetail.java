package com.sample;


public class OrderDetail {

  private long id;
  private String imageUrl;
  private long orderId;
  private long dishId;
  private long setmealId;
  private String dishFlavor;
  private long number;
  private double amount;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public long getDishId() {
    return dishId;
  }

  public void setDishId(long dishId) {
    this.dishId = dishId;
  }


  public long getSetmealId() {
    return setmealId;
  }

  public void setSetmealId(long setmealId) {
    this.setmealId = setmealId;
  }


  public String getDishFlavor() {
    return dishFlavor;
  }

  public void setDishFlavor(String dishFlavor) {
    this.dishFlavor = dishFlavor;
  }


  public long getNumber() {
    return number;
  }

  public void setNumber(long number) {
    this.number = number;
  }


  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

}
