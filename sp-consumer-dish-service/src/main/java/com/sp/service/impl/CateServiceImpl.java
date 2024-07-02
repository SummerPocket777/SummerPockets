package com.sp.service.impl;

import com.alibaba.fastjson.JSON;
import com.sp.mapper.CateMapper;
import com.sp.model.domain.Category;
import com.sp.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CateServiceImpl implements CateService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    CateMapper cateMapper;

    @Override
    // 将数据库中的数据加载到redis
    public List<Category> loadAllCateToRedis() {
        List<Category> allCate = cateMapper.getAllCate();
        //设置24小时过期时间
        redisTemplate.opsForValue().set("cate", allCate,24, TimeUnit.HOURS);
        return allCate;
    }

    @Override
    // 从redis中获取数据,如果redis数据库中有数据，则返回，如果没有数据，则加载数据至redis，然后返回
    public List<Category> getAllCate() {
        if(redisTemplate.opsForValue().get("cate")!=null){
            Object o = redisTemplate.opsForValue().get("cate");
            String json = String.valueOf(o);
            return JSON.parseArray(json, Category.class);
        }
        // redis数据库中没有数据,则加载数据至redis，然后返回
        return loadAllCateToRedis();
    }


}
