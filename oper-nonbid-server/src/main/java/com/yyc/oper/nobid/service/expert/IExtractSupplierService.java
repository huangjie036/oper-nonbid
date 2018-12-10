package com.yyc.oper.nobid.service.expert;

import java.util.List;

import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.supplier.SupplierBean;

public interface IExtractSupplierService {
	/**
	 * 
	 * 保存采购方案的供应商抽取记录
	 *
	 * @param purchaseSchmeId
	 *            采购方案的编号
	 * @param mergeId
	 *            采购计划的合包编号
	 * @param opertionMethod
	 *            操作方式
	 * @param supplierCount
	 *            供应商需求数量
	 * @param supplierIdList
	 *            供应商的主键编号列表
	 * @return
	 */
	ResultMessage saveSupplierExtract(String purchaseSchmeId, String mergeId, String opertionMethod,
			Integer supplierCount, List<String> supplierIdList);



	public List<SupplierBean> getSupplierByParam(SupplierBean record);



	ResultMessage saveSupplierExtractManual(String purchaseSchmeId, String mergeId, String opertionMethod,
			Integer supplierCount, Integer recommendSupplierCount, List<String> supplierIdList, List<String> supplierIdList2);



	ResultMessage saveSupplierExtractRandom(String purchaseSchmeId, String mergeId, String opertionMethod,
			Integer supplierCount, Integer recommendSupplierCount, List<String> supplierIdList, List<String> supplierIdList2);



	List<String> selectSupplierIdList(SupplierBean supplierBean);



	List<String> selectSupplierBySupplierNameList(List<String> supplierNameList);



	List<String> selectNewSupplierBySupplierIdList(List<String> supplierIdList);



	ResultMessage saveSupplierExtractImport(String purchaseSchmeId, String mergeId, String opertionMethod,
			Integer supplierCount, List<String> supplierIdList);

}
