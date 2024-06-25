package com.sp.core.exception;

import com.sp.core.domain.BaseResponse;
import com.sp.core.domain.ResultUtils;
import com.sp.core.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理程序
 *
 * @author 罗汉
 * @date 2023/06/22
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 业务异常处理程序
     *
     * @param e e
     * @return {@link BaseResponse}
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e) {
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    /**
     * 运行时异常处理程序
     *
     * @param e e
     * @return {@link BaseResponse}
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
    }

}
