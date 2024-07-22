package com.sp.mapper;

import com.sp.model.domain.SysBusiness;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:
 * @Descripition:
 * @Date: 2024/7/22
 */
@Mapper
public interface UserCenterMapper {
    int updateUserCenterInfo(SysBusiness business);
}
