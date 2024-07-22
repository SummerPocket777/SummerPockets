package com.sp;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class    consumerDishService {
    public static void main( String[] args )
    {
        SpringApplication.run(consumerDishService.class,args);
        // 写值测试：注意一定要用下列方法测试，不要用自己封装的 RedisUtil 之类的测试
        SaManager.getSaTokenDao().set("name3consumer-dish", "测试consumer-dish模块", 100000);
    }
}
