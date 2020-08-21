package com.leyou.test;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
public class testFdfs {
    @Resource
    private FastFileStorageClient storageClient;

    @Resource
    private ThumbImageConfig thumbImageConfig;
    

    @Test
    void test() throws FileNotFoundException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\Wallpaper\\1.jpg");
        StorePath storePath = storageClient.uploadFile(new FileInputStream(file), file.length(), "jpg", null);
        //获取图片全部路径
        System.out.println("storePath = " + storePath.getFullPath());
        //获取绝对
        System.out.println("storePath = " + storePath.getPath());
    }

    @Test
    void test_01() throws FileNotFoundException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\Wallpaper\\duye.jpg");
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(new FileInputStream(file), file.length(), "jpg", null);
        //解析缩略图
        String thumbImagePath = thumbImageConfig.getThumbImagePath(storePath.getFullPath());
        //获取图片全部路径
        System.out.println("storePath = " + storePath.getFullPath());
        //获取绝对
        System.out.println("storePath = " + storePath.getPath());
        //缩略图
        System.out.println("thumbImagePath = " + thumbImagePath);
    }


}
