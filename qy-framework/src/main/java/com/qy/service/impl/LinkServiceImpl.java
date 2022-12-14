package com.qy.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.constants.SystemConst;
import com.qy.domian.entity.LinkDO;
import com.qy.domian.vo.LinkVO;
import com.qy.mapper.LinkMapper;
import com.qy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.qy.service.LinkService;

import java.util.List;

/**
 * 友链(LinkDO)表服务实现类
 *
 * @author makejava
 * @since 2022-12-14 16:18:08
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, LinkDO> implements LinkService {
    @Override
    public List<LinkVO> getAllLink() {
        List<LinkDO> linkDOList = list(Wrappers.lambdaQuery(LinkDO.class).eq(LinkDO::getStatus, SystemConst.LINK_STATUS_PASS));
        return BeanCopyUtils.copyList(linkDOList, LinkVO.class);
    }
}


