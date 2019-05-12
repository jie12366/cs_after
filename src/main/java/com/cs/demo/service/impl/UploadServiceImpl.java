package com.cs.demo.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.cs.demo.service.UploadService;
import com.cs.demo.utils.QiniuUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/28 15:46
 */
@Service
public class UploadServiceImpl implements UploadService, InitializingBean {
    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private Auth auth;
    @Autowired
    BucketManager bucketManager;
    @Autowired
    private QiniuUtil qiNiuProperties;
    private StringMap putPolicy;
    String key = null;

    @Override
    public Response uploadFile(File file) throws QiniuException {
        Response response = this.uploadManager.put(file, key, getUploadToken());
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, key, getUploadToken());
            retry++;
        }
        return response;
    }

    @Override
    public String getPic(HttpServletRequest request, MultipartFile image) throws IOException {
        //根据时间戳创建文件名
        String fileName = System.currentTimeMillis() + image.getOriginalFilename();
        //创建文件的实际路径
        String destFileName = request.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;
        //根据文件路径创建文件对应的实际文件
        File destFile = new File(destFileName);
        //创建文件实际路径
        destFile.getParentFile().mkdirs();
        //将文件传到对应的文件位置
        image.transferTo(destFile);
        Response response = uploadFile(destFile);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return  "http://cdn.jie12366.xyz/" + putRet.key;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
    }

    /**
     * 获取上传凭证
     *
     * @return
     */
    private String getUploadToken() {
        return this.auth.uploadToken(qiNiuProperties.getBucket(), null, 3600, putPolicy);
    }

    @Override
    public void deleteFile(String key) throws QiniuException {
        bucketManager.delete(qiNiuProperties.getBucket(), key);
    }
}
