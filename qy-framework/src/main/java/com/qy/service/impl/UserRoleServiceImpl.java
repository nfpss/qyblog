package com.qy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.domian.entity.UserRoleDO;
import com.qy.mapper.UserRoleMapper;
import com.qy.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2022-12-16 15:01:35
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleDO> implements UserRoleService {

}


