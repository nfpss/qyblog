package com.qy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.domian.entity.RoleMenuDO;
import com.qy.mapper.RoleMenuMapper;
import com.qy.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2022-12-16 15:01:35
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenuDO> implements RoleMenuService {

}


