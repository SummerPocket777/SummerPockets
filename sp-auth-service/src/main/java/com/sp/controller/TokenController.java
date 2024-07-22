package com.sp.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;

import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.model.domain.ConsumerUser;
import com.sp.model.domain.SysBusiness;
import com.sp.model.dto.VXUserLoginDTO;
import com.sp.model.vo.BusinessLoginVO;
import com.sp.model.vo.BusinessRegisterVO;
import com.sp.model.vo.VXUserLoginVO;
import com.sp.service.ConsumerUserService;
import com.sp.service.SysBusinessService;
import com.sp.utils.RedisCacheUtil;
import com.sp.utils.VXUserStpUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 令牌控制器  先在这里登录
 *
 * @author 罗汉
 * @date 2024/06/27
 */
@RestController
@RequestMapping("/user/")
//允许跨域
//@CrossOrigin(origins = "*")
public class TokenController {
    @Resource
    private SysBusinessService sysBusinessService;
    @Resource
    private ConsumerUserService consumerUserService;

    @PostMapping("doLogin")
    public BaseResponse doLogin(@RequestBody BusinessLoginVO vo) {
        if (vo==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = vo.getUsername();
        String userPassword = vo.getPassword();
        if (StringUtils.isEmpty(userAccount) || StringUtils.isEmpty(userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String token = sysBusinessService.login(vo);

        return ResultUtils.success(token);
    }

    /**
     * wx登录
     *
     * @param vo 签证官
     * @return {@link BaseResponse }
     */
    @PostMapping("wxLogin")
    public BaseResponse wxLogin(@RequestBody VXUserLoginVO vo) {
        if (vo==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String code = vo.getCode();
        if (!StringUtils.hasText(code)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        VXUserLoginDTO vxUserLoginDTO= consumerUserService.vxLogin(vo);
        return ResultUtils.success(vxUserLoginDTO);
    }

    /**
     * 查询登录状态
     *
     * @return {@link SaResult }
     */
    @GetMapping("isLogin")
    public BaseResponse isLogin() {
        boolean isLogin = StpUtil.isLogin() || VXUserStpUtil.isLogin();
        if (isLogin) {
            return ResultUtils.success();
        } else {
            return ResultUtils.error(ErrorCode.NOT_LOGIN);
        }
    }


    /**
     * 查询 Token 信息
     *
     * @return {@link SaResult }
     */
    @GetMapping("tokenInfo")
    public BaseResponse tokenInfo() {

        return ResultUtils.success(StpUtil.getTokenInfo());
    }

    /**
     * 注销登录
     *
     * @return {@link SaResult }
     */
    @GetMapping("logout")
    public BaseResponse logout() {
        StpUtil.logout();
        return ResultUtils.success();
    }

    /**
     * 注册
     *
     * @param vo 签证官
     * @return {@link BaseResponse }
     */
    @PostMapping("register")
    public BaseResponse register(@RequestBody BusinessRegisterVO vo) {
        if (vo==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id= sysBusinessService.registerBusiness(vo);
        return ResultUtils.success(id);
    }
    @GetMapping("getUserDetail")
    public BaseResponse getUserDetail() {
        SysBusiness sysBusiness = (SysBusiness)StpUtil.getTokenSession().get("user");
        if (sysBusiness==null){
            Long loginId = StpUtil.getLoginIdAsLong();
            sysBusiness = sysBusinessService.getById(loginId);
        }
        return ResultUtils.success(sysBusiness);
    }

}
