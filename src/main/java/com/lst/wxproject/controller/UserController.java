package com.lst.wxproject.controller;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lst.wxproject.common.Constants;
import com.lst.wxproject.common.Result;
import com.lst.wxproject.entity.User;
import com.lst.wxproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping
    public Result<?> agentList(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10")Integer pageSize,
                               @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();;
        wrapper.orderByDesc(User::getCreateTime);
        wrapper.like(User::getUserType,0);
        if(StrUtil.isAllBlank(search)){
            wrapper.like(User::getPhoneNum,search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }

    @PostMapping
    public Result save (@RequestBody User user){
        user.setCreateTime(StringUtils.toStringTrim(new Date()));
        user.setUserType(0);
        boolean b = PhoneUtil.isMobile(user.getPhoneNum());
        if (b==false){
            return Result.error(Constants.STATUS_CODE.PHONENUM_ERR_CODE,Constants.STATUS_CODE.PHONENUM_ERR_MSG);
        }
        int insert = userMapper.insert(user);
        if (insert>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_ADD_FAIL_CODE,Constants.STATUS_CODE.DATA_ADD_FAIL_MSG);
        }
    }

    @PutMapping
    public Result update(@RequestBody User user){
        int i = userMapper.updateById(user);
        if (i>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_UPDATE_FAIL_CODE,Constants.STATUS_CODE.DATA_UPDATE_FAIL_MSG);
        }
    }

}
