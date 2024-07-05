package com.sp.service;

import com.sp.model.domain.Dish;

import java.util.List;

public interface DishService {
    List<Dish> getDishList(Long shopId);
}
