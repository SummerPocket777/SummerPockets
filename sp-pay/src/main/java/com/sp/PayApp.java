package com.sp;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(basePackages = "com.sp.mapper")
public class PayApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(PayApp.class,args);
    }


}
