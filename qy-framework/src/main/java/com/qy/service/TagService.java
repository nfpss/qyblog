package com.qy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.entity.TagDO;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.TagVO;

import java.util.List;
import java.util.Map;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2022-12-16 13:50:21
 */
public interface TagService extends IService<TagDO> {

   PageVO<TagVO> listByCondition(PageParameterHelper pageParameterHelper, String name, String remark);

    List<Map<String, Object>> listAllTag();

}
