package com.sp.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

//import com.sp.client.ConsumerDishCartFeign;
import com.sp.client.CartFeignClient;
import com.sp.core.common.BaseResponse;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.mapper.DishMapper;
import com.sp.mapper.OrderDetailMapper;
import com.sp.mapper.OrderMapper;
import com.sp.model.domain.Dish;
import com.sp.model.domain.OrderDetail;
import com.sp.model.domain.Orders;
import com.sp.model.domain.ShoppingCart;
import com.sp.model.dto.OrderSubmitDTO;
import com.sp.model.vo.OrdersSubmitVO;
import com.sp.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private DishMapper dishMapper;

    @Resource
    private CartFeignClient cartFeignClient;

    @Override
    //根据商家id查询订单
    public List<Orders> listOrders(Long shopId) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("business_id",shopId);
        List<Orders> orders = orderMapper.selectList(queryWrapper);
        for(Orders item : orders){
            List<OrderDetail> orderDetails = listOrderDetail(item.getId());
            item.setOrderDetailList(orderDetails);
        }
        List<Orders> res = new ArrayList<>();
        for(Orders item : orders){
            for(OrderDetail od : item.getOrderDetailList()){
                Orders temp = new Orders();
                temp.setId(item.getId());
                temp.setOrderDetail(od);
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                temp.setDaytt(sdf1.format(item.getOrderTime()));
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
                temp.setTimett(sdf2.format(item.getOrderTime()));
                temp.setNumber(item.getNumber());
                temp.setStatus(item.getStatus());
                temp.setTableId(item.getTableId());
                res.add(temp);
            }
        }
        return res;
    }

    @Override
    public List<Orders> listHistoryOrders(Long shopId) {
        return null;
    }

    @Override
    //根据商家id和用户id来查询用户订单
    public List<Orders> listOrders(Long userId, Long shopId) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("business_id",shopId);
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

    @Override
    //更新订单详情状态(用于上菜，退菜）
    public void updateOrderDetail(Integer status, Long id) {
        orderDetailMapper.updateStatus(status,id);
    }


    //TODO 此处可以优化高并发处理
    //用于装载菜品信息
    public Dish loadDishById(Long dishId) {
        return dishMapper.selectById(dishId);
    }

    /**
     * 提交订单
     *
     * @param ordersSubmitVO 订单提交vo
     * @return {@link OrderSubmitDTO }
     */
    @Override
    public OrderSubmitDTO sumbitOrder(OrdersSubmitVO ordersSubmitVO) {
        if (ordersSubmitVO == null||
            ordersSubmitVO.getBusinessId()== null||
            ordersSubmitVO.getUserId()==null||
            ordersSubmitVO.getTableId()==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long businessId = ordersSubmitVO.getBusinessId();
        Long tableId = ordersSubmitVO.getTableId();
        Long userId = ordersSubmitVO.getUserId();

        //获取购物车信息
        BaseResponse<List<ShoppingCart>> response = cartFeignClient.list(tableId,businessId);
        log.info("response{}",response);
        if (response.getCode() != 20000){
           throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (response.getData() == null||response.getData().size()==0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 计算购物车总金额
        List<ShoppingCart> shoppingCartList = response.getData();
        BigDecimal orderAmount = BigDecimal.ZERO;
        for (ShoppingCart item : shoppingCartList) {
            if (item.getNumber() == null || item.getNumber() <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
            // 调用方法计算总金额
            BigDecimal amount = item.getAmount();
            orderAmount = orderAmount.add(amount);
        }

        //保存订单到数据库
        Orders orders = new Orders();

        orders.setOrderTime(new Date());
        orders.setPayStatus(Orders.UN_PAID);
        orders.setStatus(Orders.PAID);// 待付款
        orders.setNumber(String.valueOf(System.currentTimeMillis()) + userId);
        orders.setUserId(userId);
        orders.setAmount(orderAmount);
        orders.setBusinessId(businessId);
        orders.setTableId(Math.toIntExact(tableId));
        orderMapper.insert(orders);

        for (ShoppingCart item : shoppingCartList) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orders.getId());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setNumber(item.getNumber());
            orderDetail.setAmount(item.getAmount());
            orderDetailMapper.insert(orderDetail);
        }

        // 清空用户的购物车数据
        cartFeignClient.clean(tableId, businessId);
        //todo 通知前端清空购物车
//        webSocket.broadcastToRoom(businessId, tableId, "clean_cart", null);

        OrderSubmitDTO orderSubmitDTO = OrderSubmitDTO.builder()
                .id(orders.getId())
                .orderTime(orders.getOrderTime())
                .orderNumber(orders.getNumber())
                .orderAmount(orders.getAmount())
                .build();

        return orderSubmitDTO;
    }
}
