package com.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qy.constants.SystemConst;
import com.qy.domian.entity.LinkDO;
import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.vo.LinkVO;
import com.qy.domian.vo.PageVO;
import com.qy.exception.BizException;
import com.qy.mapper.LinkMapper;
import com.qy.response.AppHttpCodeEnum;
import com.qy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.qy.service.LinkService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public PageVO<LinkVO> listAll(PageParameterHelper pageParameterHelper, String name, String status) {
        Page<LinkDO> page = new Page<>(pageParameterHelper.getCurrentPage(), pageParameterHelper.getPageSize());
        LambdaQueryWrapper<LinkDO> eq = Wrappers.lambdaQuery(LinkDO.class).like(StringUtils.isNotBlank(name), LinkDO::getName, name)
                .eq(StringUtils.isNotBlank(status), LinkDO::getStatus, status);
        page(page, eq);
        return new PageVO<>(BeanCopyUtils.copyList(page.getRecords(), LinkVO.class), page.getTotal());
    }

    @Override
    public void add(LinkVO linkVO) {
        LinkDO linkDO = BeanCopyUtils.copyBean(linkVO, LinkDO.class);
        LinkDO name = getById(linkVO.getName());
        if (Objects.nonNull(name)) {
            throw new BizException(AppHttpCodeEnum.SYSTEM_ERROR, "友联名字重复，已存在");
        }
        save(linkDO);
    }

    @Override
    public LinkVO getInfo(Long id) {
        LinkDO byId = getById(id);
        return BeanCopyUtils.copyBean(byId, LinkVO.class);
    }

    @Override
    public void update(LinkVO linkVO) {
        Long id = linkVO.getId();
        if (Objects.isNull(getById(id))) {
            throw new BizException(AppHttpCodeEnum.SYSTEM_ERROR,"友链不存在");
        }
        updateById(BeanCopyUtils.copyBean(linkVO, LinkDO.class));
    }

    @Override
    public void changeStatus(Map<String, Object> map) {
        update(Wrappers.lambdaUpdate(LinkDO.class).set(LinkDO::getStatus,map.get("status")).eq(LinkDO::getId,map.get("id")));
    }
}


