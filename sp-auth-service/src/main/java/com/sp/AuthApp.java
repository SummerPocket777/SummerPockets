package com.sp;

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

    }
    @PostConstruct
    public void printSMSProperties() {
        System.out.println("SMS Properties: " + smsProperties);
    }
}
