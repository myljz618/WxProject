package com.lst.wxproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("photograph")
public class Photo {
    @TableId(type = IdType.AUTO)
    private Integer id;         //图片ID
    private String photograph;  //图片链接
    private String createTime;  //创建时间
    private Integer isDelete;   //是否删除
}
