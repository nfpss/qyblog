package com.qy.service.impl;

import com.qy.config.RedisCache;
import com.qy.constants.SystemConst;
import com.qy.domian.dto.UserDTO;
import com.qy.domian.entity.LoginUser;
import com.qy.domian.vo.BlogUserLoginVo;
import com.qy.domian.vo.UserInfoVo;
import com.qy.exception.BizException;
import com.qy.response.AppHttpCodeEnum;
import com.qy.service.BlogLoginService;
import com.qy.utils.BeanCopyUtils;
import com.qy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 16:56
 **/
@Service
public class BlogLoginServiceImpl implements BlogLoginService {


    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public BlogUserLoginVo login(UserDTO user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if (Objects.isNull(authenticate)) {
            throw new BizException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long id = loginUser.getUserDO().getId();
        redisCache.setCacheObject(String.format(SystemConst.BLOG_LONG_USER_KEY, id), loginUser);
        String token = JwtUtil.createJWT(loginUser.getUserDO().getId().toString());
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUserDO(), UserInfoVo.class);
        BlogUserLoginVo loginVo = BlogUserLoginVo.builder()
                .userInfo(userInfoVo)
                .token(token)
                .build();
        return loginVo;
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUserDO().getId();
        redisCache.deleteObject(String.format(SystemConst.BLOG_LONG_USER_KEY,id));
    }
}
