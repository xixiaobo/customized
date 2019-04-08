package com.swj.customized.mapper;

import com.swj.customized.dto.ProductDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.swj.customized.bean.Product;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
    /**
     * 添加产品
     * @param product
     * @return
     */
    int insert(@Param("product") Product product);

    /**
     * 添加产品
     * @param product
     * @return
     */
    int insertSelective(@Param("product") Product product);

    /**
     * 批量添加产品
     * @param products
     * @return
     */
    int insertList(@Param("products") List<Product> products);

    /**
     * 修改产品
     * @param product
     * @return
     */
    int updateByPrimaryKeySelective(@Param("product") Product product);


    /**
     * 根据条件获取产品
     * @param product1
     * @return
     */
    List<ProductDto> selectBySelective(ProductDto product1);

    /**
     * 用户进行评分
     * @param product_id
     * @param user_id
     * @param score
     * @return
     */
    @Insert("INSERT INTO `c_product_score`(`product_id`, `user_id`, `score`) " +
            "VALUES (#{product_id},#{user_id},#{score});")
    int addProductscoreByUser(@Param("product_id") String product_id, @Param("user_id") String user_id, @Param("score") double score);


    /**
     * 判断用户是否评分
     * @param product_id
     * @param user_id
     * @return
     */
    @Select("SELECT COUNT(user_id) num from c_product_score where product_id=#{product_id} and user_id= #{user_id}")
    int selectishavauserByscore(@Param("product_id") String product_id, @Param("user_id") String user_id);

    /**
     * 获取用的对此产品的评分
     * @param product_id
     * @param user_id
     * @return
     */
    @Select("SELECT score num from c_product_score where product_id=#{product_id} and user_id= #{user_id}")
    double getProductScoreByUserid(@Param("product_id") String product_id, @Param("user_id") String user_id);

    /**
     * 根据产品id获取产品
     * @param productId
     * @return
     */
    ProductDto selectByPrimaryKey(String productId);

    /**
     * 批量删除产品
     * @param ids
     * @return
     */
    int deleteByPrimaryKey(@Param("ids")  List<String> ids);

    /**
     * 获取最新添加的6个产品
     * @return
     */
    List<ProductDto> selectBySixNew();
}
