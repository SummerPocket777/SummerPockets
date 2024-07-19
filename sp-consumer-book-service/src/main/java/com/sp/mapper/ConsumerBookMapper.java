package com.sp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sp.pojo.ConsumerBook;
import com.sp.dto.BookTo;
import com.sp.vo.BookInfovo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author cc129
* @description 针对表【consumer_book】的数据库操作Mapper
* @createDate 2024-07-01 14:28:53
* @Entity generator.domain.ConsumerBook
*/
public interface ConsumerBookMapper extends BaseMapper<ConsumerBook> {
    List<BookTo> getBNameById(Long id);

    List<BookInfovo> getAllByUserId(@Param("id") Long id , @Param("consumerName")String consumerName);

    void updateStatus(Integer status, Long id);
}




