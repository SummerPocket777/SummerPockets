package com.sp.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sp.model.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家表
 * @TableName sys_business
 */
@TableName(value ="sys_business")
@Data
public class SysBusinessVO  extends PageRequest implements Serializable{
    /**
     * 主键Id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商家账户
     */
    private String account;

    /**
     * 商家密码
     */
    private String password;

    /**
     * 地址簿Id
     */
    private Integer addressId;

    /**
     * 父商家
     */
    private Integer parentId;

    /**
     * 店铺负责人手机号
     */
    private String phone;

    /**
     * 商家邮箱
     */
    private String email;

    /**
     * 商家头像url
     */
    private Integer logoUrl;

    /**
     * 商家店铺启用状态 0未启用 1已经启用
     */
    private Integer status;

    /**
     * 商家创建时间
     */
    private Date createTime;

    /**
     * 商家更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除 0未删除 已删除
     */
//    逻辑删除键
    private Integer isDelete;

    /**
     * 权限id 1普通商家
     */
    private Integer roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}