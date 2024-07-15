package com.sp.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShoppingCartVO implements Serializable {
    /**
     * 菜id
     */
    private Long dishId;
    /**
     * 套餐 id
     */
    private Long setmealId;
    /**
     * 菜味道
     */
    private String dishFlavor;
    /**
     * 店铺号
     */
    private Long businessId;
    /**
     * 桌号
     */
    private Long tableId;

}
