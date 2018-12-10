package com.yyc.oper.nobid.service.supplier;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.supplier.SupplierEvalTypeBean;

/**
 * 评价分类接口
 * Description:   
 * @author hlg
 * @date 2018年9月19日
 */
public interface ISupplierEvalTypeService {

	/**
	 * 分页查询
	 * @param evalTypeBean
	 * @return
	 */
	PageInfo<SupplierEvalTypeBean> getPageInfo(SupplierEvalTypeBean evalTypeBean) throws Exception;

	
	/**
	 * 添加分类
	 * @param evalTypeBean
	 */
	void addType(SupplierEvalTypeBean evalTypeBean) throws Exception;

	
	/**
	 * 编辑分类
	 * @param evalTypeBean
	 * @throws Exception 
	 */
	void editType(SupplierEvalTypeBean evalTypeBean) throws Exception;


	/**
	 * 删除分类
	 * @param ids 评价分类ID集合
	 * @throws Exception
	 */
	void deleteType(List<String> ids) throws Exception;
	
	/**
	 * 根据环节ID查询评价分类集合
	 * @param questionLinkId
	 * @return List<Map<String, Object>> 评价分类集合
	 */
	List<Map<String, Object>> selectTypeByLinkId(String questionLinkId);

}
