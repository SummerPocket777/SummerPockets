package com.sp.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.enums.ErrorCode;
import com.sp.core.utils.ResponseDTO;
import com.sp.mapper.SysBusinessMapper;
import com.sp.model.domain.SysBusiness;
import com.sp.to.LoginTO;
import com.sp.to.UserTO;
import com.sp.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("consumer/user")
public class UserController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private SysBusinessMapper sysBusinessMapper;

    @RequestMapping("login")
    public BaseResponse<LoginTO> login(@RequestBody LoginVO loginVO) {
        System.out.println(loginVO);
        QueryWrapper<SysBusiness> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",loginVO.getUsername()).eq("password",loginVO.getPassword());
        List<SysBusiness> sysBusinesses = sysBusinessMapper.selectList(queryWrapper);


        if (sysBusinesses==null || sysBusinesses.isEmpty()) {
            return ResultUtils.success(null,"用户名或密码错误");
        }
        UserTO user = new UserTO();
        SysBusiness sysBusiness = sysBusinesses.get(0);

        user.setName(sysBusiness.getBusinessName());
        ArrayList<String> role = new ArrayList<>();
        role.add("admin");
        user.setRoles(role);

        user.setAvatar(sysBusiness.getLogoUrl());

        redisTemplate.opsForValue().set("consumer"+"123456",user);


        LoginTO loginTO = new LoginTO();
        loginTO.setToken("123456");
        return ResultUtils.success(loginTO);
    }
    //此处通过token获得用户的详细信息
    @RequestMapping("info")
    public BaseResponse<UserTO> info(@RequestParam("token") String token) {

        System.out.println(token);

        Object o = redisTemplate.opsForValue().get("consumer123456");
        if (o==null){
            return ResultUtils.success(null,"数据库暂无信息");
        }
        String json = String.valueOf(o);
        UserTO user =  JSON.parseObject(json, UserTO.class);
        //todo  在此处连接数据库

        return ResultUtils.success(user,"获取信息成功");
    }


    @RequestMapping("logout")
    public BaseResponse<String> logout() {

        //todo 这里写在redis上面清除token登录信息

        return ResultUtils.success("退出成功");
    }



}
