package com.swj.customized.mapper;

import com.swj.customized.bean.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     * 批量删除产品
     * @param ids
     * @return
     */
    int deleteByPrimaryKey(@Param("ids") List<String> ids);

    /**
     * 添加产品
     * @param record
     * @return
     */
    int insert(Product record);

    /**
     * 添加有值数据
     * @param record
     * @return
     */
    int insertSelective(Product record);

    /**
     * 根据产品id获取产品
     * @param id
     * @return
     */
    Product selectByPrimaryKey(String id);

    /**
     * 根据条件获取产品
     * @param record
     * @return
     */
    List<Product> selectBySelective(Product record);

    /**
     * 获取最新添加的6个产品
     * @return
     */
    List<Product> selectBySixNew();

    /**
     * 修改产品
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Product record);


    @Insert("INSERT INTO `c_product_score`(`product_id`, `user_id`, `score`) " +
            "VALUES (#{product_id},#{user_id},#{score});")
    int addProductscoreByUser(@Param("product_id") String product_id, @Param("user_id") String user_id, @Param("score") double score);

    @Select("SELECT COUNT(user_id) num from c_product_score where product_id=#{product_id} and user_id= #{user_id}")
    int selectishavauserByscore(@Param("product_id") String product_id, @Param("user_id") String user_id);

}