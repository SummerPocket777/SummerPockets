package com.sp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sp.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OrderMapper extends BaseMapper<OrderDetail> {

}
