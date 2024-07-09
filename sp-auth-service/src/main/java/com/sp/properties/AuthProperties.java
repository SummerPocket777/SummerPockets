package com.sp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 罗汉
 */
@Data
@ConfigurationProperties(prefix = "auth")
@Component
public class AuthProperties {

    /**
     * 使用真实短信服务
     */
    private boolean useShortMessagingService = false;
}
