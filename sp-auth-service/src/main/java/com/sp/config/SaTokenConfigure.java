package com.sp.config;

import cn.dev33.satoken.context.SaHolder;

import cn.dev33.satoken.filter.SaServletFilter;

import cn.dev33.satoken.same.SaSameUtil;

import cn.dev33.satoken.util.SaResult;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*") // 使用 allowedOriginPatterns 代替 allowedOrigins
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册 Sa-Token 拦截器，定义详细认证规则
//        registry.addInterceptor(new SaInterceptor(handler -> {
//                    // 指定一条 match 规则
//                    SaRouter
//                            .match("/**")    // 拦截的 path 列表，可以写多个
//                            .notMatch("/user/doLogin",
//                                    "/user/wxLogin",
//                                    "/anno/doLogin",
//                                    "/user/isLogin",
//                                    "/business/getCode",
//                                    "/captcha/getCode")
//                            .check(r -> {
//                                // 打印当前请求的 URI
//                                System.out.println("当前请求的 URI：" + SaHolder.getRequest().getRequestPath());
//                                if (!StpUtil.isLogin() && !VXUserStpUtil.isLogin()) {
//                                    throw new BusinessException(ErrorCode.NOT_LOGIN, "未登录");
//                                }
//                            });
//                }) // 让注解鉴权不要生效
//        ).addPathPatterns("/**");
//    }

    // 注册 Sa-Token 全局过滤器
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico")
                .setAuth(obj -> {
                    // 校验 Same-Token 身份凭证     —— 以下两句代码可简化为：SaSameUtil.checkCurrentRequestToken();
                    String token = SaHolder.getRequest().getHeader(SaSameUtil.SAME_TOKEN);
                        SaSameUtil.checkToken(token);
                })
                .setError(e -> {
                    return new SaResult(40101, e.getMessage(), null);
                });
    }
}
