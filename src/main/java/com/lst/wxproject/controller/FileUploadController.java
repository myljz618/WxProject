package com.lst.wxproject.controller;

import com.lst.wxproject.common.Result;
import com.lst.wxproject.entity.Photo;
import com.lst.wxproject.mapper.PhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {

    @Autowired
    PhotoMapper photoMapper;

    @PostMapping("/upload")
    public Result upload(MultipartFile photo) throws IOException {
        System.out.println(photo.getOriginalFilename());        //获取图片的原始名称
        System.out.println(photo.getContentType());         //获取文件类型
        String originalFilename = photo.getOriginalFilename();

        //为了防止文件名称重复导致覆盖，给每个文件定义一个唯一的名称
        String newFileName = UUID.randomUUID().toString().replace("-","")+originalFilename;
        //获取程序运行目录
        String dirPath = System.getProperty("user.dir");
        //拼接文件存储路径，最终存储到项目的upload目录下
        String path="/" +"upload"+'/'+newFileName;
        File destFile = new File(dirPath+path);
        //如果upload目录不存在则创建
        if(!destFile.getParentFile().exists()){
            destFile.getParentFile().mkdir();
        }
        try{
           photo.transferTo(destFile);
            Photo ph = new Photo();
            ph.setCreateTime(new Date().toString());
            ph.setPhotograph(path);
            ph.setIsDelete(0);
            photoMapper.insert(ph);
            return Result.success(ph);
        }catch (IOException e){
            return null;
        }

    }
}
