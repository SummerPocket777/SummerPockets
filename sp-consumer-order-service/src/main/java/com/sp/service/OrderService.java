package com.sp.service;

import com.sp.model.domain.Dish;
import com.sp.model.domain.OrderDetail;
import com.sp.model.domain.Orders;
import com.sp.vo.PageOrderVO;

import java.util.List;


public interface OrderService {

    List<Orders> listOrders(PageOrderVO pageOrderVO);

    List<Orders> listOrders(Long userId,Long shopId);

    List<OrderDetail> listOrderDetail(Long orderId);

    void updateOrderDetail(Integer status, Long id);

    Dish loadDishById(Long dishId);



}
