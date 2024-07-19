package com.sp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sp.model.domain.SysBusiness;
import com.sp.pojo.ConsumerBook;
import com.sp.vo.BookVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 旭
 * @Date: 2024/07/18 14:43
 * @description
 */
public interface SysbusinessMapper extends BaseMapper<SysBusiness> {
    List<BookVo> getYuyueList(@Param("userID") Long userID);

    int getIdByName(@Param("businessName") String businessName);

}
