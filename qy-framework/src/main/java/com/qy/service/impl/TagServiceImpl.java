package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.entity.TagDO;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.TagVO;
import com.qy.mapper.TagMapper;
import com.qy.service.TagService;
import com.qy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2022-12-16 13:50:21
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements TagService {

    @Override
    public PageVO<TagVO> listByCondition(PageParameterHelper pageParameterHelper, String name, String remark) {
        LambdaQueryWrapper<TagDO> wrapper = Wrappers.lambdaQuery(TagDO.class)
                .like(StringUtils.isNotBlank(name), TagDO::getName, name)
                .like(StringUtils.isNotBlank(remark), TagDO::getRemark, remark);
        Page<TagDO> page = new Page<>(pageParameterHelper.getCurrentPage(), pageParameterHelper.getPageSize());
        page(page, wrapper);
        List<TagVO> tagVOList = BeanCopyUtils.copyList(page.getRecords(), TagVO.class);
        return new PageVO<>(tagVOList, page.getTotal());
    }

    @Override
    public List<Map<String, Object>> listAllTag() {
        List<TagDO> list = list();
        return list.stream().map(tagDO -> {
            Map<String, Object> map = new HashMap<>(16);
            map.put("id", tagDO.getId());
            map.put("name", tagDO.getName());
            return map;
        }).collect(Collectors.toList());
    }
}


