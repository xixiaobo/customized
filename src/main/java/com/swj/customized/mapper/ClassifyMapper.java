package com.swj.customized.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.swj.customized.bean.Classify;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClassifyMapper {
    /**
     * 添加分类
     * @param classify
     * @return
     */
    int insert(@Param("classify") Classify classify);

    /**
     * 添加分类
     * @param classify
     * @return
     */
    int insertSelective(@Param("classify") Classify classify);

    /**
     * 批量添加分类
     * @param classifys
     * @return
     */
    int insertList(@Param("classifys") List<Classify> classifys);

    /**
     * 修改分类
     * @param classify
     * @return
     */
    int updateByPrimaryKeySelective(@Param("classify") Classify classify);

    /**
     * 根据条件查询分类
     * @param classify1
     * @return
     */
    List<Classify> selectBySelective(Classify classify1);

    /**
     * 查询分类下有多少产品
     * @param id
     * @return
     */
    @Select("select count(id) from c_product where classid=#{id}")
    int selectNumByPrimaryKey(@Param("id")Integer id);

    /**
     * 根据id删除分类
     * @param classifyid
     * @return
     */
    int deleteByPrimaryKey(int classifyid);

    /**
     * 根据id获取分类
     * @param classifyid
     * @return
     */
    Classify selectByPrimaryKey(int classifyid);
}
