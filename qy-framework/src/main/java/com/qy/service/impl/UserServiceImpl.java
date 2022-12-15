package com.qy.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.constants.SystemConst;
import com.qy.domian.dto.UserDTO;
import com.qy.domian.entity.UserDO;
import com.qy.domian.vo.UserInfoVo;
import com.qy.exception.BizException;
import com.qy.mapper.UserMapper;
import com.qy.response.AppHttpCodeEnum;
import com.qy.service.UserService;
import com.qy.utils.BeanCopyUtils;
import com.qy.utils.SecurityUtils;
import com.qy.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2022-12-14 16:29:16
 */
@Service("sysUserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfoVo getUserInfo() {
        UserDO user = getById(SecurityUtils.getUserId());
        return BeanCopyUtils.copyBean(user, UserInfoVo.class);
    }

    @Override
    public void updateUserInfo(UserDO user) {
        updateById(user);
    }

    @Override
    public void register(UserDTO userDO) {
        ValidatorUtils.valid(userDO);
        UserDO checkUserName = getOne(Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUserName, userDO.getUserName()));
        if (Objects.nonNull(checkUserName)) {
            throw new BizException(AppHttpCodeEnum.PARAM_ERROR.getCode(), SystemConst.REGISTER_USERNAME);
        }
        UserDO checkNickName = getOne(Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getNickName, userDO.getNickName()));
        if (Objects.nonNull(checkNickName)) {
            throw new BizException(AppHttpCodeEnum.PARAM_ERROR.getCode(), SystemConst.REGISTER_NICKNAME);
        }
        UserDO checkEmail = getOne(Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getEmail, userDO.getEmail()));
        if (Objects.nonNull(checkEmail)) {
            throw new BizException(AppHttpCodeEnum.PARAM_ERROR.getCode(), SystemConst.REGISTER_EMAIL);
        }
        UserDO saveBean = BeanCopyUtils.copyBean(userDO, UserDO.class);
        save(saveBean.setPassword(passwordEncoder.encode(userDO.getPassword())));
    }
}


