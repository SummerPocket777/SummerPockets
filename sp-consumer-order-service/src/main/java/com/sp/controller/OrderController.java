package com.sp.controller;


import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.pojo.OrderDetail;
import com.sp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/history/{Id}")
    public BaseResponse<OrderDetail> getHistoryOrders(@PathVariable("Id") Long id){
        return ResultUtils.success(orderService.findOrdersByOrderId(id));
    }
}
