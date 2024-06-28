package com.sp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.mapper.SysBusinessMapper;
import com.sp.model.domain.SysBusiness;
import com.sp.model.vo.SysBusinessVO;
import com.sp.service.SysBusinessService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author Administrator
* @description 针对表【sys_business(商家表)】的数据库操作Service实现
* @createDate 2024-06-28 12:43:04
*/
@Service
public class SysBusinessServiceImpl extends ServiceImpl<SysBusinessMapper, SysBusiness>
    implements SysBusinessService{

    /**
     * 获得安全店铺 后面可以用权限进行区分显示
     *
     * @param business 业务对象
     * @return 安全的SysBusiness对象
     */
    @Override
    public SysBusiness getSafetyBusiness(SysBusiness business) {
        if (business == null) {
            return null;
        }
        SysBusiness safeBusiness = new SysBusiness();
        safeBusiness.setId(business.getId());
        safeBusiness.setAccount(business.getAccount());
        safeBusiness.setAddressId(business.getAddressId());
        safeBusiness.setParentId(business.getParentId());
        // 如果电话号码需要部分隐藏，例如显示前三位和后四位，中间用星号代替
        String phone = business.getPhone();
        if (phone != null && phone.length() >= 7) {
            safeBusiness.setPhone(phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4));
        }

        // 如果email需要部分隐藏，例如显示前三个字符，后面用星号代替
        String email = business.getEmail();
        if (email != null && email.length() > 3) {
            safeBusiness.setEmail(email.substring(0, 3) + "****" + email.substring(email.indexOf('@')));
        }

        safeBusiness.setLogoUrl(business.getLogoUrl());
        safeBusiness.setStatus(business.getStatus());
        safeBusiness.setCreateTime(business.getCreateTime());
        safeBusiness.setUpdateTime(business.getUpdateTime());
        safeBusiness.setIsDelete(business.getIsDelete());
        safeBusiness.setRoleId(business.getRoleId());
        return safeBusiness;
    }

    /**
     * 添加业务
     *
     * @param sysBusiness 系统业务
     * @return long
     */
    @Override
    public long addBusiness(SysBusiness sysBusiness) {
        if (sysBusiness == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return this.save(sysBusiness)?sysBusiness.getId():0;
    }

    /**
     * 更新业务
     *
     * @param sysBusinessVO 系统业务
     * @return long
     */
    @Override
    public boolean updateBusiness(SysBusinessVO sysBusinessVO) {
        // 校验输入参数sysBusinessVO是否为null
        if (sysBusinessVO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long id = sysBusinessVO.getId();
        // 校验ID是否有效
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 根据ID查询旧的团队信息
        SysBusiness oldBusiness = this.getById(id);

        // 校验旧队伍是否存在
        if (oldBusiness == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        BeanUtils.copyProperties(sysBusinessVO, oldBusiness);
        oldBusiness.setUpdateTime(new Date());
        return this.updateById(oldBusiness);
    }
}




