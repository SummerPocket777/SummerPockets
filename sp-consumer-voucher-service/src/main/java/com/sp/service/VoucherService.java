package com.sp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sp.model.domain.Voucher;
import com.sp.model.domain.VoucherUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface VoucherService extends IService<Voucher> {

    /**
     * 创建一种优惠券
     * @return 返回结果（是否成功）
     */
    int createdVoucher(Voucher voucher);

    /**
     * 查询某用户下的所有优惠券
     * @param userId 用户id
     * @return 优惠券信息列表
     */
    List<VoucherUser> userQuery(@Param("userId") int userId);

    /**
     * 查询某优惠券的信息
     * @param id 优惠券id
     * @return 优惠券信息
     */
    Voucher getById(@Param("id") int id);

    /**
     * 发放优惠券
     * @param userId 用户id
     * @return 发放结果
     */
    int issueVoucher(int userId, int orderAmount);

}
