package com.sp.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.core.constants.StatusConstant;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.mapper.DishFlavorMapper;
import com.sp.mapper.DishMapper;
import com.sp.mapper.SetmealDishMapper;
import com.sp.mapper.SetmealMapper;
import com.sp.model.PageRequest;
import com.sp.model.PageResult;
import com.sp.model.domain.*;
import com.sp.model.dto.DishDTO;
import com.sp.service.DishService;
import com.sp.vo.DishVO;
import com.sp.vo.PageVO;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>  implements DishService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private DishMapper dishMapper;
    @Resource
    private DishFlavorMapper dishFlavorMapper;
    @Resource
    private SetmealDishMapper setmealDishMapper;
    @Resource
    private SetmealMapper setmealMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private CategoryServiceImpl categoryService;
    //从redis中获取数据,如果redis数据库中有数据，则返回，如果没有数据，则加载数据至redis，然后返回
    public List<Dish> getDishList(Long shopId){
        Object o = redisTemplate.opsForValue().get("dishList:"+shopId);
        if(o!=null){
            String json = String.valueOf(o);
            return JSON.parseArray(json, Dish.class);
        }
        return loadDishListToRedis(shopId);
    }

    /**
     * 查找该商店的菜单信息，通过数据库，没有经过redis
     *
     * @param pageVO 页签证官
     * @return {@link PageResult }<{@link Dish }>
     */
    @Override
    public PageResult<Dish> getDishListThroughSQL(PageVO pageVO) {

        Page<Dish> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("business_id", pageVO.getId());

        if(pageVO.getCategoryId()!=null){
            queryWrapper.eq("category_id", pageVO.getCategoryId());
        }
        if(pageVO.getKeyword()!=null ){
            queryWrapper.like("name", pageVO.getKeyword());
        }

        dishMapper.selectPage(page,queryWrapper);
        List<Dish> list = page.getRecords();
        List<Dish> dishes = loadCategoryToDish(list);

        PageResult<Dish> pageResult = new PageResult<>();
        pageResult.setPageNo(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalRow(page.getTotal());
        pageResult.setPageTotalCount(page.getPages());
        pageResult.setItems(dishes);
        if (page.getTotal() == 0) {
            pageResult.setHasNext(false);
            pageResult.setHasPrevious(false);
        } else {
            pageResult.setHasNext(page.hasNext());
            pageResult.setHasPrevious(page.hasPrevious());
        }

        return pageResult;

    }

    //用于给菜品添加分类到属性中
    private List<Dish> loadCategoryToDish(List<Dish> dishList){
        List<Category> allCate = categoryService.getAllCate(dishList.get(0).getBusinessId());
        for (Dish dish : dishList) {
            for (Category category : allCate) {
                if(dish.getCategoryId().equals(category.getId())){
                    dish.setCategory(category);
                }
            }
        }
        return dishList;
    }

    //将数据库中的数据加载到redis
    public List<Dish> loadDishListToRedis(Long shopId) {
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id", shopId);
        List<Dish> dishList = dishMapper.selectList(queryWrapper);
        redisTemplate.opsForValue().set("dishList", dishList);
        return dishList;
    }

    /**
     * 以口味为生
     *
     * @param id id
     * @return {@link DishDTO }
     */
    @Override
    public DishDTO getByIdWithFlavor(Long id) {
        //1 根据id查询菜品
        Dish dish = this.dishMapper.selectById(id);

        //2 根据菜品查询口味
        QueryWrapper<DishFlavor> dishFlavorQueryWrapper = new QueryWrapper<>();
        dishFlavorQueryWrapper.eq("dish_id",id);

        List<DishFlavor> dishFlavors= dishFlavorMapper.selectList(dishFlavorQueryWrapper);

        //3 封装到vo
        DishDTO dishDTO = new DishDTO();
        BeanUtils.copyProperties(dish,dishDTO);
        dishDTO.setShopId(dish.getBusinessId());
        dishDTO.setFlavors(dishFlavors);
        return dishDTO;
    }

    @Override
    public void delete(List<Long> ids) {
        //要删除三张表

        //1 判断当前菜品是否在起售中
        for (Long id : ids) {
            Dish dish = this.getById(id);
            if (Objects.equals(dish.getStatus(), StatusConstant.ENABLE)){
                //在起售状态 不能删除 抛个异常
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"在起售状态 不能删除");
            }
        }

        //2 如果当前菜品是否被某个套餐关联
        List<Long> setmealIds= setmealDishMapper.getSetmealIdsByDishIds(ids);
        if (setmealIds!=null && setmealIds.size()>0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"当前菜品关联了套餐,不能删除");
        }

        //3 如果都能删除
        for (Long id : ids) {
            //3.1 删除菜品表中的数据
            dishMapper.deleteById(id);
            //3.2 删除菜品关联的口味表的数据
            dishFlavorMapper.deleteById(id);
        }

    }

    /**
     * 开始或停止
     *
     * @param status 状态
     * @param id     id
     */
    @Override
    public void startOtStop(Integer status, Long id) {
        Dish dish = new Dish();
        dish.setId(id);
        dish.setStatus(status);

        this.updateById(dish);

        if (Objects.equals(status, StatusConstant.DISABLE)) {
            // 如果是停售操作，还需要将包含当前菜品的套餐也停售
            List<Long> dishIds = new ArrayList<>();
            dishIds.add(id);
            // select setmeal_id from setmeal_dish where dish_id in (?,?,?)
            List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(dishIds);
            System.err.println(setmealIds);
            if (setmealIds != null && setmealIds.size() > 0) {
                for (Long setmealId : setmealIds) {
                    Setmeal setmeal = Setmeal.builder()
                            .id(setmealId)
                            .status(StatusConstant.DISABLE)
                            .build();
                    setmealMapper.updateById(setmeal);
                }
            }
        }

    }

    /**
     * 更新菜品与味道
     *
     * @param dishDTO 菜dto
     */
    @Override
    public void updateWithFlavor(DishDTO dishDTO) {
        // 先修改基本信息
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        this.updateById(dish);

        //1 先把原先菜品口味删掉
        QueryWrapper<DishFlavor> dishFlavorQueryWrapper = new QueryWrapper<>();
        dishFlavorQueryWrapper.eq("dish_id",dishDTO.getId());
        dishFlavorMapper.delete(dishFlavorQueryWrapper);

        //2 再重新插入新的口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors!=null && flavors.size()>0){
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(dishDTO.getId());
            }
            //批量插入数据
            dishFlavorMapper.insert(flavors);
        }
    }

    @Override
    public void saveWishFlavor(DishVO dishVO) {
        Dish dish = new Dish();

        // 对象属性拷贝赋值
        BeanUtils.copyProperties(dishVO, dish);
        // 向菜品表插入1数据
        dish.setCreateTime(new Date());
        dishMapper.insert(dish);

        //获取insert语句生成的主键值
        Long dishId = dish.getId();

        // 向口味表插入多个数据
        List<DishFlavor> flavors = dishVO.getFlavors();
        if (flavors != null && flavors.size() > 0) {// 说明口味数据不为空
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            // 向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    //添加菜品
    public void insertDish(Dish dish){
        dish.setCreateTime(new Date());
        dish.setStatus(1);
        dishMapper.insert(dish);
    }



}
