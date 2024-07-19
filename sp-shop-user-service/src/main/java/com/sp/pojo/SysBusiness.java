package com.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName sys_business
 */
@TableName(value ="sys_business")
@Data
public class SysBusiness implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String account;

    private String password;

    private Integer addressId;

    private Integer parentId;

    private String phone;

    private String email;

    private String avatar;

    private Integer status;

    private String createTime;

    private String updateTime;

    private Integer isDelete;

    private Integer roleId;
    private String address;

    private String businessName;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}