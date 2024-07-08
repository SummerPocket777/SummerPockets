package com.sp.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.mapper.OrderDetailMapper;
import com.sp.mapper.OrderMapper;
import com.sp.model.domain.OrderDetail;
import com.sp.model.domain.Orders;
import com.sp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Orders findOrdersByOrderId(Long id) {
        if(id==null || id <=0 ){
           throw new BusinessException(ErrorCode.PARAMS_ERROR,"订单id不能为空");
        }
        Orders orders = orderMapper.selectById(id);
        if(orders==null){
            throw new BusinessException(ErrorCode.NULL_ERROR,"返回的订单为空");
        }
        return orders;
    }

    @Override
    public OrderDetail getOrderByShopId(Long shopId) {

        if(shopId==null || shopId <=0 ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"商铺id不能为空");
        }
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();

        return null;
    }

    @Override
    public OrderDetail getOrderDetailById(Long id) {
        if (id==null || id<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"订单详情id不能为空");
        }
        OrderDetail orderDetail = orderDetailMapper.selectById(id);
       if (orderDetail==null){
           throw new BusinessException(ErrorCode.NULL_ERROR,"返回的订单详情为空");
       }
        return orderDetail;
    }

}
