package com.qy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qy.domian.dto.AddUserDTO;
import com.qy.domian.dto.UpdateUserDTO;
import com.qy.domian.dto.UserDTO;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.entity.UserDO;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.UserInfoVo;
import com.qy.domian.vo.admin.AdminUserInfoVO;
import com.qy.domian.vo.admin.AdminUserVO;

import java.util.Map;


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

    PageVO<AdminUserVO> listByCondition(PageParameterHelper pageParameterHelper, String userName, String phonenumber, String status);

    void add(AddUserDTO addUserDTO);

    void deleteById(Long id);

    AdminUserInfoVO getInfo(Long id);

    void update(UpdateUserDTO userDTO);

    void changstatus(Map<String, Object> map);
}
