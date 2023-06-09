package com.lst.wxproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@MapperScan("com.lst.wxproject.mapper")
@CrossOrigin
@SpringBootApplication
public class WxProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxProjectApplication.class, args);
    }

}
