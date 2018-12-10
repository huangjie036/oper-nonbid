package com.yyc.oper.nobid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.expert.ExpertBean;
import com.yyc.oper.nobid.expert.ExtractSupplier;
import com.yyc.oper.nobid.mat.MatplanBean;
import com.yyc.oper.nobid.mat.NonMatplanBean;
import com.yyc.oper.nobid.purchase.PurchaseSchemeBean;
import com.yyc.oper.nobid.purchase.PurchaseSchemeRequest;
import com.yyc.oper.nobid.purchase.PurchaseSchemeResponse;
import com.yyc.oper.nobid.supplier.SupplierBean;

@Mapper
public interface PurchaseSchmeMapper {
	/**
	 * 
	 * 更新状态
	 *
	 * @param map
	 *            【state-状态值，purchaseId-方案编号】
	 * @return
	 */
	public int updateState(Map<String, Object> map);



	/**
	 * 
	 * 查询采购方案的基础详情
	 *
	 * @param purchaseId
	 *            方案唯一编号
	 * @return
	 */
	public PurchaseSchemeBean selectDetail(String purchaseId);



	/**
	 * 
	 * 查询采购方案抽取的供应商的抽取结果信息
	 *
	 * @param mergeId
	 *            采购方案的合包编号
	 * @return
	 */
	public List<ExtractSupplier> selectExtractSuppilerBean(String mergeId);



	/**
	 * 
	 * 查询采购方案抽取的供应商列表信息
	 *
	 * @param mergeId
	 *            采购方案的合包编号
	 * @return
	 */
	public List<SupplierBean> selectSupplierBean(String mergeId);



	/**
	 * 
	 * 查询采购方案抽取的专家的抽取结果信息
	 *
	 * @param mergeId
	 *            采购方案的合包编号
	 * @return
	 */
	public List<Map<String, String>> selectExtractExpertBean(String purchaseId);



	/**
	 * 
	 * 查询采购方案抽取的专家列表信息
	 *
	 * @param mergeId
	 *            采购方案的合包编号
	 * @return
	 */
	public List<ExpertBean> selectExpertBean(String mergeId);



	/**
	 * 
	 * 查询采购方案的物料列表信息
	 *
	 * @param mergeId
	 *            采购方案的合包编号
	 * @return
	 */
	public List<MatplanBean> selectMatplanBean(String mergeId);



	/**
	 * 
	 * 查询采购方案的非物料列表信息
	 *
	 * @param mergeId
	 *            采购方案的合包编号
	 * @return
	 */
	public List<NonMatplanBean> selectNonMatplanBean(String mergeId);
	
	
	/**
	 * 
	 * 查询采购方案列表
	 *
	 * @param record
	 *            采购方案列表查询参数
	 * @return
	 */
	public List<PurchaseSchemeResponse> selectByPurchaseSchemeRequest(PurchaseSchemeRequest record);



	public int selectCountNum(String mergeId);



	public int deleteMergeState(Map<String, Object> map);



	public int delete(Map<String, Object> map);



	public int deleteName(Map<String, Object> map);



	public int deleteNum(Map<String, Object> map);



	public int updateMatState(Map<String, Object> map);
}
