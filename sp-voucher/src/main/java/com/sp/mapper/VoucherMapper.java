package com.sp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sp.model.domain.Voucher;
import com.sp.model.domain.VoucherUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Entity com.sp.pojo.Voucher
 */
public interface VoucherMapper extends BaseMapper<Voucher> {


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
     * @param voucherUser 优惠券信息
     * @return 发放结果
     */
    int issueVoucher(VoucherUser voucherUser);

    /**
     * 根据订单付款金额获取券id
     * @param payValue 订单付款金额
     * @return 券id
     */
    int getIdByPayValue(@Param("payValue") int payValue);


}




