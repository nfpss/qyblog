package com.qy.controller;

import com.qy.domian.entity.PageParameterHelper;
import com.qy.domian.vo.LinkVO;
import com.qy.domian.vo.PageVO;
import com.qy.response.ResponseResult;
import com.qy.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: qy
 * @create: 2022/12/18 22:27
 * @description:
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, String name, String status) {
        PageVO<LinkVO> linkVOPageVO = linkService.listAll(new PageParameterHelper(pageNum, pageSize), name, status);
        return ResponseResult.success(linkVOPageVO);
    }

    @PostMapping
    public ResponseResult add(@RequestBody LinkVO linkVO) {
        linkService.add(linkVO);
        return ResponseResult.success();
    }

    @GetMapping("/{id}")
    public ResponseResult getInfo(@PathVariable("id") Long id) {
        LinkVO linkVO = linkService.getInfo(id);
        return ResponseResult.success(linkVO);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        linkService.removeById(id);
        return ResponseResult.success();
    }

    @PutMapping()
    public ResponseResult update(@RequestBody LinkVO linkVO) {
        linkService.update(linkVO);
        return ResponseResult.success();
    }

    @PutMapping("/changeLinkStatus")
    public ResponseResult changeStatus(@RequestBody Map<String, Object> map) {
        linkService.changeStatus(map);
        return ResponseResult.success();
    }
}
