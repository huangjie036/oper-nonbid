package com.yyc.oper.nobid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.expert.ExpertExamEvalBean;

@Mapper
public interface ExpertExamEvalMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExpertExamEvalBean record);

    int insertSelective(ExpertExamEvalBean record);

    ExpertExamEvalBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExpertExamEvalBean record);

    int updateByPrimaryKey(ExpertExamEvalBean record);

    /**
     * 分页条件查询
     * @param examEvalBean
     * @return
     */
	List<ExpertExamEvalBean> getEvalsByCondition(ExpertExamEvalBean examEvalBean);

	/**
	 * 逻辑删除 考试评价
	 * @param id
	 */
	void deleteExpertExamEval(String id);

	/**
	 * 批量导入考试评价
	 * @param evalBeans
	 * @return
	 */
	int inserts(List<ExpertExamEvalBean> evalBeans);

	/**
	 * 根据ID查询出考试评级Map集合
	 * @param id 集合ID
	 * @return <Map<String, Object>
	 */
	List<Map<String, Object>> selectExamEvals(String id);
}