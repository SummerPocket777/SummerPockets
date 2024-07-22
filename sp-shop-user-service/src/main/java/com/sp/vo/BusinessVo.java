package com.sp.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class BusinessVo implements Serializable {
    private int pageNo;

    private int pageSize;
}
