package com.lst.wxproject.controller;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lst.wxproject.common.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lst.wxproject.common.Constants;
import com.lst.wxproject.common.Result;
import com.lst.wxproject.entity.User;
import com.lst.wxproject.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping
    public Result<?> agentList(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10")Integer pageSize,
                               @RequestParam(required = false )Integer userType,
                               @RequestParam(required = false )String username
                               ){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();;
        wrapper.orderByDesc(User::getCreateTime);
        wrapper.like(User::getUsername,username);
        wrapper.eq(User::getUserType,userType);
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }

    @PostMapping
    public Result save (@RequestBody User user){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sf.format(new Date());
        user.setCreateTime(format);
        if (user.getUserType().equals(1)){
            boolean b = PhoneUtil.isMobile(user.getPhoneNum());
            if (b==false){
                return Result.error(Constants.STATUS_CODE.PHONENUM_ERR_CODE,Constants.STATUS_CODE.PHONENUM_ERR_MSG);
            }
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

    @PostMapping("/login")
    public Result login(User user)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User selectOne = userMapper.selectOne(queryWrapper);
        if (selectOne==null){
            return Result.error(Constants.STATUS_CODE.USER_LOGIN_FAIL_CODE,Constants.STATUS_CODE.USER_LOGIN_FAIL_MSG);
        }
        String token = JWTUtil.generateToken(user.getUsername());
        user.setToken(token);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username",user.getUsername()).set("token",token);
        userMapper.update(null,updateWrapper);
        return Result.success(user);
    }

    @GetMapping("/info")
    public Result info(String token){
        String username=JWTUtil.getClaimsByToken(token).getSubject();
        User user = new User();
        user.setUsername(username);
        return Result.success(user);
    }

    @PostMapping("/logout")
    public Result logout(){
        return Result.success();
    }

    @PostMapping("/statusChange")
    public Result <?> statusChange(@RequestBody User user){
        if(user.getIsActive() == 0){
            user.setIsActive(1);
        }else {
            user.setIsActive(0);
        }
        int i = userMapper.updateById(user);
        if (i==0){
            return Result.error(Constants.STATUS_CODE.STATUS_CHANGE_FAIL_CODE,Constants.STATUS_CODE.STATUS_CHANGE_FAIL_MSG);
        }
        return Result.success();
    }
}
