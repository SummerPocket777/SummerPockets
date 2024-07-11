package com.sp.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.sp.mapper.DishMapper;
import com.sp.mapper.OrderDetailMapper;
import com.sp.mapper.OrderMapper;
import com.sp.model.domain.Dish;
import com.sp.model.domain.OrderDetail;
import com.sp.model.domain.Orders;
import com.sp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private DishMapper dishMapper;


    @Override
    //根据商家id和用户id来查询用户订单
    public List<Orders> listOrders(Long userId, Long shopId) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("shop_id",shopId);
        List<Orders> orders = orderMapper.selectList(queryWrapper);
        for(Orders item : orders){
            List<OrderDetail> orderDetails = listOrderDetail(item.getId());
            item.setOrderDetailList(orderDetails);
        }
        return orders;
    }

    @Override
    //根据订单id查询订单详情列表
    public List<OrderDetail> listOrderDetail(Long orderId) {
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId);
        List<OrderDetail> orderDetails = orderDetailMapper.selectList(queryWrapper);
        //装载菜品信息
        for (OrderDetail orderDetail : orderDetails){
            orderDetail.setDish(loadDishById(orderDetail.getDishId()));
        }
        return orderDetails;
    }


    //TODO 此处可以优化高并发处理
    //用于装载菜品信息
    public Dish loadDishById(Long dishId) {
        return dishMapper.selectById(dishId);
    }
}
