package com.sp.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis Plus配置
 *
 * @author 罗汉
 * @date 2023/07/28
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * MyBatis Plus拦截器
     *
     * @return {@link MybatisPlusInterceptor}
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        //新建分页插件
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
    //分页插件2配置
    // <!-- 分页插件 -->
    //     <dependency>
    //         <groupId>com.github.pagehelper</groupId>
    //         <artifactId>pagehelper</artifactId>
    //         <version>5.2.0</version>
    //     </dependency>

}
