package com.sp.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookPageQueryVo implements Serializable {
    private int pageNo;

    private int pageSize;
}
