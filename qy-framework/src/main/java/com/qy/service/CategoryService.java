package com.qy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qy.domian.dto.CategoryDTO;
import com.qy.domian.entity.CategoryDO;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.admin.AdminCategoryVO;
import com.qy.domian.vo.CategoryVO;
import com.qy.response.ResponseResult;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 分类表(SgCategory)表服务接口
 *
 * @author makejava
 * @since 2022-12-13 00:46:10
 */
public interface CategoryService extends IService<CategoryDO> {

    ResponseResult<List<CategoryVO>> getCategoryList();

    List<AdminCategoryVO> listAllCategory();

    void export(HttpServletResponse response);

    PageVO<AdminCategoryVO> list(PageParameterHelper pageParameterHelper, String name, String status);

    void add(CategoryDTO categoryDTO);

    CategoryVO getInfo(Long id);

    void updateByVO(CategoryVO categoryVO);

    void deletById(Long id);
}

