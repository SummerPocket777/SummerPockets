package com.sp.service;

import com.sp.model.PageResult;
import com.sp.model.domain.Dish;
import com.sp.model.dto.DishDTO;
import com.sp.vo.PageVO;

import java.util.List;

public interface DishService {
    List<Dish> getDishList(Long shopId);

    PageResult<Dish> getDishListThroughSQL(PageVO pageVO);

    List<Dish> loadDishListToRedis(Long shopId);

    /**
     * 获取菜品详情包括口味
     *
     * @param id id
     * @return {@link DishDTO }
     */
    DishDTO getByIdWithFlavor(Long id);
}
