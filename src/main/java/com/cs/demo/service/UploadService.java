package com.cs.demo.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/28 15:44
 */
public interface UploadService {
    /**
     * 上传文件
     *
     * @param file File
     * @return
     * @throws QiniuException
     */
    Response uploadFile(File file) throws QiniuException;

    /**
     * 上传图片到七牛云并返回地址
     * @param request
     * @param image
     * @return
     * @throws IOException
     */
    String getPic(HttpServletRequest request, MultipartFile image) throws IOException;

    /**
     * 根据文件名删除空间中的文件
     *
     * @param key 文件名
     */
    void deleteFile(String key) throws QiniuException;
}
