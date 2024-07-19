package com.sp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sp.core.common.BaseResponse;
import com.sp.core.common.ResultUtils;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.model.domain.SysBusiness;
import com.sp.pojo.ConsumerBook;
import com.sp.service.ConsumerBookService;
import com.sp.dto.BookTo;
import com.sp.vo.BookInfovo;
import com.sp.vo.BookVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        // 使用BeanUtils工具类，将SysBusinessVO对象的属性复制到SysBusiness对象中1
        BeanUtils.copyProperties(bookVo, consumerBook);
        consumerBook.setBookCreatedate(new Date());
        consumerBook.setIsStatus(1L);
        // 调用业务服务，添加业务信息，并返回新增业务的ID
        long bookId = consumerBookService.bookInsert(consumerBook);
        // 构建成功响应，包含新增业务的ID
        return ResultUtils.success(bookId);
    }
    @GetMapping("/selectPage")
    public BaseResponse<List<ConsumerBook>> list(){
        QueryWrapper<ConsumerBook> consumerBookQueryWrapper = new QueryWrapper<>();
        List<ConsumerBook> list = consumerBookService.list(consumerBookQueryWrapper);
        return ResultUtils.success(list);
    }
    @GetMapping("/getBNameById/{id}")
    public BaseResponse<List<BookTo>> getBNameById(@PathVariable("id") Long id){
        List<BookTo> list = consumerBookService.getBNameById(id);
        return ResultUtils.success(list);
    }

    @GetMapping("/getAllInfo")
    public BaseResponse<List<BookInfovo>> getAllInfo(@PathVariable("consumerId") Long id , @PathVariable("consumerName") String consumerName){
        List<BookInfovo> list = consumerBookService.getAllInfo(id,consumerName);
        return ResultUtils.success(list);
    }
    @RequestMapping("/updateStatus")
    private BaseResponse<String> updateOrderStatus(@RequestParam("status")Integer status,@RequestParam("id")Long  id){
        consumerBookService.updateBook(status, id);
        return ResultUtils.success("更新成功");
    }

    @RequestMapping("/selectAllBusiness")
    private BaseResponse<List<SysBusiness>> selectAllBusiness(){
        List<SysBusiness> list = consumerBookService.selectAll();
        return ResultUtils.success(list);
    }

    @GetMapping ("/getYuyueLists")
    private BaseResponse<List<BookVo>> getYuyueList(Long userID){
        List<BookVo> list = consumerBookService.getYuyueList(userID);
        return ResultUtils.success(list);
    }

    @RequestMapping("/cancelBook")
    private BaseResponse<String> cancelBook(@RequestParam("bookId")Long id){
        if (consumerBookService.cancelBook(id)){
            return ResultUtils.success("取消成功");
        }else {
            return ResultUtils.success("取消失败");
        }

    }
}
