package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.model.domain.SysBusiness;
import com.sp.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:
 * @Descripition:
 * @Date: 2024/7/19
 */
@RestController
@RequestMapping("/shop/userCenter")
public class UserCenterController {
    @Autowired
    private UserCenterService userCenterService;

    @RequestMapping("/updateInfo")
    public BaseResponse updateUserInfo(@RequestBody SysBusiness sysBusiness){
        return userCenterService.updateUserCenterInfo(sysBusiness);
    }

    @RequestMapping("/updateLogo")
    public BaseResponse updateLogo(@RequestBody SysBusiness business){
        return userCenterService.updateLogo(business);
    }
}
