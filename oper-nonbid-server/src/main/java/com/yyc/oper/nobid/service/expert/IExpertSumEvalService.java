package com.yyc.oper.nobid.service.expert;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.expert.ExpertSumEvalBean;;
/**
 * Description:年度评价  接口  
 * @author hlg
 * @date 2018年9月13日
 */
public interface IExpertSumEvalService {
	/**
	 * 分页查询年度评价
	 * @param map
	 * @return
	 */
	PageInfo<ExpertSumEvalBean> getExpertSumEvals(ExpertSumEvalBean sumEvalBean) throws Exception;

	/**
	 * 新增年度评价
	 * @param sumEvalBean
	 * @throws Exception
	 */
	void addExpertSumEval(ExpertSumEvalBean sumEvalBean) throws Exception;

	/**
	 * 修改年度评价
	 * @param sumEvalBean
	 */
	void updateExpertSumEval(ExpertSumEvalBean sumEvalBean) throws Exception;

	/**
	 * 删除年度评价
	 * @param ids 集合
	 */
	void deleteExpertSumEval(List<String> ids) throws Exception;

	/**
	 * 导入年度评价
	 * @param readExcel
	 * @return
	 */
	String addResponsibilityInput(List<Map<Integer, Object>> readExcel);

	/**
	 * 导出年度评价
	 * @param set 年度评价ID集合
	 * @param request
	 * @param response
	 */
	void exportSumEval(Set<String> set, HttpServletRequest request, HttpServletResponse response);

}
