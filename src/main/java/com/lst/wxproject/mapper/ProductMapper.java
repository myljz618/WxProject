package com.lst.wxproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lst.wxproject.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
