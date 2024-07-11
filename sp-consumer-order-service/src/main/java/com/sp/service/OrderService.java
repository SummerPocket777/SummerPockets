package com.sp.service;

import com.sp.model.domain.Dish;
import com.sp.model.domain.OrderDetail;
import com.sp.model.domain.Orders;
import java.util.List;


public interface OrderService {



    List<Orders> listOrders(Long userId,Long shopId);

    List<OrderDetail> listOrderDetail(Long orderId);

    Dish loadDishById(Long dishId);



}
