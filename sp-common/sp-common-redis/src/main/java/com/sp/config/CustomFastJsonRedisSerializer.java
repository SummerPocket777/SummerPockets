package com.sp.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

public class CustomFastJsonRedisSerializer<T> implements RedisSerializer<T> {

    private final Class<T> clazz;

    public CustomFastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
        ParserConfig.getGlobalInstance().addAccept("com.sp.model."); // 添加白名单
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, Charset.forName("UTF-8"));
        return JSON.parseObject(str, clazz);
    }
}
