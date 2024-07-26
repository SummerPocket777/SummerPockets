package com.sp.service;

import com.sp.core.common.BaseResponse;
import com.sp.model.domain.SysBusiness;


/**
 * @Author:
 * @Descripition:
 * @Date: 2024/7/19
 */
public interface UserCenterService {
    BaseResponse updateUserCenterInfo(SysBusiness business);

    BaseResponse updateLogo(SysBusiness business);
}
