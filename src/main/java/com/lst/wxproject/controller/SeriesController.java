package com.lst.wxproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lst.wxproject.common.Constants;
import com.lst.wxproject.common.Result;
import com.lst.wxproject.entity.Series;
import com.lst.wxproject.entity.Swiper;
import com.lst.wxproject.mapper.SeriesMapper;
import com.lst.wxproject.mapper.SwiperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("series")
public class SeriesController {
    @Autowired
    SeriesMapper seriesMapper;
    @GetMapping
    public Result<?> swiperList(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10")Integer pageSize,
                                @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Series> wrapper = Wrappers.<Series>lambdaQuery();;
        Page<Series> userPage = seriesMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }


    @PostMapping
    public Result save (@RequestBody Series series){
        int insert = seriesMapper.insert(series);
        if (insert>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_ADD_FAIL_CODE,Constants.STATUS_CODE.DATA_ADD_FAIL_MSG);
        }
    }

    @PutMapping
    public Result update(@RequestBody Series series){
        int i = seriesMapper.updateById(series);
        if (i>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_UPDATE_FAIL_CODE,Constants.STATUS_CODE.DATA_UPDATE_FAIL_MSG);
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        int i = seriesMapper.deleteById(id);
        if (i>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_DELETE_FAIL_CODE,Constants.STATUS_CODE.DATA_DELETE_FAIL_MSG);
        }
    }

    @PostMapping("/{id}")
    public Result selectSeriesById(@PathVariable Integer id){
        Series series = seriesMapper.selectById(id);
        return Result.success(series);
    }

}
