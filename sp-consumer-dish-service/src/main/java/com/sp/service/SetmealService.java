package com.sp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sp.model.domain.Setmeal;
import com.sp.model.domain.SysBusiness;
import com.sp.model.dto.DishItemDTO;
import com.sp.model.vo.SetmealVO;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 按id获取菜品
     *
     * @param id id
     * @return {@link List }<{@link DishItemVO }>
     */
    List<DishItemDTO> getDishItemById(Long id);

    /**
     * 保存套餐
     *
     * @param setmealVO setmeal签证官
     */
    void saveSetmeal(SetmealVO setmealVO);
}
