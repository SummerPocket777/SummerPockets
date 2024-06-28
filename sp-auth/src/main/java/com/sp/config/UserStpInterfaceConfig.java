package com.sp.config;
import cn.dev33.satoken.stp.StpInterface;
import org.springframework.context.annotation.Configuration;


import java.util.Arrays;
import java.util.List;


/**
 * 用户STP接口配置
 *
 * @author 罗汉
 * @date 2024/06/27
 */
@Configuration
public class UserStpInterfaceConfig implements StpInterface {
    /**
     * 获取权限列表
     *
     * @param loginId   登录id
     * @param loginType 登录类型
     * @return {@link List }<{@link String }>
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {

//        return StpUtil.getSession().get(PERMISSION_LIST);
        return Arrays.asList("user:add","user:edit","user:list");
    }

    /**
     * 获取角色列表
     *
     * @param loginId   登录id
     * @param loginType 登录类型
     * @return {@link List }<{@link String }>
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
//        假设自己是管理员 学生 老师
        return Arrays.asList("admin","student","teacher");
    }
}
