package com.qy.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.constants.SystemConst;
import com.qy.domian.dto.CategoryDTO;
import com.qy.domian.entity.ArticleDO;
import com.qy.domian.entity.CategoryDO;
import com.qy.domian.entity.CategoryExcel;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.admin.AdminCategoryVO;
import com.qy.domian.vo.CategoryVO;
import com.qy.exception.BizException;
import com.qy.mapper.ArticleMapper;
import com.qy.mapper.CategoryMapper;
import com.qy.response.AppHttpCodeEnum;
import com.qy.response.ResponseResult;
import com.qy.service.CategoryService;
import com.qy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(SgCategory)表服务实现类
 *
 * @author makejava
 * @since 2022-12-13 00:46:10
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryDO> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ArticleMapper articleMapper;

    // ①要求只展示有发布正式文章的分类 ②必须是正常状态的分类
    @Override
    public ResponseResult<List<CategoryVO>> getCategoryList() {
        LambdaQueryWrapper<ArticleDO> queryWrapper = Wrappers.lambdaQuery(ArticleDO.class).eq(ArticleDO::getStatus, SystemConst.ARTICLE_STATUS_NORMAL);
        Set<Long> categoryId = articleMapper.selectList(queryWrapper).stream().map(ArticleDO::getCategoryId).collect(Collectors.toSet());
        LambdaQueryWrapper<CategoryDO> wrapper = Wrappers
                .lambdaQuery(CategoryDO.class).eq(CategoryDO::getStatus, SystemConst.CATEGORY_STATUS_NORMAL)
                .in(CategoryDO::getId, categoryId);
        List<CategoryDO> categoryDOList = categoryMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(categoryDOList)) {
            return ResponseResult.success(Collections.emptyList());
        }
        return ResponseResult.success(BeanCopyUtils.copyList(categoryDOList, CategoryVO.class));
    }

    @Override
    public List<AdminCategoryVO> listAllCategory() {
        List<CategoryDO> list = list();
        return BeanCopyUtils.copyList(list, AdminCategoryVO.class);
    }

    @Override
    public void export(HttpServletResponse response) {
        List<CategoryDO> list = list();
        List<CategoryExcel> excels = BeanCopyUtils.copyList(list, CategoryExcel.class);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        try {
            String fileName = URLEncoder.encode("分类", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), CategoryExcel.class).sheet("分类表格").doWrite(excels);
        } catch (IOException e) {
            throw new BizException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public PageVO<AdminCategoryVO> list(PageParameterHelper pageParameterHelper, String name, String status) {
        Page<CategoryDO> page = new Page<>(pageParameterHelper.getCurrentPage(), pageParameterHelper.getPageSize());
        LambdaQueryWrapper<CategoryDO> eq = Wrappers.lambdaQuery(CategoryDO.class).like(StringUtils.isNotBlank(name), CategoryDO::getName, name)
                .eq(StringUtils.isNotBlank(status), CategoryDO::getStatus, status);
        page(page, eq);
        return new PageVO<>(BeanCopyUtils.copyList(page.getRecords(), AdminCategoryVO.class), page.getTotal());
    }

    @Override
    public void add(CategoryDTO categoryDTO) {
        CategoryDO categoryDO = BeanCopyUtils.copyBean(categoryDTO, CategoryDO.class);
        save(categoryDO);
    }

    @Override
    public CategoryVO getInfo(Long id) {
        CategoryDO byId = getById(id);
        return BeanCopyUtils.copyBean(byId, CategoryVO.class);
    }

    @Override
    public void updateByVO(CategoryVO categoryVO) {
        updateById(BeanCopyUtils.copyBean(categoryVO, CategoryDO.class));
    }

    @Override
    public void deletById(Long id) {
        LambdaQueryWrapper<ArticleDO> eq = Wrappers.lambdaQuery(ArticleDO.class).eq(ArticleDO::getCategoryId, id);
        List<ArticleDO> articleDOS = articleMapper.selectList(eq);
        if (CollectionUtils.isNotEmpty(articleDOS)) {
            throw new BizException(AppHttpCodeEnum.SYSTEM_ERROR, "该分类有文章引用无法删除");
        }
        removeById(id);
    }
}

