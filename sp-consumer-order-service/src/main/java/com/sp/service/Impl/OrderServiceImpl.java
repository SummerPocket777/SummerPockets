package com.sp.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.mapper.OrderMapper;
import com.sp.pojo.OrderDetail;
import com.sp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDetail findOrdersByOrderId(Long id) {
        if(id==null || id <=0 ){
           throw new BusinessException(ErrorCode.PARAMS_ERROR,"订单id不能为空");
        }
        OrderDetail orderDetail = orderMapper.selectById(id);
        if(orderDetail==null){
            throw new BusinessException(ErrorCode.NULL_ERROR,"返回的订单为空");
        }
        return orderDetail;
    }

    @Override
    public OrderDetail getOrderByShopId(Long shopId) {

        if(shopId==null || shopId <=0 ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"商铺id不能为空");
        }
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();




        return null;
    }
}
