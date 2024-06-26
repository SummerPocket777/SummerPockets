package com.sp.core.utils;

import lombok.Data;

import java.io.Serializable;

import static com.sp.core.constants.SystemConstants.PAGE_NO;
import static com.sp.core.constants.SystemConstants.PAGE_SIZE;

/**
 * 页面请求
 *
 * @author 罗汉
 * @date 2024/04/25
 */
@Data
public class PageRequest implements Serializable {
    private int pageSize=PAGE_SIZE;
    private int pageNo=PAGE_NO;
    private static final long serialVersionUID = 1L;
}
