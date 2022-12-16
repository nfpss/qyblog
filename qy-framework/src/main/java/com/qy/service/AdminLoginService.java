package com.qy.service;

import com.qy.domian.dto.UserDTO;
import com.qy.domian.vo.AdminUserInfo;
import com.qy.domian.vo.RouterVO;

import java.util.Map;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/16 14:04
 **/

public interface AdminLoginService {
    Map<String, String> login(UserDTO user);

    AdminUserInfo getInfo();

    RouterVO getRouters();

}
