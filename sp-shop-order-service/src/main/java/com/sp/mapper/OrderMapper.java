package com.sp.mapper;

import com.sp.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OrderMapper  {
    List<OrderDetail> findOrdersByOrderId(long id);
}
