package com.qy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.domian.entity.MenuDO;
import com.qy.mapper.MenuMapper;
import com.qy.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2022-12-16 15:01:35
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuDO> implements MenuService {

}


