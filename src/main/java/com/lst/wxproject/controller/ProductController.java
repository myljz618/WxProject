package com.lst.wxproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lst.wxproject.common.Constants;
import com.lst.wxproject.common.Result;
import com.lst.wxproject.entity.Order;
import com.lst.wxproject.entity.Product;
import com.lst.wxproject.entity.Series;
import com.lst.wxproject.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @GetMapping
    public Result<?> swiperList(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10")Integer pageSize,
                                @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Product> wrapper = Wrappers.<Product>lambdaQuery();;
        wrapper.orderByDesc(Product::getCreateTime);
        Page<Product> userPage = productMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }

    @PostMapping
    public Result save (@RequestBody Product product){
        int insert = productMapper.insert(product);
        if (insert>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_ADD_FAIL_CODE,Constants.STATUS_CODE.DATA_ADD_FAIL_MSG);
        }
    }

    @PutMapping
    public Result update(@RequestBody Product product){
        int i = productMapper.updateById(product);
        if (i>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_UPDATE_FAIL_CODE,Constants.STATUS_CODE.DATA_UPDATE_FAIL_MSG);
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        int i = productMapper.deleteById(id);
        if (i>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_DELETE_FAIL_CODE,Constants.STATUS_CODE.DATA_DELETE_FAIL_MSG);
        }
    }
}
