package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.model.domain.Dish;
import com.sp.service.DishService;
import com.sp.service.impl.DishServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("consumer/dish")
public class DishController {
    @Autowired
    private DishServiceImpl dishService;
    @RequestMapping("getDishList")
    public BaseResponse<List<Dish>> getDishList() {
        return ResultUtils.success(dishService.getDishList());
    }
}
