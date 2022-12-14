package com.qy.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qy.domian.entity.LoginUser;
import com.qy.domian.entity.UserDO;
import com.qy.response.AppHttpCodeEnum;
import com.qy.exception.BizException;
import com.qy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 17:15
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDO userDO = userService.getOne(Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUserName, s));
        if (Objects.isNull(userDO)) {
            throw new BizException(AppHttpCodeEnum.USER_NOT_EXIST);
        }
        // TODO: 2022/12/14 权限定义为null；
        return new LoginUser(userDO, Collections.emptyList());
    }
}
