package com.sp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sp.model.domain.Dish;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
