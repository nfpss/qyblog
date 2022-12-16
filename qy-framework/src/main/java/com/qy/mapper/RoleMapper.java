package com.qy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qy.domian.entity.RoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-16 14:47:31
 */
public interface RoleMapper extends BaseMapper<RoleDO> {
    /**
     * 根据用户id查询所有的角色key
     */
    List<String> selectByUserId(@Param("id") Long id);
}


