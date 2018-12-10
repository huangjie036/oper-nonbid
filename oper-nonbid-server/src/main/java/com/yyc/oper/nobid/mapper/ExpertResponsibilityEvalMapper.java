package com.yyc.oper.nobid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.expert.ExpertResponsibilityEvalBean;
/**
 * 
 * Description:履责评价mapper   
 * @author hlg
 * @date 2018年9月13日
 */
@Mapper
public interface ExpertResponsibilityEvalMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExpertResponsibilityEvalBean record);

    int insertSelective(ExpertResponsibilityEvalBean record);

    ExpertResponsibilityEvalBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExpertResponsibilityEvalBean record);

    int updateByPrimaryKey(ExpertResponsibilityEvalBean record);
    
    /**
     * 分页条件查询
     * @param ExpertResponsibilityEvalBean
     * @return
     */
	List<ExpertResponsibilityEvalBean> getResponsibilityCondition(ExpertResponsibilityEvalBean responsibilityEvalBean);

	/**
	 * 逻辑删除履责评价
	 * @param id
	 */
	void deleteResponsibility(String id);

	/**
	 * 导出履责评价
	 * @param id 履责ID
	 * @return
	 */
	List<Map<String, Object>> selectResponsibilitys(String id);
}