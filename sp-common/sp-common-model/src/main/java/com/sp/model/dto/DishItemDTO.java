package com.sp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishItemDTO implements Serializable {

    //菜品名称
    private String name;

    //份数
    private Integer copies;

    //菜品图片
    private String image;

    //菜品描述
    private String description;
}
