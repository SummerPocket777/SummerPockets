package com.sp.service;

import com.sp.pojo.OrderDetail;
import org.springframework.stereotype.Component;

@Component
public interface OrderService {

    OrderDetail findOrdersByOrderId(Long id);

    OrderDetail getOrderByShopId(Long shopId);
}
