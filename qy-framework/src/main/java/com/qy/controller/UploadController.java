package com.qy.controller;

import com.qy.response.ResponseResult;
import com.qy.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/15 16:40
 **/
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult<String> uploadImg(MultipartFile img) {
        return ResponseResult.success(uploadService.uploadImg(img));
    }
}
