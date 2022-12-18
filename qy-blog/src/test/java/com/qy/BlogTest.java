package com.qy;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qy.config.OssConfig;
import com.qy.domian.entity.LinkDO;
import com.qy.service.LinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileInputStream;

/**
 * @author: qy
 * @create: 2022/12/14 21:42
 * @description:
 **/
@SpringBootTest
public class BlogTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OssConfig ossConfig;

    @Test
    void tes() {
        System.out.println(passwordEncoder.encode("123456"));
    }

    @Test
    void testUpload() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = ossConfig.getAk();
        String secretKey = ossConfig.getSk();
        String bucket = ossConfig.getBucket();

//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "blog-test";
        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/pengxiaoxi/Downloads/R-C.jpeg");
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(fileInputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                System.out.println(putRet);
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

    }

    @Autowired
    private LinkService linkService;

    @Test
    void testLink(){
        linkService.list();
        LinkDO linkDO = new LinkDO();
        linkDO.setId(4L);
        linkDO.setName("sfdhfsah");
        linkService.update(linkDO, null);
        System.out.println("SFdfdsdsfa");
    }
}
