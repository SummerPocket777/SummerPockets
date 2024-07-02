package com.sp.service.impl;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.mapper.SysBusinessMapper;
import com.sp.model.domain.SysBusiness;
import com.sp.model.vo.BusinessLoginVO;
import com.sp.model.vo.SysBusinessVO;
import com.sp.service.SysBusinessService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sp.core.constants.SystemConstants.SALT;
import static com.sp.core.constants.UserConstants.MINIMUM_ACCOUNT_LEN;
import static com.sp.core.constants.UserConstants.MINIMUM_PASSWORD_LEN;

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

    /**
     * 登录
     *
     * @param vo 签证官
     * @return {@link String }
     */
    @Override
    public String login(BusinessLoginVO vo) {
        if (vo == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //1 效验
        validateAccPwd(vo.getUsername(),vo.getPassword());
        //2 查询数据库
        SysBusiness userInDatabase = getUserInDatabase(vo.getUsername(),vo.getPassword());
        // 3. 用户脱敏
        SysBusiness safetyUser = getSafetyBusiness(userInDatabase);
        // 会话登录：参数填写要登录的账号id，建议的数据类型：long | int | String， 不可以传入复杂类型，如：User、Admin 等等
        StpUtil.login(safetyUser.getId());
        StpUtil.getTokenSession().set("user", safetyUser);
        return StpUtil.getTokenValue();
    }


    /**
     * 验证acc PWD
     *
     * @param userAccount  用户帐户
     * @param userPassword 用户密码
     */
    public void validateAccPwd(String userAccount, String userPassword) {
        if (!StringUtils.hasText(userAccount) || !StringUtils.hasText(userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        if (userAccount.length() < MINIMUM_ACCOUNT_LEN) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号非法");
        }
        if (userPassword.length() < MINIMUM_PASSWORD_LEN) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码非法");
        }
        // 账户不能包含特殊字符
        String validPattern = "[^[a-zA-Z][a-zA-Z0-9_]{4,15}$]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (!matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号非法");
        }
    }

    /**
     * 在数据库中获取用户
     *
     * @param userAccount 用户帐户
     * @param password
     * @return {@link SysBusiness }
     */
    private SysBusiness getUserInDatabase(String userAccount, String password) {
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 查询用户是否存在
        LambdaQueryWrapper<SysBusiness> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysBusiness::getAccount, userAccount);
        SysBusiness userInDatabase = this.getOne(userLambdaQueryWrapper);
        if (userInDatabase == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在");
        }
        if (!userInDatabase.getPassword().equals(encryptPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        if (!userInDatabase.getStatus().equals(1)) {
            throw new BusinessException(ErrorCode.FORBIDDEN, "该用户已被封禁");
        }
        return userInDatabase;
    }
}




