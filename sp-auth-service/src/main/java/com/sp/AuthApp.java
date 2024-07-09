package com.sp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.sp.mapper")
public class AuthApp {
    public static void main( String[] args ) {
        SpringApplication.run(AuthApp.class, args);
    }
}
