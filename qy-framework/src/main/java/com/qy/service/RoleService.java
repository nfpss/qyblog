package com.qy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qy.domian.dto.RoleDTO;
import com.qy.domian.dto.RoleUpdateDTO;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.entity.RoleDO;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.admin.AdminAllRoleVO;
import com.qy.domian.vo.admin.AdminRoleMenDetail;
import com.qy.domian.vo.admin.AdminRoleMenuVO;
import com.qy.domian.vo.admin.AdminRoleVO;

import java.util.List;
import java.util.Map;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2022-12-16 14:47:31
 */
public interface RoleService extends IService<RoleDO> {

    PageVO<AdminRoleVO> listPage(PageParameterHelper pageParameterHelper, String roleName, String status);

    void changeStatus(Map<String, Object> map);

    List<AdminRoleMenuVO> treeSelect();

    void saveByDTO(RoleDTO roleDTO);

    AdminRoleVO getInfo(Long id);

    AdminRoleMenDetail roleMenuTreeSelect(Long id);

    void updateByDTO(RoleUpdateDTO roleUpdateDTO);

    void deleteById(Long id);

    List<AdminAllRoleVO> listAllRole();

}
