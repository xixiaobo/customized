package com.swj.customized.mapper;

import com.swj.customized.bean.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(String id);

    List<Users> selectUserBySelective(Users record);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}