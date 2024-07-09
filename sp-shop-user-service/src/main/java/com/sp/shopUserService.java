package com.sp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@MapperScan("com.sp.mapper")
@SpringBootApplication
public class shopUserService
{
    public static void main( String[] args )
    {

        SpringApplication.run(shopUserService.class, args);
    }
}
