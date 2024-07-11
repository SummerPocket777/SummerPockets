package com.sp.service;

import com.sp.model.domain.Category;


import java.util.List;

public interface CateService {

    //获取所有分类,通过redis
    List<Category> loadAllCateToRedis(Long shopId);
    //从redis获取所有分类
    List<Category> getAllCate(Long shopId);
}
