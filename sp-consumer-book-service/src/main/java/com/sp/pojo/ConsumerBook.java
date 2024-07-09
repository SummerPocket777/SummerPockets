package com.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName(value ="consumer_book")
@Data
public class ConsumerBook implements Serializable {

  @TableId(type = IdType.AUTO)
  private Long bookId;


  private Long bookNumber;//1
  private String bookDate;
  private String bookName;
  private String bookPhone;
  private Long isStatus;
  private Long isDelete;
  private Long consumerId;
  private Date bookCreatedate;

  @TableField(exist = false)
  private static final long serialVersionUID = 1L;

}
