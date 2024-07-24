package com.sp.controller;


import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.model.domain.Orders;
import com.sp.model.dto.OrderSubmitDTO;
import com.sp.model.vo.OrdersSubmitVO;
import com.sp.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
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
    @PostMapping("/submit")
    public BaseResponse<OrderSubmitDTO> sumbit(@RequestBody OrdersSubmitVO ordersSubmitVO){
        log.info("用户下单的参数为{}",ordersSubmitVO);
        OrderSubmitDTO orderSubmitVO=orderService.sumbitOrder(ordersSubmitVO);
        return ResultUtils.success(orderSubmitVO);
    }


}
