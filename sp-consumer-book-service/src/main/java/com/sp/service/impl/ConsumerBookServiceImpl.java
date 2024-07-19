package com.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.mapper.ConsumerBookMapper;
import com.sp.mapper.SysbusinessMapper;
import com.sp.model.PageResult;
import com.sp.model.domain.SysBusiness;
import com.sp.pojo.ConsumerBook;
import com.sp.service.ConsumerBookService;
import com.sp.dto.BookTo;
import com.sp.vo.BookInfovo;
import com.sp.vo.BookPageQueryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
* @author cc129
* @description 针对表【consumer_book】的数据库操作Service实现
* @createDate 2024-07-01 14:23:51
*/
@Service
public class ConsumerBookServiceImpl extends ServiceImpl<ConsumerBookMapper, ConsumerBook>
    implements ConsumerBookService {
    @Resource
    private ConsumerBookMapper consumerBookMapper;

    @Resource
    private SysbusinessMapper sysbusinessMapper;


    @Override
    public long bookInsert(ConsumerBook consumerBook) {
        if (consumerBook==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return this.save(consumerBook)?consumerBook.getBookId():0;
    }

    @Override
    public PageResult<ConsumerBook> pageQuery(BookPageQueryVo bookPageQueryVo) {
        int pageNO = bookPageQueryVo.getPageNo();
        int pageSize = bookPageQueryVo.getPageSize();
        //设置分页参数
        Page<ConsumerBook> page = new Page<>(pageNO,pageSize);
        QueryWrapper<ConsumerBook> queryWrapper = new QueryWrapper<>();
        consumerBookMapper.selectPage(page,queryWrapper);
        //获取分页数据
        List<ConsumerBook> list = page.getRecords();
        PageResult<ConsumerBook> pageResult = new PageResult<>();
        pageResult.setPageNo(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setPageTotalCount(page.getPages());
        pageResult.setTotalRow(page.getTotal());
        pageResult.setItems(list);
        if (page.getTotal()==0){
            pageResult.setHasNext(false);
            pageResult.setHasPrevious(false);
        }else {
            pageResult.setHasNext(page.hasNext());
            pageResult.setHasPrevious(page.hasPrevious());
        }
        return pageResult;
    }

    @Override
    public List<BookTo> getBNameById(Long id) {
        return consumerBookMapper.getBNameById(id);
    }


    @Override
    //更新订单详情状态(用于上菜，退菜）
    public void updateBook(Integer status, Long id) {
        consumerBookMapper.updateStatus(status,id);
    }

    @Override
    public List<SysBusiness> selectAll() {
        return sysbusinessMapper.selectList(null);
    }

    @Override
    public List<BookInfovo> getAllInfo(Long id, String consumerName) {
        //QueryWrapper查找返回集合
        return consumerBookMapper.getAllByUserId(id,consumerName);
    }

    @Override
    public List<ConsumerBook> getYuyueList(Long userID) {
        return sysbusinessMapper.getYuyueList(userID);
    }

    @Override
    public boolean cancelBook(Long id) {
        QueryWrapper<ConsumerBook> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id",id);
        return consumerBookMapper.delete(queryWrapper) >0;
    }
}




