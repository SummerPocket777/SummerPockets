package com.sp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sp.model.domain.DishFlavor;
import org.apache.ibatis.annotations.Param;


import java.util.List;


public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
    void insertBatch(@Param("flavors") List<DishFlavor> flavors);
}
