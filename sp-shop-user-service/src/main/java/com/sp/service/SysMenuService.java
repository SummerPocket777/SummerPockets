package com.sp.service;

import com.sp.model.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> getMenuTree();

}
