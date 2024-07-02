package com.sp.controller;

import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.pojo.ConsumerBook;
import com.sp.service.ConsumerBookService;
import com.sp.vo.BookVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/book")
public class UserBookController {
    @Resource
    private ConsumerBookService consumerBookService;

    @PostMapping("/add")
    public BaseResponse<Long> addBusiness(@RequestBody BookVo bookVo) {
        // 校验输入参数是否为空，若为空则抛出业务异常
        if (bookVo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 创建SysBusiness实例，用于存储业务信息
        ConsumerBook consumerBook = new ConsumerBook();
        // 使用BeanUtils工具类，将SysBusinessVO对象的属性复制到SysBusiness对象中
        BeanUtils.copyProperties(bookVo, consumerBook);
        consumerBook.setBookDate(new Date());
        // 调用业务服务，添加业务信息，并返回新增业务的ID
        long bookId = consumerBookService.bookInsert(consumerBook);
        // 构建成功响应，包含新增业务的ID
        return ResultUtils.success(bookId);
    }

}