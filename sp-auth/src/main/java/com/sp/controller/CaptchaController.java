package com.sp.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Console;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码 Controller
 *
 * @author 罗汉
 * @date 2024/07/04
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @GetMapping("/getCode")
    public void getCaptcha(HttpServletResponse response) throws IOException {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

        // 将验证码写出到客户端
        response.setContentType("image/png");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        lineCaptcha.write(response.getOutputStream());

        // 打印验证码的值 (仅用于测试，生产环境请移除)
        Console.log(lineCaptcha.getCode());
    }
}
