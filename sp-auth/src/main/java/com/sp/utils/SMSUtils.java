package com.sp.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.properties.SMSProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * smsutil
 * 短信发送工具
 *
 * @author 罗汉
 * @date 2023/06/22
 */

@Log4j2
@Component
public class SMSUtils {

    private static SMSProperties smsProperties;

    @Resource
    private SMSProperties tempProperties;

    /**
     * 发送消息
     *
     * @param phoneNum 电话号码
     * @param code     密码
     */
    public static BaseResponse sendMessage(String phoneNum, String code) {
        log.info("RegionId: " + smsProperties.getRegionId());
        log.info("AccessKey: " + smsProperties.getAccessKey());
        log.info("SecretKey: " + smsProperties.getSecretKey());
        log.info("SignName: " + smsProperties.getSignName());
        log.info("TemplateCode: " + smsProperties.getTemplateCode());
        log.info("TemplateParam: " + smsProperties.getTemplateParam());

        IClientProfile profile = DefaultProfile.getProfile(
                smsProperties.getRegionId(),
                smsProperties.getAccessKey(),
                smsProperties.getSecretKey()
        );
        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNum);
        request.setSignName(smsProperties.getSignName());
        request.setTemplateCode(smsProperties.getTemplateCode());

        // 使用 String.format 或者手动拼接 JSON 字符串
        request.setTemplateParam("{\"" + smsProperties.getTemplateParam() + "\":\"" + code + "\"}");
        try {
            SendSmsResponse response = client.getAcsResponse(request);
            log.info("发送结果: " + response.getMessage());
            if (!"OK".equals(response.getCode())) {
                throw new BusinessException(ErrorCode.SMS_SEND_FAILED, response.getMessage());
            }
        } catch (ClientException e) {
            log.error("发送短信异常", e);
            throw new BusinessException(ErrorCode.SMS_SEND_FAILED, "发送短信异常");
        }
        return ResultUtils.success("短信发送成功");
    }



    /**
     * init属性
     */
    @PostConstruct
    public void initProperties() {
        smsProperties = tempProperties;
    }
}
