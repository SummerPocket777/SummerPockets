package com.sp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.sp.mapper")
public class SpConsumerBookApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpConsumerBookApplication.class,args);
    }
}
