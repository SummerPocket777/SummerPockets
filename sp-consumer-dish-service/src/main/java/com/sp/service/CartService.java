package com.sp.service;

import com.sp.model.domain.ShoppingCart;
import com.sp.model.vo.ShoppingCartVO;

import java.util.List;

/**
 * 车服务
 *
 * @author 罗汉
 * @date 2024/07/15
 */
public interface CartService {
    /**
     * 添加购物车

     */
    void add(ShoppingCartVO shoppingCartVO);

    /**
     * 显示购物车
     *
     * @return {@link List}<{@link ShoppingCart}>
     */
    List<ShoppingCart> showCart(Long tableId, Long businessId);

    /**
     * 清空购物车
     */
    void cleanCart(Long tableId, Long businessId);

    void del(ShoppingCartVO shoppingCartVO);
}
