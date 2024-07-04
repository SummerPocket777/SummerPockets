package com.sp.service.Impl;

import com.sp.mapper.OrderMapper;
import com.sp.pojo.OrderDetail;
import com.sp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderDetail> findOrdersByOrderId(long id) {
        return orderMapper.findOrdersByOrderId(id);
    }
}
