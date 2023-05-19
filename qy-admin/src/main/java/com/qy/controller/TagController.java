package com.qy.controller;

import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.entity.TagDO;
import com.qy.domian.vo.PageVO;
import com.qy.domian.vo.TagVO;
import com.qy.response.ResponseResult;
import com.qy.service.TagService;
import com.qy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/16 13:51
 **/
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVO> list(Integer pageNum, Integer pageSize, String name, String remark) {
        PageVO<TagVO> tagVOPageVO = tagService.listByCondition(new PageParameterHelper(pageNum, pageSize), name, remark);
        return ResponseResult.success(tagVOPageVO);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        tagService.removeById(id);
        return ResponseResult.success();
    }

    @GetMapping("/{id}")
    public ResponseResult getInfo(@PathVariable("id") Long id) {
        TagVO tagVO = BeanCopyUtils.copyBean(tagService.getById(id), TagVO.class);
        return ResponseResult.success(tagVO);
    }

    @PutMapping()
    public ResponseResult putInfo(@RequestBody TagVO tagVO) {
        TagDO tagDO = BeanCopyUtils.copyBean(tagVO, TagDO.class);
        tagService.updateById(tagDO);
        return ResponseResult.success();
    }

    @PostMapping()
    public ResponseResult add(@RequestBody TagDO tagDO) {
        tagService.save(tagDO);
        return ResponseResult.success();
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag() {
        List<Map<String, Object>> list = tagService.listAllTag();
        return ResponseResult.success(list);
    }


}
