package com.sp.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.core.constants.DishConstants;
import com.sp.mapper.*;
import com.sp.model.domain.Category;
import com.sp.model.domain.Dish;
import com.sp.model.domain.DishFlavor;
import com.sp.model.domain.Setmeal;
import com.sp.model.dto.DishDTO;
import com.sp.model.dto.DishItemDTO;
import com.sp.model.dto.SetmealDTO;
import com.sp.model.dto.UserDishDTO;
import com.sp.service.CategoryService;
import com.sp.utils.RedisCacheUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CategoryServiceImpl extends ServiceImpl<CateMapper, Category> implements CategoryService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RedisCacheUtil redisCacheUtil;
    @Resource
    private CateMapper cateMapper;
    @Resource
    private  SetmealMapper setmealMapper;
    @Resource
    private DishMapper dishMapper;
    @Resource
    private DishFlavorMapper dishFlavorMapper;


    @Override
    // 将数据库中的数据加载到redis
    public List<Category> loadAllCateToRedis(Long shopId) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id", shopId);
        List<Category> allCate = cateMapper.selectList(queryWrapper);
        //设置24小时过期时间
        redisTemplate.opsForValue().set("cate", allCate,24, TimeUnit.HOURS);
        return allCate;
    }

    @Override
    // 从redis中获取数据,如果redis数据库中有数据，则返回，如果没有数据，则加载数据至redis，然后返回
    public List<Category> getAllCate(Long shopId) {
        if(redisTemplate.opsForValue().get("cate")!=null){
            Object o = redisTemplate.opsForValue().get("cate");
            String json = String.valueOf(o);
            return JSON.parseArray(json, Category.class);
        }
        // redis数据库中没有数据,则加载数据至redis，然后返回
        return loadAllCateToRedis(shopId);
    }

    /**
     * 通过商店id获取店内菜列表
     *
     * @param businessId 业务
     * @return {@link List }<{@link UserDishDTO }>
     */
    @Override
    public UserDishDTO getListDishByShopId(Long businessId) {
        // 判断缓存中是否存在
        String listDishByShopKey=DishConstants.REDIS_USER_DISH + businessId;
        if (redisCacheUtil.hasKey(listDishByShopKey)) {
            return redisCacheUtil.getCacheObject( listDishByShopKey);
        }

        UserDishDTO userDishDTO = new UserDishDTO();

        // 查询全部分类并缓存
        String cateCacheKey=DishConstants.REDIS_CATEGORY_LIST+ businessId;
        List<Category> categoryList=new ArrayList<>();
        if (redisCacheUtil.hasKey(cateCacheKey)) {
            categoryList = redisCacheUtil.getCacheList(cateCacheKey);
        }
        if (categoryList.isEmpty()) {
            QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
            categoryQueryWrapper.eq("business_id", businessId);
            categoryList = cateMapper.selectList(categoryQueryWrapper);
            redisCacheUtil.setCacheList(cateCacheKey, categoryList);
        }
        userDishDTO.setCateList(categoryList);

        List<DishDTO> dishDTOList = new ArrayList<>();
        List<SetmealDTO> setmealDTOList = new ArrayList<>();

        // 查询分类下的菜品和套餐
        for (Category category : categoryList) {
            Long categoryId = category.getId();

            // 查询菜品并缓存
            String dishCacheKey = DishConstants.REDIS_CATEGORY_DISH + businessId+":"+categoryId;
            List<Dish> dishList = redisCacheUtil.getCacheList(dishCacheKey);
            if (dishList == null) {
                QueryWrapper<Dish> dishQueryWrapper = new QueryWrapper<>();
                dishQueryWrapper.eq("category_id", categoryId);
                dishQueryWrapper.eq("status", 1);
                dishList = dishMapper.selectList(dishQueryWrapper);
                redisCacheUtil.setCacheList(dishCacheKey, dishList);
            }

            for (Dish dish : dishList) {
                DishDTO dishDTO = new DishDTO();
                BeanUtils.copyProperties(dish, dishDTO);

                // 查询并设置菜品口味
                QueryWrapper<DishFlavor> dishFlavorQueryWrapper = new QueryWrapper<>();
                dishFlavorQueryWrapper.eq("dish_id", dish.getId());
                List<DishFlavor> flavors = dishFlavorMapper.selectList(dishFlavorQueryWrapper);
                dishDTO.setFlavors(flavors);

                dishDTOList.add(dishDTO);
            }

            // 查询套餐并缓存
            String setmealCacheKey = DishConstants.REDIS_CATEGORY_SETMEAL + businessId+":"+categoryId;
            List<Setmeal> setmealList = redisCacheUtil.getCacheList(setmealCacheKey);
            if (setmealList == null) {
                QueryWrapper<Setmeal> setmealQueryWrapper = new QueryWrapper<>();
                setmealQueryWrapper.eq("category_id", categoryId);
                setmealList = setmealMapper.selectList(setmealQueryWrapper);
                redisCacheUtil.setCacheList(setmealCacheKey, setmealList);
            }

            for (Setmeal setmeal : setmealList) {
                SetmealDTO setmealDTO = new SetmealDTO();
                BeanUtils.copyProperties(setmeal, setmealDTO);

                // 查询并设置套餐包含的菜品
                List<DishItemDTO> dishItemList = setmealMapper.getDishItemBySetmealId(setmeal.getId());
                setmealDTO.setDishItemDTOList(dishItemList);

                setmealDTOList.add(setmealDTO);
            }
        }

        userDishDTO.setDishDTOList(dishDTOList);
        userDishDTO.setSetmealDTOList(setmealDTOList);

        // 将结果存入缓存
        redisCacheUtil.setCacheObject(listDishByShopKey, userDishDTO);
        return userDishDTO;
    }



}
