package com.sp.config;


import com.sp.properties.AliOssProperties;
import com.sp.properties.QiNiuProperties;
import com.sp.utils.AliOssUtil;
import com.sp.utils.QiNiuUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，用于创建阿里oss工具类对象
 * @author :罗汉
 * @date : 2023/8/5
 */
@Configuration
@Slf4j
public class OssConfiguration {
    @Bean
    @ConditionalOnMissingBean//这个注解是保证整个容器里只有这一个对象
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
        log.info("开始上传阿里云文件上传工具类对象:{}",aliOssProperties);
       return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }
    @Bean
    @ConditionalOnMissingBean//这个注解是保证整个容器里只有这一个对象
    public QiNiuUtils qiNiuUtils(QiNiuProperties qiNiuProperties){
        log.info("开始上传七牛云文件上传工具类对象:{}",qiNiuProperties);
        QiNiuUtils qiNiuUtils = new QiNiuUtils();
        qiNiuUtils.initProperties();
        return qiNiuUtils;
     }
}
