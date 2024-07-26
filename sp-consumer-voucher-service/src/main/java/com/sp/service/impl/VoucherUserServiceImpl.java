package com.sp.service.impl;

import com.sp.config.VoucherDetailDTO;
import com.sp.mapper.VoucherUserMapper;
import com.sp.model.domain.Voucher;
import com.sp.model.domain.VoucherUser;
import com.sp.service.VoucherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class VoucherUserServiceImpl implements VoucherUserService {
    @Autowired
    private VoucherUserMapper voucherUserMapper;

    @Override
    public List<Voucher> getValidVouchers() {
        return voucherUserMapper.findValidVouchers();
    }

    @Override
    public List<Voucher> getExpiredVouchers() {
        return voucherUserMapper.findExpiredVouchers();
    }

    @Override
    public List<Voucher> getUsedVouchers(int userId) {
        return voucherUserMapper.findUsedVouchers(userId);
    }


}
