package com.yyc.oper.nobid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.expert.ExpertSumEvalBean;

/**
 * Description:年度评价 
 * @author hlg
 * @date 2018年9月13日
 */
@Mapper
public interface ExpertSumEvalMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExpertSumEvalBean record);

    int insertSelective(ExpertSumEvalBean record);

    ExpertSumEvalBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExpertSumEvalBean record);

    int updateByPrimaryKey(ExpertSumEvalBean record);
    
    /**
     * 分页条件查询
     * @param expertSumEvalBean
     * @return
     */
	List<ExpertSumEvalBean> getSumEvalsByCondition(ExpertSumEvalBean expertSumEvalBean);
	
	/**
	 * 逻辑删除 考试评价
	 * @param id
	 */
	void deleteSumEval(String id);

	/**
	 * 根据id导出年度评价查询集合
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> selectSumEvals(String id);

}