package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.model.domain.Category;
import com.sp.model.domain.Dish;
import com.sp.service.impl.CateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("consumer/dish")
public class CateController {
    @Autowired
    private CateServiceImpl cateService;
    // 获取所有菜品分类
    @GetMapping("getAllCate")
    public BaseResponse<List<Category>>  getAllCate(@RequestParam("shopId") Long shopId){
        return ResultUtils.success(cateService.getAllCate(shopId));
    }



}
