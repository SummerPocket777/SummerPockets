package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.model.PageResult;
import com.sp.model.domain.Dish;
import com.sp.service.impl.DishServiceImpl;
import com.sp.vo.PageVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Resource
    private DishServiceImpl dishService;

    @RequestMapping("/getAll")
    //该方法没有存入到redis中
    public BaseResponse<PageResult<Dish>> getAll(@RequestBody PageVO pageVO){
        PageResult<Dish> dishListThroughSQL = dishService.getDishListThroughSQL(pageVO);
        return ResultUtils.success(dishListThroughSQL);
    }

    @RequestMapping("/add")
    //添加菜品
    public BaseResponse<String> addDish(@RequestBody Dish dish){
        dishService.insertDish(dish);
        return ResultUtils.success("添加成功");
    }


}
