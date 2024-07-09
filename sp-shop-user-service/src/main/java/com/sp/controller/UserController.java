package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.enums.ErrorCode;
import com.sp.dto.UserDTO;
import com.sp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("shop/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    //此处通过token获得用户的详细信息
    @RequestMapping("info")
    public BaseResponse<UserDTO> info(@RequestParam("token") String token) {
        UserDTO user = userService.getInfo(token);
        if (user!=null) {
            return ResultUtils.success(user,"获取信息成功");
        }else {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,null,"redis中没有找到对应数据");
        }

    }


    @RequestMapping("logout")
    public BaseResponse<String> logout() {
//        userService.logout(token);
        return ResultUtils.success("退出成功");
    }



}
