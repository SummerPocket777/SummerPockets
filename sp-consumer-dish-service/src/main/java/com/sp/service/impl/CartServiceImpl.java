package com.sp.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.sp.mapper.CartMapper;
import com.sp.mapper.DishMapper;
import com.sp.mapper.SetmealMapper;
import com.sp.model.domain.Dish;
import com.sp.model.domain.Setmeal;
import com.sp.model.domain.ShoppingCart;
import com.sp.model.vo.ShoppingCartVO;
import com.sp.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 购物车服务提示
 *
 * @author 罗汉
 * @date 2024/07/15
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {
    @Resource
    private CartMapper cartMapper;
    @Resource
    private DishMapper dishMapper;
    @Resource
    private SetmealMapper setmealMapper;

    /**
     * 添加购物车
     *
     * @param shoppingCartVO 购物车dto
     */
    @Override
    public void add(ShoppingCartVO shoppingCartVO) {
        // 1 判断购物车是否存在当前商品
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartVO, shoppingCart);
        Long userId = StpUtil.getLoginIdAsLong();
        shoppingCart.setUserId(userId);

        List<ShoppingCart> list = cartMapper.list(shoppingCart);
        // 1.1存在 数量+1
        if (list != null && list.size() > 0) {
            ShoppingCart cart = list.get(0);
            cart.setNumber(cart.getNumber() + 1);// update shopping cart set number =? where id=?
            cartMapper.updateNumberById(shoppingCart);
        } else {
            // 1.2不存在  插入购物车数据
            // shoppingCart
            // 判断本次添加的是菜品还是套餐
            Long dishId = shoppingCartVO.getDishId();
            if (dishId != null) {// 说明添加的是菜品
                Dish dish = dishMapper.selectById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            } else {// 添加的是套餐
                Long setmealId = shoppingCartVO.getSetmealId();
                Setmeal setmeal = setmealMapper.selectById(setmealId);
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImageUrl());
                shoppingCart.setAmount(setmeal.getPrice());
            }
            // 统一插入数据
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(new Date());
            cartMapper.insert(shoppingCart);
        }


    }

    /**
     * 显示购物车
     *
     * @return {@link List}<{@link ShoppingCart}>
     */
    @Override
    public List<ShoppingCart> showCart() {
        Long userId = StpUtil.getLoginIdAsLong();
        ShoppingCart shoppingCart = ShoppingCart.builder().userId(userId).build();
        List<ShoppingCart> list = cartMapper.list(shoppingCart);
        return list;
    }

    /**
     * 清空购物车
     */
    @Override
    public void cleanCart() {
        Long userId = StpUtil.getLoginIdAsLong();
        cartMapper.deleteByUserId(userId);
    }
}
