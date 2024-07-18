package com.sp.service;

import com.sp.model.PageResult;
import com.sp.model.domain.Dish;
import com.sp.vo.PageVO;

import java.util.List;

public interface DishService {
    List<Dish> getDishList(Long shopId);

    PageResult<Dish> getDishListThroughSQL(PageVO pageVO);

    List<Dish> loadDishListToRedis(Long shopId);
}
