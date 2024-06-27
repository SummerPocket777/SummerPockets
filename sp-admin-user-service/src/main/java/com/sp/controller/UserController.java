package com.sp.controller;

import com.sp.core.common.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    public BaseResponse<String> login() {
        return null;
    }
}
