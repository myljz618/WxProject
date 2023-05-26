package com.lst.wxproject.controller;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lst.wxproject.common.Constants;
import com.lst.wxproject.common.Result;
import com.lst.wxproject.entity.Swiper;
import com.lst.wxproject.entity.User;
import com.lst.wxproject.mapper.SwiperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("swiper")
public class SwiperController {
    @Autowired
    SwiperMapper swiperMapper;
    @GetMapping
    public Result<?> swiperList(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10")Integer pageSize,
                               @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Swiper> wrapper = Wrappers.<Swiper>lambdaQuery();;
        wrapper.orderByDesc(Swiper::getAuthority);
        Page<Swiper> userPage = swiperMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }


    @PostMapping
    public Result save (@RequestBody Swiper swiper){
        int insert = swiperMapper.insert(swiper);
        if (insert>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_ADD_FAIL_CODE,Constants.STATUS_CODE.DATA_ADD_FAIL_MSG);
        }
    }

    @PutMapping
    public Result update(@RequestBody Swiper swiper){
        int i = swiperMapper.updateById(swiper);
        if (i>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_UPDATE_FAIL_CODE,Constants.STATUS_CODE.DATA_UPDATE_FAIL_MSG);
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        int i = swiperMapper.deleteById(id);
        if (i>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_DELETE_FAIL_CODE,Constants.STATUS_CODE.DATA_DELETE_FAIL_MSG);
        }
    }
}
