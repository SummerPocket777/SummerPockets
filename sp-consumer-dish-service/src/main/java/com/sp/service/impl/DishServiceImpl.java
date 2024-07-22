package com.sp.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sp.mapper.DishMapper;
import com.sp.model.PageRequest;
import com.sp.model.PageResult;
import com.sp.model.domain.Category;
import com.sp.model.domain.Dish;
import com.sp.model.domain.SysBusiness;
import com.sp.service.DishService;
import com.sp.vo.PageVO;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private CategoryServiceImpl categoryService;
    //从redis中获取数据,如果redis数据库中有数据，则返回，如果没有数据，则加载数据至redis，然后返回
    public List<Dish> getDishList(Long shopId){
        Object o = redisTemplate.opsForValue().get("dishList:"+shopId);
        if(o!=null){
            String json = String.valueOf(o);
            return JSON.parseArray(json, Dish.class);
        }
        return loadDishListToRedis(shopId);
    }

    @Override
    //查找该商店的菜单信息，通过数据库，没有经过redis
    public PageResult<Dish> getDishListThroughSQL(PageVO pageVO) {


        Page<Dish> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("business_id", pageVO.getId());

        if(pageVO.getCategoryId()!=null){
            queryWrapper.eq("category_id", pageVO.getCategoryId());
        }
        if(pageVO.getKeyword()!=null ){
            queryWrapper.like("name", pageVO.getKeyword());
        }

        dishMapper.selectPage(page,queryWrapper);
        List<Dish> list = page.getRecords();
        List<Dish> dishes = loadCategoryToDish(list);

        PageResult<Dish> pageResult = new PageResult<>();
        pageResult.setPageNo(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalRow(page.getTotal());
        pageResult.setPageTotalCount(page.getPages());
        pageResult.setItems(dishes);
        if (page.getTotal() == 0) {
            pageResult.setHasNext(false);
            pageResult.setHasPrevious(false);
        } else {
            pageResult.setHasNext(page.hasNext());
            pageResult.setHasPrevious(page.hasPrevious());
        }

        return pageResult;

    }

    //用于给菜品添加分类到属性中
    private List<Dish> loadCategoryToDish(List<Dish> dishList){
        List<Category> allCate = categoryService.getAllCate(dishList.get(0).getBusinessId());
        for (Dish dish : dishList) {
            for (Category category : allCate) {
                if(dish.getCategoryId().equals(category.getId())){
                    dish.setCategory(category);
                }
            }
        }
        return dishList;
    }

    //将数据库中的数据加载到redis
    public List<Dish> loadDishListToRedis(Long shopId) {
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id", shopId);
        List<Dish> dishList = dishMapper.selectList(queryWrapper);
        redisTemplate.opsForValue().set("dishList", dishList);
        return dishList;
    }
    //添加菜品
    public void insertDish(Dish dish){
        dish.setCreateTime(new Date());
        dish.setStatus(1);
        dishMapper.insert(dish);
    }



}
