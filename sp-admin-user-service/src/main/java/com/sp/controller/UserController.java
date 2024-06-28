package com.sp.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;

import com.sp.model.domain.SysBusiness;
import com.sp.model.vo.SysBusinessVO;
import com.sp.service.SysBusinessService;
import com.sp.to.LoginTO;
import com.sp.to.UserTO;

import com.sp.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/admin/user")
public class UserController {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Resource
    private SysBusinessService sysBusinessService;
    @RequestMapping("login")
    //登录操作，通过登录获得token
    public BaseResponse<LoginTO> login(@RequestBody LoginVO vo) {

        System.out.println(vo.getUsername());
        LoginTO loginTO = new LoginTO();
        loginTO.setToken("admin");

        UserTO user = new UserTO();
        user.setName("admin");
        ArrayList<String> role = new ArrayList<>();
        role.add("admin");
        user.setRoles(role);
        user.setIntroduction("aaaaaaaaaa");
        user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4878-b634-56703b4acafe.gif");


        redisTemplate.opsForValue().set("admin",user);

        //todo  在此处连接数据库
        return ResultUtils.success(loginTO,"登录成功");
//        return new BaseResponse<>(20000,"{roles:admin}","登录成功");
    }


    //此处通过token获得用户的详细信息
    @RequestMapping("info")
    public BaseResponse<UserTO> info(@RequestParam("token") String token) {

        System.out.println(token);

        Object o = redisTemplate.opsForValue().get(token);
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
