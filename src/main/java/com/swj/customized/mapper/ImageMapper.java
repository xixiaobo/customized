package com.swj.customized.mapper;

import com.swj.customized.bean.Image;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {
    int deleteByPrimaryKey(Integer id);


    @Delete("delete from c_image where productid=#{id}")
    int deleteByProduct(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer id);
    List<Image> selectBySelective(Image record);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKeyWithBLOBs(Image record);

    int updateByPrimaryKey(Image record);
}