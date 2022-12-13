package com.qy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qy.domian.entity.CategoryDO;
import com.qy.response.ResponseResult;


/**
 * 分类表(SgCategory)表服务接口
 *
 * @author makejava
 * @since 2022-12-13 00:46:10
 */
public interface CategoryService extends IService<CategoryDO> {

    ResponseResult getCategoryList();

}

