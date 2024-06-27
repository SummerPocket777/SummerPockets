package com.sp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 系统角色表
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
public class SysRole implements Serializable {
    /**
     * 系统角色表id
     */
    private Long id;

    /**
     * 系统角色id
     */
    private Long sysRoleId;

    /**
     * 系统角色名
     */
    private String sysRoleName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}