package com.lst.wxproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;         //用户id
    private String username;    //用户名
    private String password;    //密码
    private Integer userType;   //用户类型  0.后台用户  1.经销商
    private Integer isActive;   //激活 0.待机 1.激活
    private String remarks;     //备注
    private String createTime;  //创建时间
    private String phoneNum;    //手机号码
}
