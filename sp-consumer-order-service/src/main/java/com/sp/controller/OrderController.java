package com.sp.controller;


import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.model.domain.Orders;
import com.sp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/getOrdersList")
    private BaseResponse<List<Orders>> getOrderByUserIdAndShopId(@RequestParam Long userId, @RequestParam Long shopId){
        List<Orders> orders = orderService.listOrders(userId, shopId);
        return ResultUtils.success(orders);
    }
}
