package com.sp.core.constants;

/**
 * 系统常量
 *
 * @author 罗汉
 * @date 2023/06/22
 */
public final class DishConstants {



    private DishConstants() {
    }

    /**
     * 用户从微信小程序查询该店铺全部商品的key
     */
    public static final String REDIS_USER_DISH = "sp:dish:wxuser：business:";
    /**
     * 某个店铺下的菜品分类集合的key
     */
    public static final String REDIS_CATEGORY_LIST = "sp:dish:category:business:";

    /**
     * 某个店铺下的某个菜品分类下的菜品的key
     */
    public static final String REDIS_CATEGORY_DISH = "sp:dish:category:business:categoryid:";

    public static final String REDIS_CATEGORY_SETMEAL = "sp:dish:category:business:setmealid:";
}
