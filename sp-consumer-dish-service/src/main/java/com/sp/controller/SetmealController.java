package com.sp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.constants.StatusConstant;
import com.sp.model.domain.Setmeal;
import com.sp.model.dto.DishItemDTO;
import com.sp.model.vo.SetmealVO;
import com.sp.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;

@RestController
@RequestMapping("consumer/setmeal")
@Slf4j
public class SetmealController {
    @Resource
    private SetmealService setmealService;

    /**
     * 根据分类id查询套餐
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<Setmeal>> list(Long categoryId) {
        Setmeal setmeal = new Setmeal();
        setmeal.setCategoryId(categoryId);
        setmeal.setStatus(StatusConstant.ENABLE);
        //mybatis-plus去查询
        QueryWrapper<Setmeal> setmealQueryWrapper = new QueryWrapper<>();
        setmealQueryWrapper.eq("category_id", categoryId).eq("status", StatusConstant.ENABLE);
        List<Setmeal> list = setmealService.list(setmealQueryWrapper);
        return ResultUtils.success(list);
    }

    /**
     * 根据套餐id查询包含的菜品列表
     *
     * @param id
     * @return
     */
    @GetMapping("/dish/{id}")
    public BaseResponse<List<DishItemDTO>> dishList(@PathVariable("id") Long id) {
        List<DishItemDTO> list = setmealService.getDishItemById(id);
        return ResultUtils.success(list);
    }


    /**
     * 新增套餐
     *
     * @param setmealVO setmeal签证官
     */
    @PostMapping("/add")
    public BaseResponse<String> save(@RequestBody SetmealVO setmealVO) {

        System.out.println("当前线程id："+Thread.currentThread().getId());

        log.info("新增套餐：{}",setmealVO );
        setmealService.saveSetmeal(setmealVO);
        return ResultUtils.success();
    }




}
