package com.swj.customized.mapper;

import com.swj.customized.bean.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);
    @Delete("delete from c_message where productid=#{id}")
    int deleteByProduct(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectBySelective(Message record);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}