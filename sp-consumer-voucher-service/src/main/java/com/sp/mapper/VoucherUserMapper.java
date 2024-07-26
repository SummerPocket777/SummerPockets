package com.sp.mapper;

import com.sp.model.domain.Voucher;
import com.sp.model.domain.VoucherUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface VoucherUserMapper {

    List<Voucher> findValidVouchers();

    List<Voucher> findExpiredVouchers();

    List<Voucher> findUsedVouchers(@Param("userId") int userId);


}
