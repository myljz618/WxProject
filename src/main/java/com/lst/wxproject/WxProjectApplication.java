package com.lst.wxproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lst.wxproject.mapper")
public class WxProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxProjectApplication.class, args);
    }

}
