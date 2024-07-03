package com.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.mapper.SetmealDishMapper;
import com.sp.mapper.SetmealMapper;
import com.sp.model.PageResult;
import com.sp.model.domain.Setmeal;
import com.sp.model.domain.SetmealDish;
import com.sp.model.dto.DishItemDTO;
import com.sp.model.vo.SetmealPageQueryVO;
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

    /**
     * 页面查询
     *
     * @param setmealPageQueryVO 设置页面查询vo
     * @return {@link PageResult }
     */
    @Override
    public PageResult pageQuery(SetmealPageQueryVO setmealPageQueryVO) {
        int PageNo = setmealPageQueryVO.getPageNo();
        int pageSize = setmealPageQueryVO.getPageSize();
        //设置分页参数
        Page<Setmeal> page = new Page<>(PageNo, pageSize);
        QueryWrapper<Setmeal> queryWrapper = new QueryWrapper<>();
        //    private String name;
        //    private Integer categoryId;
        queryWrapper.like(setmealPageQueryVO.getName() != null, "name", setmealPageQueryVO.getName());
        queryWrapper.eq(setmealPageQueryVO.getCategoryId() != null, "category_id", setmealPageQueryVO.getCategoryId());
        setmealMapper.selectPage(page, queryWrapper);
        //获取分页数据
        List<Setmeal> list = page.getRecords();
        PageResult<Setmeal> pageResult = new PageResult<>();
        pageResult.setPageNo(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalRow(page.getTotal());
        pageResult.setPageTotalCount(page.getPages());
        pageResult.setItems(list);
        if (page.getTotal() == 0) {
            pageResult.setHasNext(false);
            pageResult.setHasPrevious(false);
        } else {
            pageResult.setHasNext(page.hasNext());
            pageResult.setHasPrevious(page.hasPrevious());
        }
        return pageResult;
    }

    /**
     * 启动或停止
     *
     * @param status 状态
     * @param id     id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        if (status == null || id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
        Setmeal setmeal = Setmeal.builder().status(status).id(id).build();
        setmealMapper.updateById(setmeal);
    }
}
