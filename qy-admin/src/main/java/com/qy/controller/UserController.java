package com.qy.controller;

import com.qy.domian.dto.AddUserDTO;
import com.qy.domian.dto.UpdateUserDTO;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.admin.AdminUserInfoVO;
import com.qy.domian.vo.admin.AdminUserVO;
import com.qy.response.ResponseResult;
import com.qy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: qy
 * @create: 2022/12/18 20:13
 * @description:
 **/
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public ResponseResult<PageVO<AdminUserVO>> list(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        PageVO<AdminUserVO> adminUserVOPageVO = userService.listByCondition(new PageParameterHelper(pageNum, pageSize), userName, phonenumber, status);
        return ResponseResult.success(adminUserVOPageVO);
    }

    @PostMapping
    public ResponseResult add(@RequestBody AddUserDTO addUserDTO) {
        userService.add(addUserDTO);
        return ResponseResult.success();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseResult.success();
    }

    @GetMapping("/{id}")
    public ResponseResult<AdminUserInfoVO> getInfo(@PathVariable("id") Long id) {
        AdminUserInfoVO adminUserInfoVO = userService.getInfo(id);
        return ResponseResult.success(adminUserInfoVO);
    }

    @PutMapping()
    public ResponseResult update(@RequestBody UpdateUserDTO userDTO) {
        userService.update(userDTO);
        return ResponseResult.success();
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody Map<String, Object> map) {
        userService.changstatus(map);
        return ResponseResult.success();
    }
}
