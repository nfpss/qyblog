package com.qy.controller;

import com.qy.domian.dto.RoleDTO;
import com.qy.domian.dto.RoleUpdateDTO;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.admin.AdminAllRoleVO;
import com.qy.domian.vo.admin.AdminRoleVO;
import com.qy.response.ResponseResult;
import com.qy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: qy
 * @create: 2022/12/18 16:04
 * @description:
 **/
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult<PageVO> list(Integer pageNum, Integer pageSize, String roleName, String status) {
        PageVO<AdminRoleVO> roleVOPageVO = roleService.listPage(new PageParameterHelper(pageNum, pageSize), roleName, status);
        return ResponseResult.success(roleVOPageVO);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody Map<String, Object> map) {
        roleService.changeStatus(map);
        return ResponseResult.success();
    }

    @PostMapping
    public ResponseResult add(@RequestBody RoleDTO roleDTO) {
        roleService.saveByDTO(roleDTO);
        return ResponseResult.success();
    }

    @GetMapping("/{id}")
    public ResponseResult<AdminRoleVO> getInfo(@PathVariable("id") Long id) {
        AdminRoleVO info = roleService.getInfo(id);
        return ResponseResult.success(info);
    }

    @PutMapping
    public ResponseResult update(@RequestBody RoleUpdateDTO roleUpdateDTO) {
        roleService.updateByDTO(roleUpdateDTO);
        return ResponseResult.success();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        roleService.deleteById(id);
        return ResponseResult.success();
    }

    @GetMapping("/listAllRole")
    public ResponseResult listAllRole() {
        List<AdminAllRoleVO> adminAllRoleVOS = roleService.listAllRole();
        return ResponseResult.success(adminAllRoleVOS);
    }
}
