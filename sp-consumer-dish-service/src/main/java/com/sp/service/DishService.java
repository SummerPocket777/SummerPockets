package com.sp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sp.model.PageResult;
import com.sp.model.domain.Dish;
import com.sp.model.domain.SetmealDish;
import com.sp.model.dto.DishDTO;
import com.sp.vo.PageVO;

import java.util.List;

public interface DishService extends IService<Dish> {
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

    void delete(List<Long> ids);

    void startOtStop(Integer status, Long id);

    void updateWithFlavor(DishDTO dishDTO);
}
