package com.sp.service.impl;

import com.sp.config.ResponseResult;
import com.sp.mapper.UserStatisticsMapper;
import com.sp.model.domain.Orders;
import com.sp.service.UserStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserStatisticsServiceImpl implements UserStatisticsService{

    @Autowired
    private UserStatisticsMapper userStatisticsMapper;


    @Override
    public List<Orders> getWeeklyRevenue() {
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate endOfWeek = now.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));

        Date startDate = Date.from(startOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return userStatisticsMapper.selectOrdersByDateRange(startDate, endDate);
    }

    @Override
    public List<Orders> getMonthlyRevenue() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());

        Date startDate = Date.from(startOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return userStatisticsMapper.selectOrdersByDateRange(startDate, endDate);
    }

    @Override
    public ResponseResult<List<Map<String, Object>>> getWeeklyOrders(int businessId) {
        try {
            List<Map<String, Object>> weeklyOrders = userStatisticsMapper.countWeeklyOrders(businessId);
            return ResponseResult.success(weeklyOrders);
        } catch (Exception e) {
            return ResponseResult.error(500, "获取本周订单数据失败");
        }
    }

    @Override
    public ResponseResult<List<Map<String, Object>>> getMonthlyOrders(int businessId) {
        try {
            List<Map<String, Object>> monthlyOrders = userStatisticsMapper.countMonthlyOrders(businessId);
            return ResponseResult.success(monthlyOrders);
        } catch (Exception e) {
            return ResponseResult.error(500, "获取本月订单数据失败");
        }
    }
}
