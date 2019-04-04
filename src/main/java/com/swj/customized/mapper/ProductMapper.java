package com.swj.customized.mapper;

import com.swj.customized.bean.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(@Param("ids") List<String> ids);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    List<Product> selectBySelective(Product record);

    List<Product> selectBySixNew();

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    @Insert("INSERT INTO `c_product_score`(`product_id`, `user_id`, `score`) " +
            "VALUES (#{product_id},#{user_id},#{score});")
    int addProductscoreByUser(@Param("product_id")String product_id,@Param("user_id")String user_id,@Param("score")double score);

    @Select("SELECT COUNT(user_id) num from c_product_score where product_id=#{product_id} and user_id= #{user_id}")
    int selectishavauserByscore(@Param("product_id")String product_id,@Param("user_id")String user_id);

}