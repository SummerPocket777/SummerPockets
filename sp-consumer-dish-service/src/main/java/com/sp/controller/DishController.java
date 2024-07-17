package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.model.domain.Dish;
import com.sp.service.impl.DishServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Resource
    private DishServiceImpl dishService;

    @RequestMapping("/getAll")
    //该方法没有存入到redis中
    public BaseResponse<List<Dish>> getAll(@RequestParam("shopId") Long shopId){
        List<Dish> dishListThroughSQL = dishService.getDishListThroughSQL(shopId);
        return ResultUtils.success(dishListThroughSQL);
    }
}
