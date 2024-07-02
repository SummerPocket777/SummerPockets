package com.sp.controller;

import java.util.ArrayList;
import java.util.List;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.model.domain.SysBusiness;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;

/**
 * Sa-Token 权限认证示例 
 * 
 * @author click33
 * @since 2022-10-13
 */
@RestController
@RequestMapping("/jur/")
public class JurAuthController {

	public static volatile List<SysBusiness> sysBusinessList=new ArrayList<>();

	static {//新建三个
//		第一个
		SysBusiness sysBusiness = new SysBusiness();
		sysBusiness.setAccount("zhang");
		sysBusiness.setPassword("123456");
		sysBusiness.setRoleId(1);
		sysBusinessList.add(sysBusiness);
//		第二个
		SysBusiness sysBusiness2 = new SysBusiness();
		sysBusiness2.setAccount("lisi");
		sysBusiness2.setPassword("123456");
		sysBusiness2.setRoleId(2);
		sysBusinessList.add(sysBusiness2);
//		第三个
		SysBusiness sysBusiness3 = new SysBusiness();
		sysBusiness3.setAccount("wangwu");
		sysBusiness3.setPassword("123456");
		sysBusiness3.setRoleId(3);
		sysBusinessList.add(sysBusiness3);
	}

	/*
	 * 前提1：首先调用登录接口进行登录，代码在 com.pj.cases.use.LoginAuthController 中有详细解释，此处不再赘述 
	 * 		---- http://localhost:8081/acc/doLogin?name=zhang&pwd=123456
	 * 
	 * 前提2：项目实现 StpInterface 接口，代码在  com.pj.satoken.StpInterfaceImpl
	 * 		Sa-Token 将从此实现类获取 每个账号拥有哪些权限。
	 * 
	 * 然后我们就可以使用以下示例中的代码进行鉴权了 
	 */

	@GetMapping("list")
	public SaResult getList() {
		StpUtil.getPermissionList().forEach(System.out::println);
		//鉴权代码
		if(StpUtil.hasPermission("user:list")) {
			return SaResult.data("当前账号有 user.list 权限");
		}
		return SaResult.error("当前账号没有 user.list 权限");
	}

	/**
	 * 查询权限
	 *
	 * @return {@link SaResult }
	 */
	@RequestMapping("getPermission")
	public SaResult getPermission() {
		// 查询权限信息 ，如果当前会话未登录，会返回一个空集合
		List<String> permissionList = StpUtil.getPermissionList();
		System.out.println("当前登录账号拥有的所有权限：" + permissionList);

		// 查询角色信息 ，如果当前会话未登录，会返回一个空集合
		List<String> roleList = StpUtil.getRoleList();
		System.out.println("当前登录账号拥有的所有角色：" + roleList);

		// 返回给前端
		return SaResult.ok()
				.set("roleList", roleList)
				.set("permissionList", permissionList);
	}

	/**
	 * 权限校验
	 *
	 * @return {@link BaseResponse }
	 */
	@RequestMapping("checkPermission")
	public BaseResponse checkPermission() {

		// 判断：当前账号是否拥有一个权限，返回 true 或 false
		// 		如果当前账号未登录，则永远返回 false
		StpUtil.hasPermission("user.add");
		StpUtil.hasPermissionAnd("user.add", "user.delete", "user.get");  // 指定多个，必须全部拥有才会返回 true
		StpUtil.hasPermissionOr("user.add", "user.delete", "user.get");     // 指定多个，只要拥有一个就会返回 true

		// 校验：当前账号是否拥有一个权限，校验不通过时会抛出 `NotPermissionException` 异常
		// 		如果当前账号未登录，则永远校验失败
		StpUtil.checkPermission("user.add");
		StpUtil.checkPermissionAnd("user.add", "user.delete", "user.get");  // 指定多个，必须全部拥有才会校验通过
		StpUtil.checkPermissionOr("user.add", "user.delete", "user.get");  // 指定多个，只要拥有一个就会校验通过

		return ResultUtils.success();
	}

	/**
	 * 角色校验
	 *
	 * @return {@link SaResult }
	 */
	@RequestMapping("checkRole")
	public SaResult checkRole() {

		// 判断：当前账号是否拥有一个角色，返回 true 或 false
		// 		如果当前账号未登录，则永远返回 false
		StpUtil.hasRole("admin");
		StpUtil.hasRoleAnd("admin", "ceo", "cfo");  // 指定多个，必须全部拥有才会返回 true
		StpUtil.hasRoleOr("admin", "ceo", "cfo");      // 指定多个，只要拥有一个就会返回 true

		// 校验：当前账号是否拥有一个角色，校验不通过时会抛出 `NotRoleException` 异常
		// 		如果当前账号未登录，则永远校验失败
		StpUtil.checkRole("admin");
		StpUtil.checkRoleAnd("admin", "ceo", "cfo");  // 指定多个，必须全部拥有才会校验通过
		StpUtil.checkRoleOr("admin", "ceo", "cfo");  // 指定多个，只要拥有一个就会校验通过

		return SaResult.ok();
	}

	/**
	 * 权限通配符
	 *
	 * @return {@link SaResult }
	 */
	@RequestMapping("wildcardPermission")
	public SaResult wildcardPermission() {

		// 前提条件：在 StpInterface 实现类中，为账号返回了 "art.*" 泛权限
		StpUtil.hasPermission("art.add");  // 返回 true
		StpUtil.hasPermission("art.delete");  // 返回 true
		StpUtil.hasPermission("goods.add");  // 返回 false，因为前缀不符合

		// * 符合可以出现在任意位置，比如权限码的开头，当账号拥有 "*.delete" 时
		StpUtil.hasPermission("goods.add");        // false
		StpUtil.hasPermission("goods.delete");     // true
		StpUtil.hasPermission("art.delete");      // true

		// 也可以出现在权限码的中间，比如当账号拥有 "shop.*.user" 时
		StpUtil.hasPermission("shop.add.user");  // true
		StpUtil.hasPermission("shop.delete.user");  // true
		StpUtil.hasPermission("shop.delete.goods");  // false，因为后缀不符合

		// 注意点：
		// 1、上帝权限：当一个账号拥有 "*" 权限时，他可以验证通过任何权限码
		// 2、角色校验也可以加 * ，指定泛角色，例如： "*.admin"，暂不赘述

		return SaResult.ok();
	}



}
