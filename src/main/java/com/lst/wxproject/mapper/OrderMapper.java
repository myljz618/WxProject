package com.lst.wxproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lst.wxproject.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
