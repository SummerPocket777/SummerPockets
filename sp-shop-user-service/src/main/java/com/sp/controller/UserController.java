package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.enums.ErrorCode;
import com.sp.dto.UserDTO;
import com.sp.model.domain.SysMenu;
import com.sp.service.SysMenuService;
import com.sp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("shop/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SysMenuService sysMenuService;

    //此处通过token获得用户的详细信息
    @RequestMapping("info")
    public BaseResponse<Map<String, Object>> info(@RequestParam("token") String token) {
        // 用户信息 + 角色信息
        UserDTO user = userService.getInfo(token);
        // 菜单信息
        if (user!=null) {
            List<SysMenu> menuTree = sysMenuService.getMenuTree();
            Map<String, Object> map = new HashMap<>();
            map.put("user",user);
            map.put("menuTree", menuTree);
            return ResultUtils.success(map,"获取信息成功");
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
