package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.ConsumerBook;
import generator.service.ConsumerBookService;
import generator.mapper.ConsumerBookMapper;
import org.springframework.stereotype.Service;

/**
* @author cc129
* @description 针对表【consumer_book】的数据库操作Service实现
* @createDate 2024-07-03 14:26:18
*/
@Service
public class ConsumerBookServiceImpl extends ServiceImpl<ConsumerBookMapper, ConsumerBook>
    implements ConsumerBookService{

}




