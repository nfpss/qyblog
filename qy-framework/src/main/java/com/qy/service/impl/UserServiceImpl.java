package com.qy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.domian.entity.UserDO;
import com.qy.mapper.UserMapper;
import com.qy.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2022-12-14 16:29:16
 */
@Service("sysUserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}


