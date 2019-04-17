package com.swj.customized.mapper;

import com.swj.customized.dto.MessageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.swj.customized.bean.Message;

@Mapper
public interface MessageMapper {
    /**
     * 添加留言
     * @param message
     * @return
     */
    int insert(@Param("message") Message message);

    /**
     * 添加留言
     * @param message
     * @return
     */
    int insertSelective(@Param("message") Message message);

    /**
     * 批量添加留言
     * @param messages
     * @return
     */
    int insertList(@Param("messages") List<Message> messages);

    /**
     * 修改留言
     * @param message
     * @return
     */
    int updateByPrimaryKeySelective(@Param("message") Message message);

    /**
     * 根据id删除留言
     * @param messageid
     * @return
     */
    int deleteByPrimaryKey(int messageid);

    /**
     * 根据id查询留言
     * @param messageid
     * @return
     */
    Message selectByPrimaryKey(int messageid);

    /**
     * 根据条件查询留言
     * @param select
     * @return
     */
    List<MessageDto> selectBySelective(Message select);
}
