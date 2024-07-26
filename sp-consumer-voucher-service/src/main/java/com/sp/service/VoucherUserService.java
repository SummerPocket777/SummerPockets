package com.sp.service;

import com.sp.config.VoucherDetailDTO;
import com.sp.model.domain.Voucher;
import com.sp.model.domain.VoucherUser;

import java.util.List;

public interface VoucherUserService {
    List<Voucher> getValidVouchers();
    List<Voucher> getExpiredVouchers();
    List<Voucher> getUsedVouchers(int userId);




}
