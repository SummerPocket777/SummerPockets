package com.sp.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.sp.core.constants.RedisConstants;
import com.sp.mapper.DishMapper;
import com.sp.mapper.SetmealMapper;
import com.sp.model.domain.Dish;
import com.sp.model.domain.Setmeal;
import com.sp.model.domain.ShoppingCart;
import com.sp.model.vo.ShoppingCartVO;
import com.sp.service.CartService;
import com.sp.utils.RedisCacheUtil;
import com.sp.ws.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    @Resource
    private DishMapper dishMapper;
    @Resource
    private SetmealMapper setmealMapper;
    @Resource
    private RedisCacheUtil redisCacheUtil;


    @Override
    public void add(ShoppingCartVO shoppingCartVO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartVO, shoppingCart);
//        Long userId = StpUtil.getLoginIdAsLong();
//        shoppingCart.setUserId(userId);
        Long tableId = shoppingCartVO.getTableId();
        Long businessId = shoppingCartVO.getBusinessId();

        String cartKey = String.format(RedisConstants.USER_CART_KEY, businessId, tableId);
        String itemKey = shoppingCartVO.getDishId() != null ? "dish:" + shoppingCartVO.getDishId() : "setmeal:" + shoppingCartVO.getSetmealId();

        ShoppingCart cart = redisCacheUtil.getCacheMapValue(cartKey, itemKey);

        if (cart != null) {
            cart.setNumber(cart.getNumber() + 1);
            redisCacheUtil.setCacheMapValue(cartKey, itemKey, cart);
            redisCacheUtil.expire(cartKey, RedisConstants.USER_CART_TTL, TimeUnit.DAYS);
        } else {
            Long dishId = shoppingCartVO.getDishId();
            if (dishId != null) {
                Dish dish = dishMapper.selectById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            } else {
                Long setmealId = shoppingCartVO.getSetmealId();
                Setmeal setmeal = setmealMapper.selectById(setmealId);
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImageUrl());
                shoppingCart.setAmount(setmeal.getPrice());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(new Date());
            redisCacheUtil.setCacheMapValue(cartKey, itemKey, shoppingCart);
            redisCacheUtil.expire(cartKey, RedisConstants.USER_CART_TTL, TimeUnit.DAYS);
        }
    }

    @Override
    public List<ShoppingCart> showCart(Long tableId, Long businessId) {
        String cartKey = String.format(RedisConstants.USER_CART_KEY, businessId, tableId);
        Map<String, ShoppingCart> cartMap = redisCacheUtil.getCacheMap(cartKey);
        List<ShoppingCart> shoppingCartList = new ArrayList<>();

        cartMap.forEach((k, v) -> {
            ShoppingCart shoppingCart = new ShoppingCart();
            if (k.startsWith("dish:")) {
                shoppingCart.setAmount(v.getAmount().multiply(new BigDecimal(v.getNumber())));
                shoppingCart.setDishId(v.getDishId());
                shoppingCart.setDishFlavor(v.getDishFlavor());
            } else {
                shoppingCart.setAmount(v.getAmount().multiply(new BigDecimal(v.getNumber())));
                shoppingCart.setSetmealId(v.getSetmealId());
            }
            shoppingCart.setImage(v.getImage());
            shoppingCart.setName(v.getName());
            shoppingCart.setNumber(v.getNumber());
            shoppingCartList.add(shoppingCart);
        });
        return shoppingCartList;
    }

    @Override
    public void cleanCart(Long tableId, Long businessId) {
        String cartKey = String.format(RedisConstants.USER_CART_KEY, businessId, tableId);
        redisCacheUtil.deleteObject(cartKey);
    }
}
