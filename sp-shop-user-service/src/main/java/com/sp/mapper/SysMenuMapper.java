package com.sp.mapper;

import com.sp.model.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.sp.pojo.SysMenu
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> getMenuTree();

    List<SysMenu> getSubMenuList(Integer id);
}




