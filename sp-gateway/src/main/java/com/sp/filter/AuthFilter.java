package com.sp.filter;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.sp.config.properties.IgnoreWhiteProperties;

import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.utils.VXUserStpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [Sa-Token 权限认证] 拦截器
 *
 * @author Lion Li
 */
@Configuration
@Slf4j
public class AuthFilter {

    /**
     * 注册 Sa-Token 全局过滤器
     */
    @Bean
    public SaReactorFilter getSaReactorFilter(IgnoreWhiteProperties ignoreWhite) {

        return new SaReactorFilter()
            // 拦截地址
            .addInclude("/**")
            .addExclude("/favicon.ico", "/actuator/**")
            // 鉴权方法：每次访问进入
            .setAuth(obj -> {
                System.err.println(ignoreWhite.getWhites());
                // 打印当前请求的 URI
                System.out.println("当前请求的 URI：" + SaHolder.getRequest().getRequestPath());
                // 登录校验 -- 拦截所有路由
                SaRouter.match("/**")
                    .notMatch(ignoreWhite.getWhites())
                    .check(r -> {
//                         检查是否登录 是否有token
                        if (!VXUserStpUtil.isLogin() && !StpUtil.isLogin()) {
                            throw new BusinessException(ErrorCode.NOT_LOGIN);
                        }
                    });
            }).setError(e -> {
                log.error("网关过滤器的异常: ", e); // 打印完整的堆栈信息
                if (e instanceof BusinessException) {
                    return SaResult.error(e.getMessage()).setCode(((BusinessException) e).getCode());
                }
                return SaResult.error("认证失败，无法访问系统资源").setCode(40101);
            });
    }
}
