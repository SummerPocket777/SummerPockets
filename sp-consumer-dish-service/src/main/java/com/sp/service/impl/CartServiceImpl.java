package com.sp.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.sp.core.constants.RedisConstants;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
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


    /**
     * 添加购物车项到用户购物车。
     *
     * @param shoppingCartVO 购物车项的视图对象，包含了用户选择的商品或套餐信息。
     */
    @Override
    public void add(ShoppingCartVO shoppingCartVO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartVO, shoppingCart);

        Long tableId = shoppingCartVO.getTableId();
        Long businessId = shoppingCartVO.getBusinessId();

        String cartKey = String.format(RedisConstants.USER_CART_KEY, businessId, tableId);
        // 根据购物车项是菜品还是套餐，格式化购物车项的Redis缓存键名
        String itemKey = shoppingCartVO.getDishId() != null ? "dish_" + shoppingCartVO.getDishId() : "setmeal_" + shoppingCartVO.getSetmealId();

        // 从Redis缓存中获取已存在的购物车项
        ShoppingCart cart = redisCacheUtil.getCacheMapValue(cartKey, itemKey);

        if (cart != null) {
            // 如果购物车项已存在，则数量加1并更新缓存
            cart.setNumber(cart.getNumber() + 1);
            redisCacheUtil.setCacheMapValue(cartKey, itemKey, cart);
            // 为购物车缓存设置过期时间
            redisCacheUtil.expire(cartKey, RedisConstants.USER_CART_TTL, TimeUnit.DAYS);
        } else {
            // 如果购物车项不存在，则根据是菜品还是套餐来设置购物车项的属性
            Long dishId = shoppingCartVO.getDishId();
            if (dishId != null) {
                // 如果是菜品，查询菜品信息并设置到购物车项
                Dish dish = dishMapper.selectById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            } else {
                // 如果是套餐，查询套餐信息并设置到购物车项
                Long setmealId = shoppingCartVO.getSetmealId();
                Setmeal setmeal = setmealMapper.selectById(setmealId);
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImageUrl());
                shoppingCart.setAmount(setmeal.getPrice());
            }
            // 设置购物车项的数量和创建时间
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(new Date());
            // 将新的购物车项添加到Redis缓存
            redisCacheUtil.setCacheMapValue(cartKey, itemKey, shoppingCart);
            // 为购物车缓存设置过期时间
            redisCacheUtil.expire(cartKey, RedisConstants.USER_CART_TTL, TimeUnit.DAYS);
        }
    }


    /**
     * 删除购物车单项
     *
     * @param shoppingCartVO 购物车vo
     */
    @Override
    public void del(ShoppingCartVO shoppingCartVO) {
        // 创建一个新的购物车对象
        ShoppingCart shoppingCart = new ShoppingCart();
        // 将购物车视图对象的属性复制到购物车实体对象
        BeanUtils.copyProperties(shoppingCartVO, shoppingCart);

        // 获取购物车项关联的餐桌和业务ID
        Long tableId = shoppingCartVO.getTableId();
        Long businessId = shoppingCartVO.getBusinessId();

        // 根据业务ID和餐桌ID格式化购物车的Redis缓存键名
        String cartKey = String.format(RedisConstants.USER_CART_KEY, businessId, tableId);
        // 根据购物车项是菜品还是套餐，格式化购物车项的Redis缓存键名
        String itemKey = shoppingCartVO.getDishId() != null ? "dish_" + shoppingCartVO.getDishId() : "setmeal_" + shoppingCartVO.getSetmealId();

        // 从Redis缓存中获取已存在的购物车项
        ShoppingCart cart = redisCacheUtil.getCacheMapValue(cartKey, itemKey);

        if (cart != null) {
            // 如果购物车项已存在，则数量减1
            cart.setNumber(cart.getNumber() - 1);

            if (cart.getNumber() > 0) {
                // 如果数量大于0，则更新缓存
                redisCacheUtil.setCacheMapValue(cartKey, itemKey, cart);
            } else {
                // 如果数量小于等于0，则从缓存中删除该项
                redisCacheUtil.deleteCacheMapValue(cartKey, itemKey);
            }
            // 为购物车缓存设置过期时间
            redisCacheUtil.expire(cartKey, RedisConstants.USER_CART_TTL, TimeUnit.DAYS);
        } else {
             log.warn("尝试删除不存在的购物车项: " + itemKey);
             throw new BusinessException(ErrorCode.PARAMS_ERROR,"购物车项不存在");
        }
    }


    @Override
    public List<ShoppingCart> showCart(Long tableId, Long businessId) {
        String cartKey = String.format(RedisConstants.USER_CART_KEY, businessId, tableId);
        Map<String, ShoppingCart> cartMap = redisCacheUtil.getCacheMap(cartKey);
        List<ShoppingCart> shoppingCartList = new ArrayList<>();

        cartMap.forEach((k, v) -> {
            ShoppingCart shoppingCart = new ShoppingCart();
            if (k.startsWith("dish")) {//菜品
                shoppingCart.setAmount(v.getAmount().multiply(new BigDecimal(v.getNumber())));
                shoppingCart.setDishId(v.getDishId());
                shoppingCart.setDishFlavor(v.getDishFlavor());
            } else {//套餐
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
