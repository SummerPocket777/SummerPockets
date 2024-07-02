package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;

import com.sp.core.constants.RedisConstants;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.core.utils.ValidateCodeUtils;
import com.sp.utils.MessageUtils;
import com.sp.utils.RedisCacheUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 第三方阿里
 *
 * @author 罗汉
 * @date 2024/07/01
 */
@RestController
@RequestMapping("/business/")
public class AliController {

    @Resource
    private RedisCacheUtil redisCacheUtil;

    /**
     * 发送验证码
     *
     * @param phone 电话
     * @return {@link BaseResponse}<{@link String}>
     */
    @GetMapping("/getCode")
    @Deprecated
    public BaseResponse<String> sendMessage(String phone) {
        if (!StringUtils.hasText(phone)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"手机号不能为空");
        }
        Integer code = ValidateCodeUtils.generateValidateCode();
        String key = RedisConstants.REGISTER_CODE_KEY + phone;
        String phoneCode = redisCacheUtil.getCacheObject(key);
        if (phoneCode != null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请稍后再试~ "+RedisConstants.REGISTER_CODE_TTL+TimeUnit.MINUTES);
        }
        redisCacheUtil.setCacheObject(key, code, RedisConstants.REGISTER_CODE_TTL, TimeUnit.MINUTES);
        MessageUtils.sendMessage(phone, String.valueOf(code));
        return ResultUtils.success("短信发送成功");
    }
//
//    /**
//     * 发送手机更新消息
//     *
//     * @param phone   电话
//     * @param request 请求
//     * @return {@link BaseResponse}<{@link String}>
//     */
//    @Deprecated
//    @GetMapping("/message/update/phone")
//
//    public BaseResponse<String> sendPhoneUpdateMessage(String phone, HttpServletRequest request) {
//        User loginUser = userService.getLoginUser(request);
//        if (loginUser == null) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN);
//        }
//        if (StringUtils.isBlank(phone)) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        Integer code = ValidateCodeUtils.generateValidateCode();
//        String key = RedisConstants.USER_UPDATE_PHONE_KEY + phone;
//        stringRedisTemplate.opsForValue().set(key, String.valueOf(code), RedisConstants.USER_UPDATE_PHONE_TTL, TimeUnit.MINUTES);
//        MessageUtils.sendMessage(phone, String.valueOf(code));
//        return ResultUtils.success("短信发送成功");
//    }
}
