package com.sp.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.sp.core.constants.RedisConstants;
import com.sp.utils.RedisCacheUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码 Controller
 *
 * @author 罗汉
 * @date 2024/07/04
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Resource
    private RedisCacheUtil redisCacheUtil;

    @GetMapping("/getCode")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

        // 获取当前会话的 sessionId
        String sessionId = request.getSession().getId();
        String key = RedisConstants.REGISTER_IMG_CODE_KEY + sessionId;

        // 将验证码存入 Redis 中，并设置有效期
        redisCacheUtil.setCacheObject(key, lineCaptcha.getCode(), RedisConstants.REGISTER_IMG_CODE_TTL, TimeUnit.MINUTES);

        // 将验证码写出到客户端
        response.setContentType("image/png");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        lineCaptcha.write(response.getOutputStream());
    }
}
