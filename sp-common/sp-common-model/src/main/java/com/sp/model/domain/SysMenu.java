package com.sp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统菜单表
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
public class SysMenu implements Serializable {
    /**
     * 系统菜单Id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 系统菜单名
     */
    private String menuName;

    /**
     * 系统菜单组件
     */
    private String menuComponent;

    /**
     * 系统菜单路径
     */
    private String menuPath;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单是否启用1启用0禁用
     */
    private Integer menuStatus;

    /**
     * 所属父级菜单id
     */
    private Integer menuParentId;

    /**
     * 菜单是否隐藏1隐藏0未隐藏
     */
    private Integer menuIsHidden;

    /**
     * 菜单排序-数字越大排在越上面
     */
    private Integer menuSort;

    /**
     * 菜单创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否逻辑删除 0未删除 1删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}