package com.sp.controller;

import com.sp.model.domain.Voucher;

import com.sp.core.utils.ResponseDTO;
import com.sp.model.domain.VoucherUser;
import com.sp.service.VoucherService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/voucher")
public class VoucherController {

    @Resource
    private VoucherService voucherService;
    private ResponseDTO dto = null;

    @RequestMapping("/create")
    public ResponseDTO create(@RequestBody Voucher voucher) {
        int i = voucherService.createdVoucher(voucher);
        if (i == 1) {
            dto = new ResponseDTO(1001, "优惠券生成成功", null);
        } else {
            dto = new ResponseDTO(1000, "优惠券生成失败", null);
        }
        return dto;
    }

    @RequestMapping("/query/user")
    public ResponseDTO userQuery(@Param("userId") int userId) {
        List<VoucherUser> voucherUsers = voucherService.userQuery(userId);
        if (voucherUsers.size() > 0) {
            dto = new ResponseDTO(1021, "该用户优惠券查询成功", voucherUsers);
        } else if (voucherUsers.size() == 0) {
            dto = new ResponseDTO(1022, "用户优惠券查询成功，该用户无优惠券", voucherUsers);
        } else {
            dto = new ResponseDTO(1020, "该用户优惠券查询失败", null);
        }
        return dto;
    }


    @RequestMapping("/query")
    public ResponseDTO getById(@Param("id") int id) {
        Voucher voucher = voucherService.getById(id);
        if (voucher != null) {
            dto = new ResponseDTO(1031, "优惠券信息获取成功", voucher);
        } else {
            dto = new ResponseDTO(1030, "优惠券信息获取失败", null);
        }
        return dto;
    }

    @RequestMapping("/issue")
    public ResponseDTO issue(@Param("orderId") int orderId, @Param("userId") int userId) {
        // 1.获取订单编号 --- 查是否支付成功，查订单金额 -------------------------------
        int orderAmount = 95;

        // 2.根据不同的订单的金额发放不同的券
        int i = voucherService.issueVoucher(userId, orderAmount);
        if (i == 1) {
            dto=  new ResponseDTO(1011, "优惠券发放成功", null);
        } else {
            dto=  new ResponseDTO(1010, "优惠券发放失败", null);
        }
        return dto;
    }


}
