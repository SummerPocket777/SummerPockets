package com.sp.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 踢人下线接口
 *
 * @author 罗汉
 * @date 2024/07/01
 */
@RestController
@RequestMapping("/kickOut")
public class KickOutController {


    /**
     * 踢人下线
     *
     * @param loginId 登录id
     * @return {@link SaResult }
     */
    @GetMapping("/kickOut1/{loginId}")
    public SaResult kickOut1(@PathVariable Long loginId) {
        StpUtil.kickout(loginId);
        return SaResult.ok("踢人下线成功啦，ლ(°◕‵ƹ′◕ლ) , 乖乖");
    }

    /**
     * 踢人下线(踢自己)
     *
     * @param loginId 登录id
     * @return {@link SaResult }
     */
    @GetMapping("/kickOut2/{loginId}")
    public SaResult kickOut2(@PathVariable Long loginId) {
        StpUtil.kickout(loginId, StpUtil.getLoginDevice());   // 获取自己登录的设备，然后把自己踢了
        return SaResult.ok("踢人下线成功啦，ლ(°◕‵ƹ′◕ლ) , 乖乖");
    }

    /**
     * 踢人下线（token）
     *
     * @param token 令牌
     * @return {@link SaResult }
     */
    @GetMapping("/kickOut3/{token}")
    public SaResult kickOut3(@PathVariable String token) {
        StpUtil.kickoutByTokenValue(token);
        return SaResult.ok("踢人下线成功啦，ლ(°◕‵ƹ′◕ლ) , 乖乖");
    }


}