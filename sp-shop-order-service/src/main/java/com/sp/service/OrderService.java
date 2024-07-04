package com.sp.service;

import com.sp.pojo.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrderService {
    List<OrderDetail> findOrdersByOrderId(long id);
}
