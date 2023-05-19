package com.qy.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/15 16:41
 **/
public interface UploadService {
    String uploadImg(MultipartFile img);
}
