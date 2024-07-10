package com.sp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sp.model.domain.ConsumerUser;
import com.sp.model.dto.VXUserLoginDTO;
import com.sp.model.vo.VXUserLoginVO;


/**
* @author Administrator
* @description 针对表【sys_business(商家表)】的数据库操作Service
* @createDate 2024-06-28 12:43:04
*/
public interface ConsumerUserService extends IService<ConsumerUser> {

    VXUserLoginDTO vxLogin(VXUserLoginVO vo);
}
