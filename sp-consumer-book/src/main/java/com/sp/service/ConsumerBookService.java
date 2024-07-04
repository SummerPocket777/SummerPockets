package com.sp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sp.model.PageResult;
import com.sp.pojo.ConsumerBook;
import com.sp.vo.BookPageQueryVo;

/**
* @author cc129
* @description 针对表【consumer_book】的数据库操作Service
* @createDate 2024-07-01 14:23:51
*/
public interface ConsumerBookService extends IService<ConsumerBook> {
    long bookInsert(ConsumerBook consumerBook);
    PageResult<ConsumerBook> pageQuery(BookPageQueryVo bookPageQueryVo);
}
