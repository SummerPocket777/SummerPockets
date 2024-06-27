package com.sp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 系统角色菜单关联表
 * @TableName sys_role_menu
 */
@TableName(value ="sys_role_menu")
@Data
public class SysRoleMenu implements Serializable {
    /**
     * 系统角色菜单关联表id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色Id
     */
    private Long roleId;

    /**
     * 菜单Id
     */
    private Long menuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}