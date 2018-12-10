package com.yyc.oper.nobid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.mdict.MdictBean;

/**
 * Description: 数据字典维护  
 * @author hlg
 * @date 2018年9月26日
 */
@Mapper
public interface MdictMapper {
    int deleteByPrimaryKey(String id);

    int insert(MdictBean record);

    int insertSelective(MdictBean record);

    MdictBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MdictBean record);

    int updateByPrimaryKey(MdictBean record);

	List<MdictBean> selectMadicts();

	/**
	 * 逻辑删除字典数据
	 * @param id 
	 * @return
	 */
	int deleteMdictById(String id);

	/**
	 * 根据类型查询数据
	 * @param keyword
	 * @return
	 */
	List<Map<String, Object>> selectMdictsByType(String keyword);
	
}