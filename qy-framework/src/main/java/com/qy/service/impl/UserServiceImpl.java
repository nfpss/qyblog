package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.constants.SystemConst;
import com.qy.domian.dto.AddUserDTO;
import com.qy.domian.dto.UpdateUserDTO;
import com.qy.domian.dto.UserDTO;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.entity.UserDO;
import com.qy.domian.entity.UserRoleDO;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.UserInfoVo;
import com.qy.domian.vo.admin.AdminAllRoleVO;
import com.qy.domian.vo.admin.AdminUserInfoVO;
import com.qy.domian.vo.admin.AdminUserVO;
import com.qy.exception.BizException;
import com.qy.mapper.UserMapper;
import com.qy.response.AppHttpCodeEnum;
import com.qy.service.RoleService;
import com.qy.service.UserRoleService;
import com.qy.service.UserService;
import com.qy.utils.BeanCopyUtils;
import com.qy.utils.SecurityUtils;
import com.qy.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

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

    @Override
    public PageVO<AdminUserVO> listByCondition(PageParameterHelper pageParameterHelper, String userName, String phonenumber, String status) {
        Page<UserDO> page = new Page<>(pageParameterHelper.getCurrentPage(), pageParameterHelper.getPageSize());
        LambdaQueryWrapper<UserDO> eq = Wrappers.lambdaQuery(UserDO.class).like(StringUtils.isNotBlank(userName), UserDO::getUserName, userName)
                .eq(StringUtils.isNotBlank(phonenumber), UserDO::getPhonenumber, phonenumber)
                .eq(StringUtils.isNotBlank(status), UserDO::getStatus, status);
        page(page, eq);
        List<AdminUserVO> adminUserVOS = BeanCopyUtils.copyList(page.getRecords(), AdminUserVO.class);
        return new PageVO<>(adminUserVOS, page.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void add(AddUserDTO addUserDTO) {
        if (!isRepeat(addUserDTO)) {
            UserDO userDO = BeanCopyUtils.copyBean(addUserDTO, UserDO.class).setPassword(passwordEncoder.encode(addUserDTO.getPassword()));
            save(userDO);
            List<Long> roleIds = addUserDTO.getRoleIds();
            List<UserRoleDO> collect = roleIds.stream().map(aLong -> new UserRoleDO(userDO.getId(), aLong)).collect(Collectors.toList());
            userRoleService.saveBatch(collect);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void deleteById(Long id) {
        removeById(id);
        userRoleService.remove(Wrappers.lambdaQuery(UserRoleDO.class).eq(UserRoleDO::getUserId, id));
    }

    @Override
    public AdminUserInfoVO getInfo(Long id) {
        List<AdminAllRoleVO> adminAllRoleVOS = roleService.listAllRole();
        List<Long> collect = userRoleService
                .list(Wrappers.lambdaQuery(UserRoleDO.class).eq(UserRoleDO::getUserId, id)).stream().map(UserRoleDO::getRoleId).collect(Collectors.toList());
        UserDO byId = getById(id);
        AdminUserVO adminUserVO = BeanCopyUtils.copyBean(byId, AdminUserVO.class);
        return new AdminUserInfoVO().setUser(adminUserVO).setRoles(adminAllRoleVOS).setRoleIds(collect);
    }

    private boolean isRepeat(AddUserDTO addUserDTO) {
        String userName = addUserDTO.getUserName();
        String phonenumber = addUserDTO.getPhonenumber();
        String email = addUserDTO.getEmail();
        LambdaQueryWrapper<UserDO> eq = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUserName, userName);
        if (Objects.nonNull(getOne(eq))) {
            throw new BizException(AppHttpCodeEnum.SYSTEM_ERROR, "用户已经存在");
        }
        LambdaQueryWrapper<UserDO> eq1 = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getPhonenumber, phonenumber);
        if (Objects.nonNull(getOne(eq1))) {
            throw new BizException(AppHttpCodeEnum.SYSTEM_ERROR, "手机号已经存在");
        }
        LambdaQueryWrapper<UserDO> eq2 = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getEmail, email);
        if (Objects.nonNull(getOne(eq2))) {
            throw new BizException(AppHttpCodeEnum.SYSTEM_ERROR, "邮箱已经存在");
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void update(UpdateUserDTO userDTO) {
        UserDO userDO = BeanCopyUtils.copyBean(userDTO, UserDO.class);
//        isRepeat(BeanCopyUtils.copyBean(userDO, AddUserDTO.class));
        updateById(userDO);
        List<Long> roleIds = userDTO.getRoleIds();
        userRoleService.remove(Wrappers.lambdaQuery(UserRoleDO.class).eq(UserRoleDO::getUserId, userDTO.getId()));
        List<UserRoleDO> collect = roleIds.stream().map(aLong -> new UserRoleDO(userDTO.getId(), aLong)).collect(Collectors.toList());
        userRoleService.saveBatch(collect);
    }

    @Override
    public void changstatus(Map<String, Object> map) {
        LambdaUpdateWrapper<UserDO> eq = Wrappers.lambdaUpdate(UserDO.class)
                .set(UserDO::getStatus, map.get(SystemConst.USER_STATUS))
                .eq(UserDO::getId, map.get(SystemConst.USER_ID));
        update(eq);
    }
}


