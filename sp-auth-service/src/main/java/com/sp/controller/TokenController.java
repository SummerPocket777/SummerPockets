package com.sp.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.constants.RedisConstants;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.model.vo.BusinessLoginVO;
import com.sp.model.vo.BusinessRegisterVO;
import com.sp.service.SysBusinessService;
import com.sp.utils.RedisCacheUtil;
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


    @PostMapping("doLogin")
    public BaseResponse doLogin(@RequestBody BusinessLoginVO vo) {
        if (vo==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = vo.getUsername();
        String userPassword = vo.getPassword();
        if (StringUtils.isEmpty(userAccount)||StringUtils.isEmpty(userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String token= sysBusinessService.login(vo);

        return ResultUtils.success(token);
    }

    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @GetMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @GetMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @GetMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    //注册
    @PostMapping("register")
    public BaseResponse register(@RequestBody BusinessRegisterVO vo) {
        if (vo==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id= sysBusinessService.registerBusiness(vo);
        return ResultUtils.success(id);
    }
}
