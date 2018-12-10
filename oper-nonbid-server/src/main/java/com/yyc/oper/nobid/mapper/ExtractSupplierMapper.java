package com.yyc.oper.nobid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.expert.ExtractSupplier;
import com.yyc.oper.nobid.supplier.SupplierBean;

@Mapper
public interface ExtractSupplierMapper {

	int insertrExtractSupplierExtract(ExtractSupplier extractSupplier);



	int insertSupplierExtractChild(List<ExtractSupplier> extractSupplier);



	/**
	 * 
	 * 根据合包编号查询推荐的供应商编号集合
	 *
	 * @param mergeId
	 *            合包编号
	 * @return
	 */
	List<String> selectRecommandedSupplierIdListByMergeId(String mergeId);



	List<String> selectSupplierBySupplierIdList(List<String> supplierIdList);



	Map<String, Object> selectMergeRecordByMergeId(String mergeId);



	List<String> selectSupplierIdList(SupplierBean supplierBean);



	List<String> selectSupplierBySupplierNameList(List<String> supplierNameList);



	List<String> selectNewSupplierBySupplierIdList(List<String> supplierIdList);



	int insertExtractSupplierState(ExtractSupplier extractSupplier);

}
