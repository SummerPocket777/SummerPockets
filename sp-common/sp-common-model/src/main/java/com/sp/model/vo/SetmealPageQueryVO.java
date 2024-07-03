package com.sp.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SetmealPageQueryVO implements Serializable {

    private int pageNo;

    private int pageSize;

    private String name;

    //分类id
    private Integer categoryId;

    //状态 0表示禁用 1表示启用
    private Integer status;

}
