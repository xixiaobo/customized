package com.swj.customized.mapper;

import com.swj.customized.bean.Classify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    @Update("update c_classify set classnum=classnum+1 where id = #{id}")
    int updateClassnumUp(@Param("id") Integer id);

    @Update("update c_classify set classnum=classnum-1 where id = #{id}")
    int updateClassnumDown(@Param("id") Integer id);
}