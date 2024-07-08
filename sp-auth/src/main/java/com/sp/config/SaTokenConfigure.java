package com.sp.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.SameTokenInvalidException;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.sp.core.common.ResultUtils;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，定义详细认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
                    // 指定一条 match 规则
                    SaRouter
                            .match("/**")    // 拦截的 path 列表，可以写多个
                            .notMatch("/user/doLogin", "/anno/doLogin", "/business/getCode", "/captcha/getCode")
                            .check(r -> {
                                System.out.println("亲亲，我进来了");
                                StpUtil.checkLogin();
                            });        // 要执行的校验动作，可以写完整的 lambda 表达式
                    // 根据路由划分模块，不同模块不同鉴权
                    SaRouter.match("/user/**")
                            .notMatch("/user/doLogin") // 放登录接口一条生路
                            .check(r -> StpUtil.checkPermission("user:add"));  // 假设有user:add就可以访问全部 /user/**的路径了
                    SaRouter.match("/anno/**", r -> {
                        System.out.println("我只想证明，还可以写多行代码呢！");
                        StpUtil.checkPermission("我是不存在的权限字符串，嘻嘻");
                    });
                    SaRouter.match("/**").check(r -> System.out.println("6~"));
                }) // 让注解鉴权不要生效
        ).addPathPatterns("/**");
    }

    // 注册 Sa-Token 全局过滤器
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico")
                .setAuth(obj -> {
                    // 校验 Same-Token 身份凭证     —— 以下两句代码可简化为：SaSameUtil.checkCurrentRequestToken();
                    String token = SaHolder.getRequest().getHeader(SaSameUtil.SAME_TOKEN);

//                    try {
                        SaSameUtil.checkToken(token);
//                    } catch (SameTokenInvalidException e) {
//
//                       throw new BusinessException(ErrorCode.NO_AUTH);
//                    }
                })
                .setError(e -> {
//                   throw new BusinessException(ErrorCode.SYSTEM_ERROR,e.getMessage());
                    return new SaResult(40101, e.getMessage(), null);
//                    return ResultUtils.error(ErrorCode.SYSTEM_ERROR,"不能直接访问服务");
                });
    }
}
