package com.sp.core.constants;

/**
 * 系统常量
 *
 * @author 罗汉
 * @date 2023/06/22
 */
public final class SystemConstants {
    private SystemConstants() {
    }
    public static final String SALT="SUMMER_POCKETS";

    /**
     * 分页大小
     */
    public static final Long PAGE_SIZE = 8L;
    /**
     * 默认当前页
     */
    public static final Long PAGE_NO = 1L;
    //项目名
    public static final String PROJECT_NAME = "SummerPockets";

    /**
     * 最大验证码
     */
    public static final int MAXIMUM_VERIFICATION_CODE_NUM = 999999;
    /**
     * 最小验证码
     */
    public static final int MINIMUM_VERIFICATION_CODE_NUM = 100000;

    /**
     * 默认缓冲区大小
     */
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    /**
     * 文件结束
     */
    public static final int FILE_END = -1;
}
