package com.sp.service.impl;

import com.alibaba.fastjson.JSON;

import com.sp.dto.UserDTO;

import com.sp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserDTO getInfo(String token) {

        Object o = redisTemplate.opsForValue().get("shop:userinfo:" +token);
        if (o!=null){
            String json = String.valueOf(o);
            return JSON.parseObject(json, UserDTO.class);
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("shop:userinfo:" +token);
    }
}
