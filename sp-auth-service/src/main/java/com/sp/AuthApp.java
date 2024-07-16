package com.sp;

import cn.dev33.satoken.SaManager;
import com.sp.properties.SMSProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.sp.mapper")
public class AuthApp {
    @Resource
    private SMSProperties smsProperties;
    public static void main( String[] args ) {
        SpringApplication.run(AuthApp.class, args);
        // 写值测试：注意一定要用下列方法测试，不要用自己封装的 RedisUtil 之类的测试
        SaManager.getSaTokenDao().set("name1auth", "测试auth模块", 100000);

    }
    @PostConstruct
    public void printSMSProperties() {
        System.out.println("SMS Properties: " + smsProperties);
    }
}
