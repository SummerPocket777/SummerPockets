package com.sp.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class WeChatProperties {
    @Value("wxd89743bd342689d7")
    private String appid; //小程序的appid
    @Value("72241fb2ea7878a3714093b756072f88")
    private String secret; //小程序的秘钥
    @Value("1607151211")
    private String mchid; //商户号
    @Value("64E206DD37F5BA316DCE094723739F5E93035FAE")
    private String mchSerialNo; //商户API证书的证书序列号
    @Value("e10adc3949ba59abbe56e057f20f883e")
    private String apiV3Key; //证书解密的密钥
    private String privateKeyFilePath; //商户私钥文件
    private String weChatPayCertFilePath; //平台证书
    private String notifyUrl; //支付成功的回调地址
    private String refundNotifyUrl; //退款成功的回调地址
}
