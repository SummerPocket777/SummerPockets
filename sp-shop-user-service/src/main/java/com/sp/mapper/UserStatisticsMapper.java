package com.sp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sp.model.domain.OrderDetail;
import com.sp.model.domain.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserStatisticsMapper {

    List<Orders> selectOrdersByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<Map<String, Object>> countWeeklyOrders(@Param("businessId") int businessId);
    List<Map<String, Object>> countMonthlyOrders(@Param("businessId") int businessId);
}
