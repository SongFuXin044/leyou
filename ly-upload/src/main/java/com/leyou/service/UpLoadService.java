package com.leyou.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.EnumsException;
import com.leyou.common.exceptions.lyException;
import com.leyou.config.UploadProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
@EnableConfigurationProperties(value = UploadProperties.class)
public class UpLoadService {
    @Resource
    private FastFileStorageClient storageClient;

    @Resource
    private UploadProperties uploadProperties;
    //类型限制
//    public static final List<String> allowTypes = Arrays.asList("image/jpeg","image/png","image/bmp");

    public String uploadImage(MultipartFile file)  {
        System.out.println("uploadProperties = " + uploadProperties);
        try {
        String contentType = file.getContentType();//获取文件类型
        //检验文件类型,不符合就抛出异常
        if (!uploadProperties.getAllowTypes().contains(contentType)){
            throw new lyException(EnumsException.IMAGE_TYPE_ERROR);
        }
        //检验文件内容,如果为空抛出异常
        BufferedImage read = ImageIO.read(file.getInputStream());
        if (read == null){
            throw new lyException(EnumsException.IMAGE_NOT_FOUND);
        }
            //FastDfs处理
            //获取图片后缀
            String substringAfterLast = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), substringAfterLast, null);
            System.out.println("图片路径 : " + storePath.getFullPath());
            return uploadProperties.getBaseUrl()+storePath.getFullPath();
        } catch (IOException e) {
            throw new lyException(EnumsException.IMAGE_UPLOAD_ERROR);
        }
    }
}
