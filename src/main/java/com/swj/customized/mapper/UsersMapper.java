package com.swj.customized.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.swj.customized.bean.Users;

@Mapper
public interface UsersMapper {
    /**
     * 添加用户
     * @param users
     * @return
     */
    int insert(@Param("users") Users users);

    /**
     * 将实体类有值得数据添加
     * @param users
     * @return
     */
    int insertSelective(@Param("users") Users users);

    /**
     * 批量添加用户
     * @param userss
     * @return
     */
    int insertList(@Param("userss") List<Users> userss);

    /**
     * 修改用户
     * @param users
     * @return
     */
    int updateByPrimaryKeySelective(@Param("users") Users users);

    /**
     * 查询指定条件用户
     * @param record
     * @return
     */
    List<Users> selectUserBySelective(Users record);

    /**
     * 根据用户id删除用户
     * @param userid
     * @return
     */
    int deleteByPrimaryKey(String userid);

    /**
     * 根据用户id查询用户
     * @param userid
     * @return
     */
    Users selectByPrimaryKey(String userid);
}
