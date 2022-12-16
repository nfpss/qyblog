package com.qy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.domian.entity.RoleDO;
import com.qy.mapper.RoleMapper;
import com.qy.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2022-12-16 15:01:35
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {

}


