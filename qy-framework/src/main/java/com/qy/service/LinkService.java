package com.qy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qy.domian.entity.LinkDO;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.vo.LinkVO;
import com.qy.domian.vo.PageVO;

import java.util.List;
import java.util.Map;


/**
 * 友链(LinkDO)表服务接口
 *
 * @author makejava
 * @since 2022-12-14 16:18:08
 */
public interface LinkService extends IService<LinkDO> {

    List<LinkVO> getAllLink();

    PageVO<LinkVO> listAll(PageParameterHelper pageParameterHelper, String name, String status);

    void add(LinkVO linkVO);

    LinkVO getInfo(Long id);

    void update(LinkVO linkVO);

    void changeStatus(Map<String, Object> map);
}
