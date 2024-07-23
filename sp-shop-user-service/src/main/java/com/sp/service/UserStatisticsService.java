package com.sp.service;

import com.sp.config.ResponseResult;
import com.sp.model.domain.Orders;

import java.util.List;
import java.util.Map;

public interface UserStatisticsService {
    List<Orders> getWeeklyRevenue();
    List<Orders> getMonthlyRevenue();


    ResponseResult<List<Map<String, Object>>> getWeeklyOrders(int businessId);
    ResponseResult<List<Map<String, Object>>> getMonthlyOrders(int businessId);
}
