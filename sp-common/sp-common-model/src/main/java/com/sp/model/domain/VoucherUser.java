package com.sp.model.domain;

import lombok.Data;

@Data
public class VoucherUser {

  private long id;
  private long userId;
  private long voucherId;
  private String voucherUniqueCode;
  private long status;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getVoucherId() {
    return voucherId;
  }

  public void setVoucherId(long voucherId) {
    this.voucherId = voucherId;
  }


  public String getVoucherUniqueCode() {
    return voucherUniqueCode;
  }

  public void setVoucherUniqueCode(String voucherUniqueCode) {
    this.voucherUniqueCode = voucherUniqueCode;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

}
