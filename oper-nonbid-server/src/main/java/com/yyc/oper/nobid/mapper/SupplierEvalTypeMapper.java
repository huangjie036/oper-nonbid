package com.yyc.oper.nobid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.supplier.SupplierEvalTypeBean;
/**
 * 评价分类
 * Description:   
 * @author hlg
 * @date 2018年9月19日
 */
@Mapper
public interface SupplierEvalTypeMapper {
    int deleteByPrimaryKey(String supplierEvalTypeId);

    int insert(SupplierEvalTypeBean record);

    int insertSelective(SupplierEvalTypeBean record);

    SupplierEvalTypeBean selectByPrimaryKey(String supplierEvalTypeId);

    int updateByPrimaryKeySelective(SupplierEvalTypeBean record);

    int updateByPrimaryKey(SupplierEvalTypeBean record);

    /**
     * 分页条件查询
     * @param evalTypeBean
     * @return
     */
	List<SupplierEvalTypeBean> selectTypePage(SupplierEvalTypeBean evalTypeBean);

	/**
	 * 逻辑删除分类
	 * @param supplierEvalTypeId
	 */
	void deleteType(String supplierEvalTypeId);

	/**
	 * 根据环节ID查询评价(统计条数)
	 * @param questionLinkId
	 * @return
	 */
	int getTypeByLinkId(String questionLinkId);
	
	/**
	 * 根据环节ID查询评价分类集合
	 * @param questionLinkId
	 * @return List<Map<String, Object>> 评价分类集合
	 */
	List<Map<String, Object>> selectTypeByLinkId(String questionLinkId);
}