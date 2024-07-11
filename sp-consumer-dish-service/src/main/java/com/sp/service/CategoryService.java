package com.sp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sp.model.domain.Category;
import com.sp.model.dto.UserDishDTO;


import java.util.List;

public interface CategoryService extends IService<Category>{

    //获取所有分类,通过redis
    List<Category> loadAllCateToRedis(Long shopId);
    //从redis获取所有分类
    List<Category> getAllCate(Long shopId);

    /**
     * 通过商店id获取列表菜
     *
     * @param businessId 商店id
     * @return {@link List }<{@link UserDishDTO }>
     */
    UserDishDTO getListDishByShopId(Long businessId);
}
