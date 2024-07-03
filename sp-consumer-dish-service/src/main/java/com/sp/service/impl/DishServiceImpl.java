package com.sp.service.impl;

import com.alibaba.fastjson.JSON;
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
    public List<Dish> getDishList(){
        Object o = redisTemplate.opsForValue().get("dishList");
        if(o!=null){
            String json = String.valueOf(o);
            return JSON.parseArray(json, Dish.class);
        }
        return loadDishListToRedis();
    }

    //将数据库中的数据加载到redis
    public List<Dish> loadDishListToRedis() {
        List<Dish> dishList = dishMapper.selectList(null);
        redisTemplate.opsForValue().set("dishList", dishList);
        return dishList;
    }
    //发送消息队列
    public void insertDish(Dish dish){
        rocketMQTemplate.convertAndSend("addDish", JSON.toJSONString(dish));
    }
}
