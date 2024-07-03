package com.sp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sp.model.domain.Setmeal;
import com.sp.model.dto.DishItemDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * setmeal映射器
 *
 * @author 罗汉
 * @date 2024/07/02
 */
public interface SetmealMapper extends BaseMapper<Setmeal> {
    /**
     * 根据套餐id查询菜品选项
     * @param setmealId
     * @return
     */
    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{setmealId}")
    List<DishItemDTO> getDishItemBySetmealId(Long setmealId);
}




