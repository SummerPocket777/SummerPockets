package com.sp.config;

import com.sp.model.domain.Voucher;
import com.sp.model.domain.VoucherUser;

public class VoucherDetailDTO {
    private VoucherUser voucherUser;
    private Voucher voucher;

    public VoucherDetailDTO(VoucherUser voucherUser, Voucher voucher) {
        this.voucherUser = voucherUser;
        this.voucher = voucher;
    }

    public VoucherUser getVoucherUser() {
        return voucherUser;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public String getTitle() {
        return voucher.getTitle();
    }

    public String getRules() {
        return voucher.getRules();
    }

    public String getExpirationTime() {
        return voucher.getExpirationTime();
    }
}
