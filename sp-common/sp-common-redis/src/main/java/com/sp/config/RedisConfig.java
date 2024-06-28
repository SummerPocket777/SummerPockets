package com.sp.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


/**

 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //开始进行序列化
        FastJsonRedisSerializer<String> stringFastJsonRedisSerializer = new FastJsonRedisSerializer<String>(String.class);
        //对value值进行序列化
        redisTemplate.setValueSerializer(stringFastJsonRedisSerializer);
        //对key值进行序列化
        redisTemplate.setKeySerializer(stringFastJsonRedisSerializer);

        redisTemplate.setHashValueSerializer(stringFastJsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringFastJsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
