package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.model.domain.ShoppingCart;
import com.sp.model.vo.ShoppingCartVO;
import com.sp.service.CartService;
import com.sp.ws.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车控制器
 *
 * @author 罗汉
 * @date 2024/07/15
 */
@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
public class CartController {
    @Resource
    private  CartService cartService;
    /**
     * 添加购物车
     */
//    @Resource
//    public void setCartService(CartService cartService) {
//        this.cartService = cartService;
//    }

    @PostMapping("/add")
    public BaseResponse add(@RequestBody ShoppingCartVO shoppingCartVO){
        log.info("添加购物车商品信息为,{}",shoppingCartVO);
        cartService.add(shoppingCartVO);
        return ResultUtils.success();
    }

    /**
     * 查看购物车
     *
     */
    @GetMapping("/list")
    public BaseResponse<List<ShoppingCart>> list(Long tableId, Long businessId){
        List<ShoppingCart> shoppingCartList=cartService.showCart(tableId,businessId);
        return ResultUtils.success(shoppingCartList);
    }

    /**
     * 清空购物车
     *
     */
    @GetMapping("/clean")
    public BaseResponse clean(Long tableId, Long businessId){
        cartService.cleanCart(tableId,businessId);
        return ResultUtils.success();
    }
}
