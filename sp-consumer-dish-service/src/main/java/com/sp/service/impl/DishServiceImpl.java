package com.sp.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sp.mapper.DishMapper;
import com.sp.model.domain.Dish;
import com.sp.service.DishService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
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
    public List<Dish> getDishListThroughSQL(Long shopId) {
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("business_id", shopId);
        List<Dish> dishList = dishMapper.selectList(queryWrapper);
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
    //发送消息队列
    public void insertDish(Dish dish){
        rocketMQTemplate.convertAndSend("addDish", JSON.toJSONString(dish));
    }



}
