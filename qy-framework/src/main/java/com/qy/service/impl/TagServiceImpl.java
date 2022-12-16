package com.qy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.domian.entity.TagDO;
import com.qy.mapper.TagMapper;
import com.qy.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2022-12-16 13:50:21
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements TagService {

}


