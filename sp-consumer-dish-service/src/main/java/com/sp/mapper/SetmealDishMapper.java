package com.sp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sp.model.domain.SetmealDish;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
* @author Administrator
* @description 针对表【setmeal_dish(套餐菜品关系表)】的数据库操作Mapper
* @createDate 2024-07-02 19:05:08
* @Entity generator.domain.SetmealDish
*/
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {

    void insertBatch(List<SetmealDish> setmealDishes);
    //select setmeal id from setmeal dis where dish_id in(1,2,3,4,5)
    List<Long> getSetmealIdsByDishIds(@Param("dishIds") List<Long> dishIds);
}




