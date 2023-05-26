package com.lst.wxproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public String upload(MultipartFile photo, HttpServletRequest request) throws IOException {
        System.out.println(photo.getOriginalFilename());        //获取图片的原始名称
        System.out.println(photo.getContentType());         //获取文件类型

        String path = request.getServletContext().getRealPath("/upload/");
        System.out.println(path);
        saveFile(photo,path);
        return "success";
    }

    public void saveFile(MultipartFile photo,String path) throws IOException {
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdir();
        }

        File file = new File(path+photo.getOriginalFilename());
        photo.transferTo(file);
    }
}
