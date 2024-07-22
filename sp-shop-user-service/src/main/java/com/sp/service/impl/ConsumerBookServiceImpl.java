package com.sp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.pojo.ConsumerBook;
import com.sp.service.ConsumerBookService;
import com.sp.mapper.ConsumerBookMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【consumer_book】的数据库操作Service实现
* @createDate 2024-07-18 17:58:45
*/
@Service
public class ConsumerBookServiceImpl extends ServiceImpl<ConsumerBookMapper, ConsumerBook>
    implements ConsumerBookService{

}



