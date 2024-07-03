package com.sp.listener;


import com.alibaba.fastjson.JSON;
import com.sp.mapper.DishMapper;
import com.sp.model.domain.Dish;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "addDish", consumerGroup = "${rocketmq.consumer.group}")
public class DishInsertListener implements RocketMQListener<String> {
    @Autowired
    private DishMapper dishMapper;
    @Override
    public void onMessage(String s) {
        Dish dish = (Dish) JSON.parse(String.valueOf(s));
        dishMapper.insert(dish);
    }
}
