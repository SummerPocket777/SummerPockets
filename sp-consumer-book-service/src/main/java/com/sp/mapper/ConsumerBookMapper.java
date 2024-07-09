package com.sp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sp.pojo.ConsumerBook;
import com.sp.dto.BookTo;

import java.util.List;

/**
* @author cc129
* @description 针对表【consumer_book】的数据库操作Mapper
* @createDate 2024-07-01 14:28:53
* @Entity generator.domain.ConsumerBook
*/
public interface ConsumerBookMapper extends BaseMapper<ConsumerBook> {
    List<BookTo> getBNameById(Long consumerId);
}




