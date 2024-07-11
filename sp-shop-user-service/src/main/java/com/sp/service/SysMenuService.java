package com.sp.service;

import com.sp.mapper.SysMenuMapper;
import com.sp.model.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> getMenuTree();

}
