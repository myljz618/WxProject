package com.lst.wxproject.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lst.wxproject.common.Constants;
import com.lst.wxproject.common.Result;
import com.lst.wxproject.entity.Order;
import com.lst.wxproject.entity.OrderNum;
import com.lst.wxproject.entity.Product;
import com.lst.wxproject.entity.User;
import com.lst.wxproject.mapper.OrderMapper;
import com.lst.wxproject.mapper.OrderNumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderNumMapper orderNumMapper;

    @GetMapping
    public Result<?> swiperList(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10")Integer pageSize,
                                @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Order> wrapper = Wrappers.<Order>lambdaQuery();;
        wrapper.orderByDesc(Order::getCreateTime);
        wrapper.like(Order::getIsActive,0);
        if(StrUtil.isAllBlank(search)){
            wrapper.like(Order::getOrderNum,search);
        }
        Page<Order> userPage = orderMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }

    @PostMapping
    public Result save (@RequestBody Order order){
        int insert = orderMapper.insert(order);

        if (insert>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_ADD_FAIL_CODE,Constants.STATUS_CODE.DATA_ADD_FAIL_MSG);
        }
    }

    @PutMapping
    public Result update(@RequestBody Order order){
        int i = orderMapper.updateById(order);
        if (i>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_UPDATE_FAIL_CODE,Constants.STATUS_CODE.DATA_UPDATE_FAIL_MSG);
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        int i = orderMapper.deleteById(id);
        if (i>0){
            return Result.success();
        }else{
            return Result.error(Constants.STATUS_CODE.DATA_DELETE_FAIL_CODE,Constants.STATUS_CODE.DATA_DELETE_FAIL_MSG);
        }
    }

   /* public String getOrderNum(){
        //判断是否存在
        LambdaQueryWrapper<OrderNum> wrapper = Wrappers.<OrderNum>lambdaQuery();
        wrapper.like()
        orderMapper.selectList()


        return "null";
    }*/



}