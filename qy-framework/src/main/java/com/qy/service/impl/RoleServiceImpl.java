package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.constants.SystemConst;
import com.qy.domian.dto.RoleDTO;
import com.qy.domian.dto.RoleUpdateDTO;
import com.qy.domian.entity.MenuDO;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.entity.RoleDO;
import com.qy.domian.entity.RoleMenuDO;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.admin.AdminAllRoleVO;
import com.qy.domian.vo.admin.AdminRoleMenDetail;
import com.qy.domian.vo.admin.AdminRoleMenuVO;
import com.qy.domian.vo.admin.AdminRoleVO;
import com.qy.mapper.RoleMapper;
import com.qy.service.MenuService;
import com.qy.service.RoleMenuService;
import com.qy.service.RoleService;
import com.qy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2022-12-16 15:01:35
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public PageVO<AdminRoleVO> listPage(PageParameterHelper pageParameterHelper, String roleName, String status) {
        LambdaQueryWrapper<RoleDO> wrapper = Wrappers.lambdaQuery(RoleDO.class).like(StringUtils.isNotBlank(roleName), RoleDO::getRoleName, roleName)
                .eq(StringUtils.isNotBlank(status), RoleDO::getStatus, status)
                .orderByAsc(RoleDO::getRoleSort);
        Page<RoleDO> page = new Page<>(pageParameterHelper.getCurrentPage(), pageParameterHelper.getPageSize());
        page(page, wrapper);
        List<AdminRoleVO> adminRoleVOS = BeanCopyUtils.copyList(page.getRecords(), AdminRoleVO.class);
        return new PageVO<>(adminRoleVOS, page.getTotal());
    }

    @Override
    public void changeStatus(Map<String, Object> map) {
        LambdaUpdateWrapper<RoleDO> eq = Wrappers.lambdaUpdate(RoleDO.class)
                .set(RoleDO::getStatus, map.get("status"))
                .eq(RoleDO::getId, map.get("roleId"));
        update(eq);
    }

    @Override
    public List<AdminRoleMenuVO> treeSelect() {
        List<MenuDO> list = menuService.list(Wrappers.lambdaQuery(MenuDO.class).orderByAsc(MenuDO::getParentId, MenuDO::getOrderNum));
        List<AdminRoleMenuVO> menuVOS = list.stream().map(menuDO -> BeanCopyUtils.copyBean(menuDO, AdminRoleMenuVO.class).setLabel(menuDO.getMenuName())).collect(Collectors.toList());
        List<AdminRoleMenuVO> collect = menuVOS.stream()
                .filter(adminRoleMenuVO -> Objects.equals(adminRoleMenuVO.getParentId(), SystemConst.ROOT_MENU))
                .map(adminRoleMenuVO -> adminRoleMenuVO.setChildren(setChidren(adminRoleMenuVO, menuVOS)))
                .collect(Collectors.toList());
        return collect;
    }

    private List<AdminRoleMenuVO> setChidren(AdminRoleMenuVO adminRoleMenuVO, List<AdminRoleMenuVO> menuVOS) {
        return menuVOS.stream()
                .filter(adminRoleMenuVO1 -> Objects.equals(adminRoleMenuVO1.getParentId(), adminRoleMenuVO.getId()))
                .map(adminRoleMenuVO1 -> adminRoleMenuVO1.setChildren(setChidren(adminRoleMenuVO1, menuVOS)))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void saveByDTO(RoleDTO roleDTO) {
        RoleDO roleDO = BeanCopyUtils.copyBean(roleDTO, RoleDO.class);
        save(roleDO);
        List<RoleMenuDO> collect = roleDTO.getMenuIds().stream().map(aLong -> new RoleMenuDO(roleDO.getId(), aLong)).collect(Collectors.toList());
        roleMenuService.saveBatch(collect);
    }

    @Override
    public AdminRoleVO getInfo(Long id) {
        RoleDO byId = getById(id);
        return BeanCopyUtils.copyBean(byId, AdminRoleVO.class);
    }

    @Override
    public AdminRoleMenDetail roleMenuTreeSelect(Long id) {
        List<AdminRoleMenuVO> adminRoleMenuVOS = treeSelect();
        AdminRoleMenDetail adminRoleMenDetail = new AdminRoleMenDetail();
        List<Long> ids = new ArrayList<>();
        if (Objects.equals(id, SystemConst.ROLE_BY_ADMIN)) {
            ids = menuService.list().stream().map(menuDO -> menuDO.getId()).collect(Collectors.toList());
        } else {
            ids = roleMenuService
                    .listObjs(Wrappers.lambdaQuery(RoleMenuDO.class).eq(RoleMenuDO::getRoleId, id).select(RoleMenuDO::getMenuId), o -> (Long) o);
        }
        return adminRoleMenDetail.setCheckedKeys(ids).setMenus(adminRoleMenuVOS);

    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void updateByDTO(RoleUpdateDTO roleUpdateDTO) {
        Long id = roleUpdateDTO.getId();
        RoleDO roleDO = BeanCopyUtils.copyBean(roleUpdateDTO, RoleDO.class);
        updateById(roleDO);
        List<Long> menuIds = roleUpdateDTO.getMenuIds();
        roleMenuService.remove(Wrappers.lambdaQuery(RoleMenuDO.class).eq(RoleMenuDO::getRoleId, id));
        List<RoleMenuDO> collect = menuIds.stream().map(aLong -> new RoleMenuDO(id, aLong)).collect(Collectors.toList());
        roleMenuService.saveBatch(collect);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void deleteById(Long id) {
        removeById(id);
        roleMenuService.remove(Wrappers.lambdaQuery(RoleMenuDO.class).eq(RoleMenuDO::getRoleId, id));
    }

    @Override
    public List<AdminAllRoleVO> listAllRole() {
        List<RoleDO> list = list(Wrappers.lambdaQuery(RoleDO.class).eq(RoleDO::getStatus, SystemConst.NORMAL_ROLE_STATUS));
        return BeanCopyUtils.copyList(list, AdminAllRoleVO.class);
    }
}


