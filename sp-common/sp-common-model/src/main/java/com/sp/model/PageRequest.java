package com.sp.model;

import com.sp.core.constants.SystemConstants;
import lombok.Data;

import java.io.Serializable;


/**
 * 页面请求
 *
 * @author 罗汉
 * @date 2024/06/25
 */
@Data
public class PageRequest implements Serializable {
    private int pageSize= SystemConstants.PAGE_SIZE;
    private int pageNo= SystemConstants.PAGE_NO;
    private static final long serialVersionUID = 1L;
}
