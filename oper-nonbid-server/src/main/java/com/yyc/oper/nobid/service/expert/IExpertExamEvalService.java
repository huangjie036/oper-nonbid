package com.yyc.oper.nobid.service.expert;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.expert.ExpertExamEvalBean;

/**
 * 
 * Description:专家评价-考试评价service   
 * @author hlg
 * @date 2018年9月12日
 */
public interface IExpertExamEvalService {

	/**
	 * 分页查询考试评价
	 * @param map
	 * @return
	 */
	PageInfo<ExpertExamEvalBean> getExamEvals(ExpertExamEvalBean examEvalBean) throws Exception;

	/**
	 * 新增考试评价
	 * @param examEvalBean
	 * @throws Exception
	 */
	void addexamEval(ExpertExamEvalBean examEvalBean) throws Exception;

	/**
	 * 修改考试评价
	 * @param examEvalBean
	 */
	void updateExamEval(ExpertExamEvalBean examEvalBean) throws Exception;

	/**
	 * 删除考试评价
	 * @param ids
	 */
	void deleteExamEval(List<String> ids) throws Exception;

	/**
	 * 批量导入考试评价
	 * @param readExcel
	 * @return
	 */
	String addEvalsInput(List<Map<Integer, Object>> readExcel);

	/**
	 * 考试评价数据导出
	 * @param set id集合
	 * @param request
	 * @param response
	 */
	void exportExamEval(Set<String> set, HttpServletRequest request, HttpServletResponse response);


}
