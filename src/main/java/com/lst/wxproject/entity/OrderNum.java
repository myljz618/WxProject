package com.lst.wxproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("orderNum")
public class OrderNum {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String date;
    private int sequence;
}
