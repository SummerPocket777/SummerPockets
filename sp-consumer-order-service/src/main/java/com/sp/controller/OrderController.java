package com.sp.controller;


import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.model.PageResult;
import com.sp.model.domain.OrderDetail;
import com.sp.model.domain.Orders;
import com.sp.service.OrderService;
import com.sp.vo.PageOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/getOrdersList")
    private BaseResponse<List<Orders>> getOrderByUserIdAndShopId(@RequestParam("userId") Long userId, @RequestParam("shopId") Long shopId){
        List<Orders> orders = orderService.listOrders(userId, shopId);
        return ResultUtils.success(orders);
    }

    @GetMapping("/getOrdersListByShopId")
    private BaseResponse<List<Orders>> getOrderByShopId(Long shopId) {

        List<Orders> orders1 = orderService.listOrders(shopId);
        return ResultUtils.success(orders1);
    }
//    @RequestMapping("/getOrdersListByShopId")
//    private BaseResponse< PageResult<Orders>> getOrderByShopId(@RequestBody PageOrderVO pageOrderVO) {
//        if (pageOrderVO== null ){
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        orderService.pageOrders(pageOrderVO);
//        return ResultUtils.success(orders);
//    }
    @RequestMapping("/updateOrderStatus")
    private BaseResponse<String> updateOrderStatus(@RequestParam("status")Integer status,@RequestParam("id")Long  id){
        orderService.updateOrderDetail(status, id);
        return ResultUtils.success("更新成功");
    }
}
