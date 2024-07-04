package com.sp.controller;


import com.sp.pojo.OrderDetail;
import com.sp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/history/{Id}")
    public List<OrderDetail> getHistoryOrders(@PathVariable("Id") Long id){
        return orderService.findOrdersByOrderId(id);
    }
}
