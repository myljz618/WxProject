package com.lst.wxproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer id;             //产品id
    private Integer seriesId;       //系列id
    private String productIntro;    //产品介绍
    private String createTime;      //产品创建时间
    private String productPic;      //产品图
}
