package com.qy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qy.domian.entity.MenuDO;
import com.qy.domian.vo.admin.AdminRoleMenuDetailVO;
import com.qy.domian.vo.admin.AdminMenuVO;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2022-12-16 14:47:31
 */
public interface MenuService extends IService<MenuDO> {

    List<AdminMenuVO> listAll(String status, String menuName);

    AdminRoleMenuDetailVO getinfo(Long id);

    void updateByDTO(MenuDO menuDO);

    void deleteByid(Long id);
}
