package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.model.domain.Category;
import com.sp.model.dto.UserDishDTO;
import com.sp.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consumer/dish/cate")
public class CateController {
    @Autowired
    private CategoryServiceImpl cateService;
    // 获取所有菜品分类
    @GetMapping("/getAll")
    public BaseResponse<List<Category>>  getAllCate(@RequestParam("shopId") Long shopId){
        return ResultUtils.success(cateService.getAllCate(shopId));
    }

    /**
     * 用户端根据商家id获取全部的菜品
     *
     * @param businessId 商店id
     * @return {@link BaseResponse }<{@link List }<{@link UserDishDTO }>>
     */
    @GetMapping("getDishList")
    public BaseResponse<UserDishDTO> getUserDishList(@RequestParam("businessId") Long businessId){
        if (businessId == null){
            new BusinessException(ErrorCode.PARAMS_ERROR,"商家店铺ID不能为空");
        }
        UserDishDTO UserDishDTO=cateService.getListDishByShopId(businessId);
        return ResultUtils.success(UserDishDTO);
    }

}
