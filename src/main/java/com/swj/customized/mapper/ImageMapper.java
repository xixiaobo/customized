package com.swj.customized.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.swj.customized.bean.Image;

@Mapper
public interface ImageMapper {
    /**
     * 添加图片
     * @param image
     * @return
     */
    int insert(@Param("image") Image image);

    /**
     * 添加图片
     * @param image
     * @return
     */
    int insertSelective(@Param("image") Image image);

    /**
     * 批量添加图片
     * @param images
     * @return
     */
    int insertList(@Param("images") List<Image> images);

    /**
     * 修改图片
     * @param image
     * @return
     */
    int updateByPrimaryKeySelective(@Param("image") Image image);

    /**
     * 根据id删除图片
     * @param imageid
     * @return
     */
    int deleteByPrimaryKey(int imageid);


    /**
     * 根据id批量删除图片
     * @param ids
     * @return
     */
    int deleteList(@Param("ids")List<Integer> ids);

    /**
     * 根据id查询图片
     * @param imageid
     * @return
     */
    Image selectByPrimaryKey(int imageid);

    /**
     * 根据条件查询图片
     * @param image
     * @return
     */
    List<Image> selectBySelective(Image image);
}
