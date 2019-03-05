package com.swj.customized.mapper;

import com.swj.customized.bean.Classify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Classify record);

    int insertSelective(Classify record);

    Classify selectByPrimaryKey(Integer id);

    List<Classify> selectBySelective(Classify record);

    int updateByPrimaryKeySelective(Classify record);

    int updateByPrimaryKey(Classify record);

    @Select("select count(id) from c_product where classid=#{id}")
    int selectNumByPrimaryKey(@Param("id")Integer id);
}