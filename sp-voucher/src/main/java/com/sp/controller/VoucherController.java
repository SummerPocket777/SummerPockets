package com.sp.controller;

import com.sp.core.utils.ResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voucher")
public class VoucherController {

    @RequestMapping("/test")
    public ResponseDTO test() {
        return new ResponseDTO(1,"优惠券获取成功1", 123);
    }
}
