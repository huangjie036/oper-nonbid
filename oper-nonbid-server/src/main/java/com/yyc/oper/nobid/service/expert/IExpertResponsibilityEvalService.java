package com.yyc.oper.nobid.service.expert;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.expert.ExpertResponsibilityEvalBean;

/**
 * Description:履责评价接口
 * @author hlg
 * @date 2018年9月13日
 */
public interface IExpertResponsibilityEvalService  {

	
	/**
	 * 分页查询履责评价
	 * @param responsibilityEvalBean
	 * @return
	 */
	PageInfo<ExpertResponsibilityEvalBean> getResponsibilitys(ExpertResponsibilityEvalBean responsibilityEvalBean) throws Exception;

	/**
	 * 新增履责评价
	 * @param responsibilityEvalBean
	 * @throws Exception
	 */
	void addResponsibility(ExpertResponsibilityEvalBean responsibilityEvalBean) throws Exception;

	/**
	 * 修改履责评价
	 * @param responsibilityEvalBean
	 */
	void updateResponsibility(ExpertResponsibilityEvalBean Responsibility) throws Exception;

	/**
	 * 删除履责评价
	 * @param ids
	 */
	void deleteResponsibility(List<String> ids) throws Exception;

	/**
	 * 导入履责评价
	 * @param readExcel
	 * @return
	 */
	String addResponsibilityInput(List<Map<Integer, Object>> readExcel);

	/**
	 * 导出履责评价
	 * @param set id集合
	 * @param request
	 * @param response
	 */
	void exportResponsibility(Set<String> set, HttpServletRequest request, HttpServletResponse response);
}
