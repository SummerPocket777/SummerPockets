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
public class consumerUserService
{
    public static void main( String[] args )
    {
        SpringApplication.run(consumerUserService.class, args);
    }
}
