package com.sp.utils;


import com.sp.properties.AuthProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 消息utils
 *
 * @author 罗汉
 * @date 2024/01/25
 */
@Log4j2
@Component
public class MessageUtils {
//    private static AuthProperties findFriendProperties;
//
//    @Resource
//    private AuthProperties tempProperties;


    /**
     * 发送消息
     *
     * @param phoneNum 电话号码
     * @param code     密码
     */
    public static void sendMessage(String phoneNum, String code) {
//        if (findFriendProperties.isUseShortMessagingService()) {
            SMSUtils.sendMessage(phoneNum, code);
//        } else {
            log.info("验证码: " + code);
//        }
    }
//
//    /**
//     * init属性
//     */
//    @PostConstruct
//    public void initProperties() {
//        findFriendProperties = tempProperties;
//    }
}
