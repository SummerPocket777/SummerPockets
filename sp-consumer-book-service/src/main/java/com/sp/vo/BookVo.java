package com.sp.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sp.model.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@TableName(value ="consumer_book")
@Data
public class BookVo extends PageRequest implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long bookId;
    private Long bookNumber;
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
