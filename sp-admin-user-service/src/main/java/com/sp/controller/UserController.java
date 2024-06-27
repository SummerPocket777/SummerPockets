package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;

import com.sp.to.LoginTO;
import com.sp.to.UserTO;
import com.sp.vo.LoginVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api/admin/user")
public class UserController {
    @RequestMapping("login")
    //登录操作，通过登录获得token
    public BaseResponse<LoginTO> login(@RequestBody LoginVO vo) {

        System.out.println(vo.getUsername());
        LoginTO loginTO = new LoginTO();
        loginTO.setToken("admin");

        //todo  在此处连接数据库
        return ResultUtils.success(loginTO,"登录成功");
//        return new BaseResponse<>(20000,"{roles:admin}","登录成功");
    }


    //此处通过token获得用户的详细信息
    @RequestMapping("info")
    public BaseResponse<UserTO> info(@RequestParam("token") String token) {

        System.out.println(token);
        //todo  在此处连接数据库
        UserTO user = new UserTO();
        user.setName("admin");
        ArrayList<String> role = new ArrayList<>();
        role.add("admin");
        user.setRoles(role);
        user.setIntroduction("aaaaaaaaaa");
        user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4878-b634-56703b4acafe.gif");
        return ResultUtils.success(user,"获取信息成功");
    }


    @RequestMapping("logout")
    public BaseResponse<String> logout() {

        //todo 这里写在redis上面清除token登录信息

        return ResultUtils.success("退出成功");
    }
}
