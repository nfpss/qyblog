package com.qy.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qy.config.OssConfig;
import com.qy.exception.BizException;
import com.qy.response.AppHttpCodeEnum;
import com.qy.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/15 16:42
 **/
@Service
public class UploadServiceImpl implements UploadService {

    private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy/MM/dd/"));

    @Autowired
    private OssConfig ossConfig;

    @Override
    public String uploadImg(MultipartFile img) {
        String originalFilename = img.getOriginalFilename();
        if (originalFilename.endsWith(".png") || originalFilename.endsWith(".jpeg") || originalFilename.endsWith("img")) {
            String key = getUploadKey(originalFilename);
            return doUploadImg(img, key);
        } else {
            throw new BizException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
    }

    private String getUploadKey(String originalFilename) {
        String formatDate = SIMPLE_DATE_FORMAT.get().format(new Date());
        int i = originalFilename.lastIndexOf(".");
        return formatDate + UUID.randomUUID().toString().replace("-", "") + originalFilename.substring(i);
    }

    private String doUploadImg(MultipartFile img, String key) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        UploadManager uploadManager = new UploadManager(cfg);
        String accessKey = ossConfig.getAk();
        String secretKey = ossConfig.getSk();
        String bucket = ossConfig.getBucket();
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(img.getInputStream(), key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return ossConfig.getResponseUrl() + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {

            //ignore
        }
        return "";
    }

}
