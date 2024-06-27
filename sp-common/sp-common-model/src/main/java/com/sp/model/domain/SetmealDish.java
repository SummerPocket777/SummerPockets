package com.sp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 套餐菜品关系表
 * @TableName setmeal_dish
 */
@TableName(value ="setmeal_dish")
@Data
public class SetmealDish implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 套餐id
     */
    private Long setmealId;

    /**
     * 菜品id
     */
    private Long dishId;

    /**
     * 菜品份数
     */
    private Integer copies;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}