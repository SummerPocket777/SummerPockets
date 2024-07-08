package com.sp.service;


import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.sp.model.domain.OrderDetail;
import com.sp.model.domain.Orders;
import org.springframework.stereotype.Component;

@Component
public interface OrderService {

    Orders findOrdersByOrderId(Long id);

    OrderDetail getOrderByShopId(Long shopId);

    OrderDetail getOrderDetailById(Long id);


}
