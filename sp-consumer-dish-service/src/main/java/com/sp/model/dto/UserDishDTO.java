package com.sp.model.dto;

import com.sp.model.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDishDTO {
    /**
     * 分类列表
     */
    private List<Category> cateList;
    /**
     * 菜品列表
     */
    private List<DishDTO> dishDTOList;
    /**
     * 套餐列表
     */
    private List<SetmealDTO> setmealDTOList;
}
