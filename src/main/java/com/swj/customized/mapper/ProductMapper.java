package com.swj.customized.mapper;

import com.swj.customized.bean.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectBySelective(Product record);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}