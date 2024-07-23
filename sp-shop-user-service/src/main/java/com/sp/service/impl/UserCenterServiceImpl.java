package com.sp.service.impl;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.mapper.UserCenterMapper;
import com.sp.model.domain.SysBusiness;
import com.sp.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:
 * @Descripition:
 * @Date: 2024/7/19
 */
@Service
public class UserCenterServiceImpl implements UserCenterService {
    @Autowired
    private UserCenterMapper sysBusinessMapper;
    @Override
    public BaseResponse updateUserCenterInfo(SysBusiness business) {
        if (business == null){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        int result = sysBusinessMapper.updateUserCenterInfo(business);
        if (result >0){
            return ResultUtils.success();
        }
        return ResultUtils.error(ErrorCode.PARAMS_ERROR);
    }
}
