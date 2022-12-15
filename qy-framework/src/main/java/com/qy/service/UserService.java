package com.qy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qy.domian.dto.UserDTO;
import com.qy.domian.entity.UserDO;
import com.qy.domian.vo.UserInfoVo;


/**
 * 用户表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2022-12-14 16:29:16
 */
public interface UserService extends IService<UserDO> {

    UserInfoVo getUserInfo();

    void updateUserInfo(UserDO user);

    void register(UserDTO userDTO);
}
