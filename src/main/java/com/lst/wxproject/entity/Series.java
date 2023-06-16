package com.lst.wxproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("series")
public class Series {
    @TableId(type = IdType.AUTO)
    private Integer id;             //系列id
    private String seriesIntro;     //系列介绍
    private String seriesPic;       //系列图
    private String seriesName;      //系列名称
    private String createTime;      //创建时间
}
