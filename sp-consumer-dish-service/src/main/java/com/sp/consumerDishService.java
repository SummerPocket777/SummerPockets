package com.sp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class consumerDishService {
    public static void main( String[] args )
    {
        SpringApplication.run(consumerDishService.class,args);
        System.out.println( "Hello World!" );
    }
}
