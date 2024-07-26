package com.sp.controller;

import com.sp.model.domain.Voucher;
import com.sp.service.VoucherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vouchers")
public class VoucherUserController {

    @Autowired
    private VoucherUserService voucherUserService;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/valid")
    public List<Voucher> getValidVouchers() {
        return voucherUserService.getValidVouchers();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/expired")
    public List<Voucher> getExpiredVouchers() {
        return voucherUserService.getExpiredVouchers();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/used/{userId}")
    public List<Voucher> getUsedVouchers(@PathVariable int userId) {
        return voucherUserService.getUsedVouchers(userId);
    }

}
