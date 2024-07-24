package com.sp.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.mapper.WXConsumerUserServiceMapper;
import com.sp.model.domain.ConsumerUser;


import com.sp.model.dto.VXUserLoginDTO;
import com.sp.model.vo.VXUserLoginVO;
import com.sp.properties.WeChatProperties;
import com.sp.service.ConsumerUserService;
import com.sp.utils.HttpClientUtil;
import com.sp.utils.VXUserStpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
* @author Administrator
* @description 针对表【sys_business(商家表)】的数据库操作Service实现
* @createDate 2024-06-28 12:43:04
*/
@Service
public class ConsumerUserServiceImpl extends ServiceImpl<WXConsumerUserServiceMapper, ConsumerUser>
    implements ConsumerUserService {
    private static final String WX_LOGIN ="https://api.weixin.qq.com/sns/jscode2session";
    @Resource
    private WXConsumerUserServiceMapper userMapper;

    @Resource
    private WeChatProperties weChatProperties;
    /**
     * vx登录
     *
     * @param vo 签证官
     * @return {@link VXUserLoginDTO }
     */
    @Override
    public VXUserLoginDTO vxLogin(VXUserLoginVO vo) {
        //1 调用微信接口服务,获得当前微信用户的openid
        String openid = getOpenid(vo.getCode());

        //2 判断openid是否为空，如果为空表示登录失败，抛出业务异常
        if (openid==null){
            throw new BusinessException(ErrorCode.WX_LOGIN_FAILED);
        }

        //3 判断当前用户是否为新用户
        QueryWrapper<ConsumerUser> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("openid",openid);
        ConsumerUser user = userMapper.selectOne(objectQueryWrapper);
        //3.1 如果是新用户 自动完成注册
        if (user==null){
            user= ConsumerUser.builder()
                    .openid(openid)
                    //date类型
                    .createTime(new Date())
                    .build();
            userMapper.insert(user);
        }
        //4 设置token
        VXUserStpUtil.login(user.getId());
        VXUserStpUtil.getTokenSession().set("vxuser", user);
        String tokenValue = VXUserStpUtil.getTokenValue();

        //5 返回这个用户对象
        VXUserLoginDTO userLoginVO = VXUserLoginDTO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(tokenValue)
                .build();

        return userLoginVO;
    }
    /**
     * 得到openid
     *
     * @param code 用户登录dto
     * @return {@link String}
     */
    private String getOpenid(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, map);

        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}




