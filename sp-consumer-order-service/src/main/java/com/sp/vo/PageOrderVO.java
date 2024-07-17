package com.sp.vo;

import lombok.Data;

@Data
public class PageOrderVO {
    private int pageNo;

    private int pageSize;
    //分类id
    private Long shopId;

}
