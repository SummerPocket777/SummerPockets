package com.sp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sp.model.PageResult;
import com.sp.model.domain.SysBusiness;
import com.sp.pojo.ConsumerBook;
import com.sp.dto.BookTo;
import com.sp.vo.BookInfovo;
import com.sp.vo.BookPageQueryVo;
import com.sp.vo.BookVo;

import java.util.List;

/**
* @author cc129
* @description 针对表【consumer_book】的数据库操作Service
* @createDate 2024-07-01 14:23:51
*/
public interface ConsumerBookService extends IService<ConsumerBook> {
    long bookInsert(ConsumerBook consumerBook);
    PageResult<ConsumerBook> pageQuery(BookPageQueryVo bookPageQueryVo);
    List<BookTo> getBNameById(Long id);

    List<BookInfovo> getAllInfo(Long id , String consumerName);

    void updateBook(Integer status, Long id);

    List<SysBusiness> selectAll();

    List<BookVo> getYuyueList(Long userID);

    boolean cancelBook(Long id);

    long getConsumerId(String name);

}
