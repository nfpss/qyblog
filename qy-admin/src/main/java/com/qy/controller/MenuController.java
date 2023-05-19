package com.qy.controller;

import com.qy.domian.entity.MenuDO;
import com.qy.domian.vo.admin.AdminRoleMenDetail;
import com.qy.domian.vo.admin.AdminRoleMenuDetailVO;
import com.qy.domian.vo.admin.AdminMenuVO;
import com.qy.domian.vo.admin.AdminRoleMenuVO;
import com.qy.response.ResponseResult;
import com.qy.service.MenuService;
import com.qy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: qy
 * @create: 2022/12/17 23:30
 * @description:
 **/
@RestController
@RequestMapping("system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult<List<AdminMenuVO>> list(String status, String menuName) {
        List<AdminMenuVO> adminMenuVOList = menuService.listAll(status, menuName);
        return ResponseResult.success(adminMenuVOList);
    }

    @PostMapping
    public ResponseResult add(@RequestBody MenuDO menuDO) {
        menuService.save(menuDO);
        return ResponseResult.success();
    }

    @GetMapping("/{id}")
    public ResponseResult getInfo(@PathVariable("id") Long id) {
        AdminRoleMenuDetailVO adminMenuDetailVO = menuService.getinfo(id);
        return ResponseResult.success(adminMenuDetailVO);
    }

    @PutMapping
    public ResponseResult update(@RequestBody MenuDO menuDO) {
        menuService.updateByDTO(menuDO);
        return ResponseResult.success();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        menuService.deleteByid(id);
        return ResponseResult.success();
    }

    @GetMapping("/treeselect")
    public ResponseResult<List<AdminRoleMenuVO>> treeSelect() {
        List<AdminRoleMenuVO> list = roleService.treeSelect();
        return ResponseResult.success(list);
    }

    @GetMapping("roleMenuTreeselect/{id}")
    public ResponseResult<AdminRoleMenDetail> roleMenuTreeSelect(@PathVariable("id") Long id) {
        AdminRoleMenDetail adminRoleMenDetail = roleService.roleMenuTreeSelect(id);
        return ResponseResult.success(adminRoleMenDetail);
    }
}
