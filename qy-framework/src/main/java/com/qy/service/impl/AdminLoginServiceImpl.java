package com.qy.service.impl;

import com.google.common.collect.Maps;
import com.qy.config.RedisCache;
import com.qy.constants.SystemConst;
import com.qy.domian.dto.UserDTO;
import com.qy.domian.entity.LoginUser;
import com.qy.domian.entity.MenuDO;
import com.qy.domian.vo.admin.AdminUserInfo;
import com.qy.domian.vo.MenuVO;
import com.qy.domian.vo.RouterVO;
import com.qy.domian.vo.UserInfoVo;
import com.qy.exception.BizException;
import com.qy.mapper.MenuMapper;
import com.qy.mapper.RoleMapper;
import com.qy.response.AppHttpCodeEnum;
import com.qy.service.AdminLoginService;
import com.qy.utils.BeanCopyUtils;
import com.qy.utils.JwtUtil;
import com.qy.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/16 14:04
 **/
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Map<String, String> login(UserDTO user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new BizException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        Long id = principal.getUserDO().getId();
        String jwt = JwtUtil.createJWT(id.toString());
        redisCache.setCacheObject(String.format(SystemConst.ADMIN_LONG_USER_KEY, id), principal);
        HashMap<String, String> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put(SystemConst.TOKEN, jwt);
        return objectObjectHashMap;
    }

    @Override
    public AdminUserInfo getInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long id = loginUser.getUserDO().getId();
        //查询当前用户所拥有的角色key
        List<String> roleList = roleMapper.selectByUserId(id);
        AdminUserInfo adminUserInfo = new AdminUserInfo();
        List<String> collect = loginUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUserDO(), UserInfoVo.class);
        adminUserInfo.setUser(userInfoVo)
                .setRoles(roleList)
                .setPermissions(collect);
        return adminUserInfo;
    }

    @Override
    public RouterVO getRouters() {
        //如果用户id为1代表管理员，menus中需要有所有菜单类型为C或者M的，状态为正常的，未被删除的权限
        Long userId = SecurityUtils.getUserId();
        List<MenuDO> menuDOS = new ArrayList<>();
        if (SystemConst.ROLE_BY_ADMIN == userId) {
            menuDOS = menuMapper.listAllRouter();
        } else {
            menuDOS = menuMapper.listRouterByUserId(userId);
        }
        List<MenuVO> menuVOS = buildTreeMenu(menuDOS);
        RouterVO routerVO = new RouterVO().setMenus(menuVOS);
        return routerVO;
    }

    @Override
    public void logout() {
        Long userId = SecurityUtils.getUserId();
        redisCache.deleteObject(String.format(SystemConst.ADMIN_LONG_USER_KEY, userId));
    }

    private List<MenuVO> buildTreeMenu(List<MenuDO> menuDOS) {
        List<MenuVO> menuVOList = BeanCopyUtils.copyList(menuDOS, MenuVO.class);
        return menuVOList.stream().filter(menuVO -> Objects.equals(menuVO.getParentId(), SystemConst.ROOT_MENU))
                .map(menuVO -> menuVO.setChildren(childrenTree(menuVO, menuVOList)))
                .collect(Collectors.toList());
    }

    /**
     * 找到所有的子类以及子类的子类
     */
    private List<MenuVO> childrenTree(MenuVO menuVO, List<MenuVO> menuVOList) {
        return menuVOList.stream()
                .filter(menuVO1 -> Objects.equals(menuVO.getId(), menuVO1.getParentId()))
                .map(menuVO1 -> menuVO1.setChildren(childrenTree(menuVO1, menuVOList)))
                .collect(Collectors.toList());
    }

}

