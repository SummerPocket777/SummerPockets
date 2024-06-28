package com.sp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sp.model.domain.SysBusiness;
import com.sp.model.vo.SysBusinessVO;

/**
* @author Administrator
* @description 针对表【sys_business(商家表)】的数据库操作Service
* @createDate 2024-06-28 12:43:04
*/
public interface SysBusinessService extends IService<SysBusiness> {

    SysBusiness getSafetyBusiness(SysBusiness business);

    long addBusiness(SysBusiness sysBusiness);

    boolean updateBusiness(SysBusinessVO sysBusiness);
}
