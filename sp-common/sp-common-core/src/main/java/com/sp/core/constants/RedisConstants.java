package com.sp.core.constants;

/**
 * Redis常量
 *
 * @author 罗汉
 * @date 2024/07/01
 */
public final class RedisConstants {
    private RedisConstants() {
    }
    /**
     * 注册图片key
     */
    public static final String REGISTER_IMG_CODE_KEY = "SummerPockets:register:imgCode:";
    /**
     * 注册验证码键
     */
    public static final String REGISTER_CODE_KEY = "SummerPockets:register:phoneCode:";
    /**
     * 注册验证码过期时间
     */
    public static final Integer REGISTER_CODE_TTL = 5;
    public static final Integer REGISTER_IMG_CODE_TTL = 1;
    /**
     * 用户更新电话键
     */
    public static final String USER_UPDATE_PHONE_KEY = "SummerPockets:user:update:phone:";
    /**
     * 用户更新电话过期时间
     */
    public static final Long USER_UPDATE_PHONE_TTL = 5L;
    /**
     * 用户更新邮件键
     */
    public static final String USER_UPDATE_EMAIL_KEY = "SummerPockets:user:update:email:";
    /**
     * 用户更新邮件过期时间
     */
    public static final Long USER_UPDATE_EMAIL_TTL = 15L;
    /**
     * 用户忘记密码键
     */
    public static final String USER_FORGET_PASSWORD_KEY = "SummerPockets:user:forget:";
    /**
     * 用户忘记密码过期时间
     */
    public static final Long USER_FORGET_PASSWORD_TTL = 15L;

    /**
     * 最小缓存随机时间
     */
    public static final int MINIMUM_CACHE_RANDOM_TIME = 2;
    /**
     * 最大缓存随机时间
     */
    public static final int MAXIMUM_CACHE_RANDOM_TIME = 3;
    /**
     * 缓存时间偏移
     */
    public static final int CACHE_TIME_OFFSET = 10;
}
