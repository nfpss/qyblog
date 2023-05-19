package com.qy.utils;

import com.qy.constants.SystemConst;
import com.qy.domian.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/15 14:49
 **/
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin() {
        return getUserId() == SystemConst.ROLE_BY_ADMIN;
    }

    public static Long getUserId() {
        return getLoginUser().getUserDO().getId();
    }
}
