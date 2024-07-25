package com.sp.controller;

import com.sp.config.ResponseResult;
import com.sp.model.domain.Orders;
import com.sp.service.UserStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/amount")
public class UserStatisticsController {

    @Autowired
    private UserStatisticsService userStatisticsService;

    @GetMapping("/week")
    public List<RevenueData> getWeeklyRevenue() {
        List<Orders> orders = userStatisticsService.getWeeklyRevenue();
        return orders.stream()
                .map(order -> new RevenueData(order.getAmount(), order.getCheckoutTime()))
                .collect(Collectors.toList());
    }

    @GetMapping("/month")
    public List<RevenueData> getMonthlyRevenue() {
        List<Orders> orders = userStatisticsService.getMonthlyRevenue();
        return orders.stream()
                .map(order -> new RevenueData(order.getAmount(), order.getCheckoutTime()))
                .collect(Collectors.toList());
    }

    @GetMapping("/weekly")
    public ResponseResult<List<Map<String, Object>>> getWeeklyOrders(@RequestParam int businessId) {
        return userStatisticsService.getWeeklyOrders(businessId);
    }

    @GetMapping("/monthly")
    public ResponseResult<List<Map<String, Object>>> getMonthlyOrders(@RequestParam int businessId) {
        return userStatisticsService.getMonthlyOrders(businessId);
    }

    public static class RevenueData {
        private BigDecimal amount;
        private String checkoutTime;

        public RevenueData(BigDecimal amount, Date checkoutTime) {
            this.amount = amount;
            this.checkoutTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(checkoutTime); // Format date to ISO 8601
        }

        // Getters and setters
        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getCheckoutTime() {
            return checkoutTime;
        }

        public void setCheckoutTime(String checkoutTime) {
            this.checkoutTime = checkoutTime;
        }
    }
}
