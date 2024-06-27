package com.sp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 罗汉
 */
@Data
@ConfigurationProperties(prefix = "file")
@Component
public class FileUploadProperties {

    /**
     * 本地图片位置
     */
    private String img;

    /**
     * 启用布隆过滤器
     */
    private boolean enableBloomFilter;

    /**
     * 使用图片本地存储
     */
    private boolean useLocalStorage;


    private boolean useAliOss;


    private boolean useQiNiu;
}
