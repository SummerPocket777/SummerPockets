package com.sp.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.mapper.ConsumerBookMapper;
import com.sp.pojo.ConsumerBook;
import com.sp.service.ConsumerBookService;
import org.springframework.stereotype.Service;


/**
* @author cc129
* @description 针对表【consumer_book】的数据库操作Service实现
* @createDate 2024-07-01 14:23:51
*/
@Service
public class ConsumerBookServiceImpl extends ServiceImpl<ConsumerBookMapper, ConsumerBook>
    implements ConsumerBookService {

    @Override
    public long bookInsert(ConsumerBook consumerBook) {
        if (consumerBook==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return this.save(consumerBook)?consumerBook.getBookId():0;
    }
}




