package com.qy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qy.domian.entity.MenuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-16 14:47:31
 */
public interface MenuMapper extends BaseMapper<MenuDO> {
    /**
     * 根据用户id查所拥有的权限
     */
    List<String> listPermissByUserId(@Param("id") Long id);

    /**
     * 查询所有用户的路由列表
     */
    List<MenuDO> listAllRouter();

    /**
     * 根据用户id查询所有菜单
     */
    List<MenuDO> listRouterByUserId(@Param("id") Long userId);
}


