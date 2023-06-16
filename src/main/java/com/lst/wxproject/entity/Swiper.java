package com.lst.wxproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("swiper")
public class Swiper {
    @TableId(type = IdType.AUTO)
    private Integer id;             //轮播图id
    private String swiperIntro;     //轮播图介绍
    private String swiperPic;       //轮播图图片
    private Integer authority;      //轮播图权重
    private String createTime;      //创建时间
}
