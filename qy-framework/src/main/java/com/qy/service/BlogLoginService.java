package com.qy.service;

import com.qy.domian.dto.UserDTO;
import com.qy.domian.vo.BlogUserLoginVo;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 16:55
 **/
public interface BlogLoginService {
    BlogUserLoginVo login(UserDTO user);

    void logout();
}
