package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.model.PageResult;
import com.sp.model.domain.Dish;
import com.sp.model.dto.DishDTO;
import com.sp.service.impl.DishServiceImpl;
import com.sp.utils.RedisCacheUtil;
import com.sp.vo.DishVO;
import com.sp.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Resource
    private DishServiceImpl dishService;

    @Resource
    private RedisTemplate  redisTemplate;

    @RequestMapping("/getAll")
    //该方法没有存入到redis中
    public BaseResponse<PageResult<Dish>> getAll(@RequestBody PageVO pageVO){
        PageResult<Dish> dishListThroughSQL = dishService.getDishListThroughSQL(pageVO);
        return ResultUtils.success(dishListThroughSQL);
    }

    @PostMapping("/add")
    //添加菜品
    public BaseResponse<String> addDish(@RequestBody DishVO dishVO){
        log.info("新增菜品：{}",dishVO);
        dishService.saveWishFlavor(dishVO);

        //清理缓存数据
//        String key="dish_"+dishVO.getId();
//        cleanDishRedis(key);

        return ResultUtils.success("添加成功");
    }

    /**
     * 获取菜品详情
     *
     * @param id id
     * @return {@link BaseResponse }<{@link DishDTO }>
     */
    @RequestMapping("/getDishDetail")
    public BaseResponse<DishDTO> getDishDetail(Long id){
        log.info("根据id查询菜品:{}",id);
        DishDTO dishVO=dishService.getByIdWithFlavor(id);
        return ResultUtils.success(dishVO);
    }

    /**
     * 批量删除菜品
     *
     * @param ids id
     * @return {@link BaseResponse }
     */
    @GetMapping
    public BaseResponse delete(@RequestParam List<Long> ids){
        log.info("菜品批量删除：{}",ids);
        dishService.delete(ids);

        //将所有的菜品数据进行缓存删除  dish_开头的
//        cleanDishRedis("dish_*");

        return ResultUtils.success();
    }

    /**
     * 起售停售菜品
     *
     * @param status 状态
     * @param id     id
     */
    @PostMapping("status/{status}/{id}")
    public BaseResponse startOtStop(@PathVariable Integer status,@PathVariable Long id){
        log.info("起售停售菜品:{},{}",status,id);
        dishService.startOtStop(status,id);

        //将所有的菜品数据进行缓存删除  dish_开 头的
//        cleanDishRedis("dish_*");
        return ResultUtils.success();
    }

    @PostMapping("/update")
    public  BaseResponse update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品信息:{}",dishDTO);
        dishService.updateWithFlavor(dishDTO);
        //修改操作
        //        cleanDishRedis("dish_*");
        return ResultUtils.success();

    }

    /**
     * 清除缓存中的菜品
     *
     * @param pattern 模式
     */
    private void cleanDishRedis(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

}
