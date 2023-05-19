package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.domian.entity.MenuDO;
import com.qy.domian.vo.admin.AdminRoleMenuDetailVO;
import com.qy.domian.vo.admin.AdminMenuVO;
import com.qy.exception.BizException;
import com.qy.mapper.MenuMapper;
import com.qy.response.AppHttpCodeEnum;
import com.qy.service.MenuService;
import com.qy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2022-12-16 15:01:35
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuDO> implements MenuService {
    @Override
    public List<AdminMenuVO> listAll(String status, String menuName) {
        LambdaQueryWrapper<MenuDO> wrapper = Wrappers.lambdaQuery(MenuDO.class).eq(StringUtils.isNotBlank(status), MenuDO::getStatus, status)
                .like(StringUtils.isNotBlank(menuName), MenuDO::getMenuName, menuName)
                .orderByAsc(MenuDO::getParentId, MenuDO::getOrderNum);
        return BeanCopyUtils.copyList(list(wrapper), AdminMenuVO.class);
    }

    @Override
    public AdminRoleMenuDetailVO getinfo(Long id) {
        MenuDO byId = getById(id);
        return BeanCopyUtils.copyBean(byId, AdminRoleMenuDetailVO.class);
    }

    @Override
    public void updateByDTO(MenuDO menuDO) {
        if (Objects.equals(menuDO.getParentId(), menuDO.getId())) {
            throw new BizException(AppHttpCodeEnum.SYSTEM_ERROR, "父类菜单不能选择自己");
        }
        updateById(menuDO);
    }

    @Override
    public void deleteByid(Long id) {
        //能够删除菜单，但是如果要删除的菜单有子菜单则提示：存在子菜单不允许删除 并且删除失败。
        List<MenuDO> list = list(Wrappers.lambdaQuery(MenuDO.class).eq(MenuDO::getParentId, id));
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BizException(AppHttpCodeEnum.SYSTEM_ERROR, "存在子菜单无法删除");
        }
        removeById(id);
    }
}


