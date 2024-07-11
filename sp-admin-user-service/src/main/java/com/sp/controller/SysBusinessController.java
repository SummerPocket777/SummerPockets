package com.sp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.model.PageResult;
import com.sp.model.domain.SysBusiness;
import com.sp.model.vo.SysBusinessVO;
import com.sp.service.SysBusinessService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sp/admin/business")
@Slf4j
public class SysBusinessController {


    @Resource
    private SysBusinessService sysBusinessService;

    /**
     * 分页查询系统业务信息
     *
     * @param sysBusinessVO 查询条件和分页信息的载体，包含业务名称等条件以及分页的页码和大小。
     * @return 返回包含查询结果的分页对象，其中查询结果是经过处理的安全业务列表。
     */
    @GetMapping("/page")
    public BaseResponse<PageResult<SysBusiness>> searchBusiness(SysBusinessVO sysBusinessVO) {
        log.info("套餐分页查询参数：{}",sysBusinessVO );
        PageResult<SysBusiness> pageResult = sysBusinessService.pageQuery(sysBusinessVO);
        return  ResultUtils.success(pageResult);
    }

    /**
     * 添加店铺的接口方法。
     *
     * @param sysBusinessVO 添加的业务信息，包含所有需要的业务字段。
     * @return BaseResponse<Long> 包含操作结果和新增业务的ID。
     */
    @PostMapping("/add")
    public BaseResponse<Long> addBusiness(@RequestBody SysBusinessVO sysBusinessVO) {
        // 校验输入参数是否为空，若为空则抛出业务异常
        if (sysBusinessVO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 创建SysBusiness实例，用于存储业务信息
        SysBusiness sysBusiness = new SysBusiness();
        // 使用BeanUtils工具类，将SysBusinessVO对象的属性复制到SysBusiness对象中
        BeanUtils.copyProperties(sysBusinessVO, sysBusiness);
        sysBusiness.setCreateTime(new Date());
        sysBusiness.setUpdateTime(new Date());
        // 调用业务服务，添加业务信息，并返回新增业务的ID
        long businessId = sysBusinessService.addBusiness(sysBusiness);
        // 构建成功响应，包含新增业务的ID
        return ResultUtils.success(businessId);
    }

    /**
     * 更新业务
     *
     * @param sysBusinessVO 系统业务
     * @return {@link BaseResponse }<{@link Boolean }>
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateBusiness(@RequestBody SysBusinessVO sysBusinessVO) {
        // 校验输入参数是否为空，若为空则抛出业务异常
        if (sysBusinessVO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean updateRes = sysBusinessService.updateBusiness(sysBusinessVO);
        if (!updateRes) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新失败");
        }
        return ResultUtils.success(true);
    }

    @GetMapping("/delete")
    public BaseResponse<Boolean> deleteBusiness(Long id) {
        // 校验输入参数是否为空，若为空则抛出业务异常
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean deleteRes = sysBusinessService.removeById(id);
        if (!deleteRes) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "删除失败");
        }
        return ResultUtils.success(true);
    }
}
