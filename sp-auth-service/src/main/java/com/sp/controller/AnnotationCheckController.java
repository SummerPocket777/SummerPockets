package com.sp.controller;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录检查注解示例Controller。
 * 这个Controller展示了不同注解在权限检查和安全控制方面的应用。
 */
@RestController
public class AnnotationCheckController {

    /**
     * 使用@SaCheckLogin注解，表示该方法需要用户已登录才能访问。
     * @return 如果用户已登录，返回成功消息。
     */
    @SaCheckLogin
    @GetMapping("login")
    public SaResult anno1() {
        return SaResult.ok("恭喜你，通过了校验！ლ(°◕‵ƹ′◕ლ)，(●´З｀●)");
    }

    /**
     * 使用@SaCheckRole注解，表示该方法需要用户具有特定角色才能访问。
     * @return 如果用户具有指定角色，返回成功消息。
     */
    @SaCheckRole("admin1")
    @GetMapping("role")
    public SaResult anno2() {
        return SaResult.ok("恭喜你，通过了校验！ლ(°◕‵ƹ′◕ლ)，(●´З｀●)");
    }

    /**
     * 使用@SaCheckPermission注解，表示该方法需要用户具有特定权限才能访问。
     * @return 如果用户具有指定权限，返回成功消息。
     */
    @SaCheckPermission("user:add")
    @GetMapping("permission")
    public SaResult anno3() {
        return SaResult.ok("恭喜你，通过了校验！ლ(°◕‵ƹ′◕ლ)，(●´З｀●)");
    }

    /**
     * 使用@SaCheckSafe注解，表示该方法需要通过二级认证才能访问。
     * @return 如果用户通过了二级认证，返回成功消息。
     */
    @SaCheckSafe
    @GetMapping("safe")
    public SaResult anno5() {
        return SaResult.ok("恭喜你，通过了校验！ლ(°◕‵ƹ′◕ლ)，(●´З｀●)");
    }

    /**
     * 执行登录操作，并设置二级认证和会话信息。
     * @return 如果登录成功，返回成功消息并开启二级认证。
     */
    @GetMapping("doLogin")
    public SaResult doLogin() {
        StpUtil.login("admin");
        SaSession session = StpUtil.getSession();
        session.set("WQJ", "王清江给我添加了二级认证，美滋滋");
        long timeout = StpUtil.getStpLogic().getConfigOrGlobal().getTimeout();
        SaManager.getSaTokenDao().setSession(session, timeout);
        StpUtil.openSafe(99999);
        return SaResult.ok("恭喜你，登录成功了呢，而且还开启了二级认证！");
    }

    /**
     * 使用@SaIgnore注解，表示该方法忽略所有权限检查。
     * 即使方法上还有其他权限注解，也会被忽略。
     * @return 总是返回成功消息。
     */
    @SaIgnore()
    @GetMapping("ignore")
    @SaCheckPermission("user:add1111111111")
    public SaResult anno6() {
        return SaResult.ok("恭喜你，通过了校验！ლ(°◕‵ƹ′◕ლ)，(●´З｀●)");
    }

    /**
     * 使用@SaCheckDisable注解，检查用户是否被禁用。
     * 默认检查登录权限是否被禁用。
     * @return 如果用户未被禁用，返回成功消息。
     */
    @SaCheckDisable()
    @GetMapping("disable")
    public SaResult anno7() {
        return SaResult.ok("恭喜你，通过了校验！ლ(°◕‵ƹ′◕ლ)，(●´З｀●)");
    }

    /**
     * 封禁指定用户。
     * @return 如果用户被成功封禁，返回成功消息。
     */
    @GetMapping("disableAdmin")
    public SaResult anno8() {
        StpUtil.disable("admin", 99999);
        return SaResult.ok("admin被封了99999秒，这狗东西，真实不识好歹！");
    }
}
