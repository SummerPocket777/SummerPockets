package com.sp.client;

import com.sp.core.common.BaseResponse;
import com.sp.model.domain.ShoppingCart;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "sp-consumer-dish-service",path  = "/user/shoppingCart")
public interface CartFeignClient {

    @GetMapping("/list")
    BaseResponse<List<ShoppingCart>> list(@RequestParam("tableId") Long tableId, @RequestParam("businessId") Long businessId);

    @GetMapping("/clean")
    BaseResponse clean(@RequestParam("tableId") Long tableId, @RequestParam("businessId") Long businessId);
}
