package com.sp.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sp.model.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 旭
 * @Date: 2024/07/15 16:47
 * @description
 */
@Data
public class BookInfovo extends PageRequest implements Serializable {
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
    private String consumerName;
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
