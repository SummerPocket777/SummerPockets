package com.sp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.model.domain.Voucher;
import com.sp.model.domain.VoucherUser;
import com.sp.service.VoucherService;
import com.sp.mapper.VoucherMapper;
import com.sp.utils.VoucherCodeTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher>
    implements VoucherService{

    @Resource
    private VoucherMapper voucherMapper;

    @Override
    public int createdVoucher(Voucher voucher) {
        return voucherMapper.createdVoucher(voucher);
    }

    @Override
    public List<VoucherUser> userQuery(int userId) {
        return voucherMapper.userQuery(userId);
    }

    @Override
    public Voucher getById(int id) {
        return voucherMapper.getById(id);
    }

    @Override
    public int issueVoucher(int userId, int orderAmount) {
        // 1.查找该面值的优惠券类型 --> 券id
        int voucherId = voucherMapper.getIdByPayValue(orderAmount);
        // 2.生成唯一券给该用户（用户id，券id，券码，状态） --> VoucherUser
        String voucherUniqueCode = VoucherCodeTool.generateVoucherCode(userId, voucherId); // -----------------------------------------
        // 3.设置 唯一券 相关信息
        VoucherUser voucherUser = new VoucherUser();
        voucherUser.setUserId(userId);
        voucherUser.setVoucherId(voucherId);
        voucherUser.setVoucherUniqueCode(voucherUniqueCode);
        voucherUser.setStatus(1);
        return voucherMapper.issueVoucher(voucherUser);
    }

}




