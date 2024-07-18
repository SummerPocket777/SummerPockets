package com.sp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.core.enums.ErrorCode;
import com.sp.core.exception.BusinessException;
import com.sp.mapper.SysBusinessMapper;
import com.sp.model.PageResult;
import com.sp.model.domain.Setmeal;
import com.sp.model.domain.SysBusiness;
import com.sp.model.vo.SysBusinessVO;
import com.sp.service.SysBusinessService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【sys_business(商家表)】的数据库操作Service实现
* @createDate 2024-06-28 12:43:04
*/
@Service
public class SysBusinessServiceImpl extends ServiceImpl<SysBusinessMapper, SysBusiness>
    implements SysBusinessService{
    @Resource
    private SysBusinessMapper sysBusinessMapper;

    /**
     * 获得安全店铺 后面可以用权限进行区分显示
     *
     * @param business 业务对象
     * @return 安全的SysBusiness对象
     */
    @Override
    public SysBusiness getSafetyBusiness(SysBusiness business) {
        if (business == null) {
            return null;
        }
        SysBusiness safeBusiness = new SysBusiness();
        safeBusiness.setId(business.getId());
        safeBusiness.setAccount(business.getAccount());
        safeBusiness.setAddressId(business.getAddressId());
        safeBusiness.setParentId(business.getParentId());
        // 如果电话号码需要部分隐藏，例如显示前三位和后四位，中间用星号代替
        String phone = business.getPhone();
        if (phone != null && phone.length() >= 7) {
            safeBusiness.setPhone(phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4));
        }

        // 如果email需要部分隐藏，例如显示前三个字符，后面用星号代替
        String email = business.getEmail();
        if (email != null && email.length() > 3) {
            safeBusiness.setEmail(email.substring(0, 3) + "****" + email.substring(email.indexOf('@')));
        }

//        safeBusiness.setLogoUrl(business.getLogoUrl());
        safeBusiness.setStatus(business.getStatus());
        safeBusiness.setCreateTime(business.getCreateTime());
        safeBusiness.setUpdateTime(business.getUpdateTime());
        safeBusiness.setIsDelete(business.getIsDelete());
        safeBusiness.setRoleId(business.getRoleId());
        return safeBusiness;
    }

    /**
     * 添加业务
     *
     * @param sysBusiness 系统业务
     * @return long
     */
    @Override
    public long addBusiness(SysBusiness sysBusiness) {
        if (sysBusiness == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return this.save(sysBusiness)?sysBusiness.getId():0;
    }

    /**
     * 更新业务
     *
     * @param sysBusinessVO 系统业务
     * @return long
     */
    @Override
    public boolean updateBusiness(SysBusinessVO sysBusinessVO) {
        // 校验输入参数sysBusinessVO是否为null
        if (sysBusinessVO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long id = sysBusinessVO.getId();
        // 校验ID是否有效
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 根据ID查询旧的团队信息
        SysBusiness oldBusiness = this.getById(id);

        // 校验旧队伍是否存在
        if (oldBusiness == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        BeanUtils.copyProperties(sysBusinessVO, oldBusiness);
        oldBusiness.setUpdateTime(new Date());
        return this.updateById(oldBusiness);
    }

    /**
     * 分页查询
     *
     * @param sysBusinessVO 系统业务
     * @return {@link PageResult }<{@link SysBusiness }>
     */
    @Override
    public PageResult<SysBusiness> pageQuery(SysBusinessVO sysBusinessVO) {
        if (sysBusinessVO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long PageNo = sysBusinessVO.getPageNo();
        Long pageSize = sysBusinessVO.getPageSize();

        // 打印分页参数
        System.out.println("PageNo: " + PageNo);
        System.out.println("PageSize: " + pageSize);

        // 设置分页参数
        Page<SysBusiness> page = new Page<>(PageNo, pageSize);
        QueryWrapper<SysBusiness> queryWrapper = new QueryWrapper<>();

        // 打印查询条件
        System.out.println("QueryWrapper: " + queryWrapper);

        sysBusinessMapper.selectPage(page, queryWrapper);

        // 获取分页数据
        List<SysBusiness> list = page.getRecords();

        // 打印查询结果
        System.out.println("Query Result: " + list);

        //测试查询全部数据并且打印出来
        List<SysBusiness> allList = sysBusinessMapper.selectList(null);
        System.out.println("All List: " + allList);

        PageResult<SysBusiness> pageResult = new PageResult<>();
        pageResult.setPageNo(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalRow(page.getTotal());
        pageResult.setPageTotalCount(page.getPages());
        pageResult.setItems(list);
        if (page.getTotal() == 0) {
            pageResult.setHasNext(false);
            pageResult.setHasPrevious(false);
        } else {
            pageResult.setHasNext(page.hasNext());
            pageResult.setHasPrevious(page.hasPrevious());
        }
        return pageResult;
    }

}




