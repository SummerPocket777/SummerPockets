package com.sp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.constants.StatusConstant;
import com.sp.model.PageResult;
import com.sp.model.domain.Setmeal;
import com.sp.model.dto.DishItemDTO;
import com.sp.model.vo.SetmealPageQueryVO;
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

    /**
     * 套餐分页查询
     *
     * @param setmealPageQueryVO 设置页面查询volt}>
     */
    @GetMapping("/page")
    public BaseResponse<PageResult> page(@RequestBody SetmealPageQueryVO setmealPageQueryVO){//springMVC会自动封装好
        log.info("套餐分页查询参数：{}",setmealPageQueryVO );
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryVO);
        return  ResultUtils.success(pageResult);
    }


    /**
     * 起售停售套餐
     *
     * @param status 状态
     * @param id     id
     * @return {@link BaseResponse }
     */
    @PostMapping("status/{status}")
    public BaseResponse startOrStop(@PathVariable Integer status,Long id){
        // public BaseResponse startOtStop(@PathVariable("status") Integer status,Long id){//变量同名可以省略
        log.info("起售停售套餐:{},{}",status,id);
        setmealService.startOrStop(status,id);
        return ResultUtils.success();
    }



}
