package com.sp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.mapper.SetmealDishMapper;
import com.sp.mapper.SetmealMapper;
import com.sp.model.domain.Setmeal;
import com.sp.model.domain.SetmealDish;
import com.sp.model.dto.DishItemDTO;
import com.sp.model.vo.SetmealVO;
import com.sp.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>
        implements SetmealService {
    @Resource
    private SetmealMapper setmealMapper;

    @Resource
    private SetmealDishMapper setmealDishMapper;
    /**
     * 按id获取菜品
     *
     * @param id id
     * @return {@link List }<{@link DishItemDTO }>
     */
    @Override
    public List<DishItemDTO> getDishItemById(Long id) {
        return setmealMapper.getDishItemBySetmealId(id);
    }

    /**
     * 保存setmeal
     *
     * @param setmealVO setmeal签证官
     */
    @Override
    public void saveSetmeal(SetmealVO setmealVO) {
        //属性拷贝
        Setmeal setmeal = new Setmeal();

        BeanUtils.copyProperties(setmealVO,setmeal);

        //向套餐表插入数据
        setmealMapper.insert(setmeal);

        //获取生成的套餐id
        Long setmealId = setmeal.getId();

        List<SetmealDish> setmealDishes = setmealVO.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });

        //保存套餐和菜品的关联关系
        setmealDishMapper.insertBatch(setmealDishes);
    }
}
