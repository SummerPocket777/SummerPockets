package com.sp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sp.model.domain.OrderDetail;
import org.apache.ibatis.annotations.Param;


public interface OrderDetailMapper extends BaseMapper <OrderDetail>{

    public void updateStatus(@Param("status") Integer status, @Param("id")Long id);


}
