package com.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.model.PageResult;
import com.sp.pojo.SysBusiness;
import com.sp.service.SysBusinessService;
import com.sp.mapper.SysBusinessMapper;
import com.sp.vo.BusinessVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author cc129
* @description 针对表【sys_business(商家表)】的数据库操作Service实现
* @createDate 2024-07-19 10:53:27
*/
@Service
public class SysBusinessServiceImpl extends ServiceImpl<SysBusinessMapper, SysBusiness>
    implements SysBusinessService{
    @Resource
    private SysBusinessMapper sysBusinessMapper;

    @Override
    public PageResult<SysBusiness> pageQuery(BusinessVo businessVo) {
        int pageNO = businessVo.getPageNo();
        int pageSize = businessVo.getPageSize();
        //设置分页参数
        Page<SysBusiness> page = new Page<>(pageNO,pageSize);
        QueryWrapper<SysBusiness> queryWrapper = new QueryWrapper<>();
        sysBusinessMapper.selectPage(page,queryWrapper);
        //获取分页数据
        List<SysBusiness> list = page.getRecords();
        PageResult<SysBusiness> pageResult = new PageResult<>();
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
}




