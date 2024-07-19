package com.sp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sp.model.PageResult;
import com.sp.pojo.SysBusiness;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sp.vo.BusinessVo;

import java.util.List;

/**
* @author cc129
* @description 针对表【sys_business(商家表)】的数据库操作Service
* @createDate 2024-07-19 10:53:27
*/
public interface SysBusinessService extends IService<SysBusiness> {

    PageResult<SysBusiness> pageQuery(BusinessVo businessVo);
}
