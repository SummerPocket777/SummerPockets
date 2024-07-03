package com.sp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sp.model.PageResult;
import com.sp.model.domain.Setmeal;
import com.sp.model.domain.SysBusiness;
import com.sp.model.dto.DishItemDTO;
import com.sp.model.vo.SetmealPageQueryVO;
import com.sp.model.vo.SetmealVO;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 按id获取菜品
     *
     * @param id id
     */
    List<DishItemDTO> getDishItemById(Long id);

    /**
     * 保存套餐
     *
     * @param setmealVO setmeal签证官
     */
    void saveSetmeal(SetmealVO setmealVO);

    /**
     * 分页查询
     *
     * @param setmealPageQueryVO 设置页面查询vo
     * @return {@link PageResult }
     */
    PageResult pageQuery(SetmealPageQueryVO setmealPageQueryVO);

    /**
     * 启动或停止
     *
     * @param status 状态
     * @param id     id
     */
    void startOrStop(Integer status, Long id);
}
