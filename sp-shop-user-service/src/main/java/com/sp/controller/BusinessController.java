package com.sp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.pojo.SysBusiness;
import com.sp.service.SysBusinessService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Resource
    private SysBusinessService sysBusinessService;

    @GetMapping("/selectById")
    public BaseResponse<List<SysBusiness>> list(@RequestParam("parentId" ) int parentId){
        QueryWrapper<SysBusiness> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId);
        List<SysBusiness> list = sysBusinessService.list(queryWrapper);
        return ResultUtils.success(list);
    }

    @PostMapping("/add")
    public BaseResponse<List<SysBusiness>> list(){
        return null;
    }

}
