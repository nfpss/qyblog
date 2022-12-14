package com.qy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qy.domian.entity.LinkDO;
import com.qy.domian.vo.LinkVO;

import java.util.List;


/**
 * 友链(LinkDO)表服务接口
 *
 * @author makejava
 * @since 2022-12-14 16:18:08
 */
public interface LinkService extends IService<LinkDO> {

    List<LinkVO> getAllLink();

}
