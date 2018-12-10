package com.yyc.oper.nobid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yyc.oper.nobid.supplier.ErpSupplierBean;
import com.yyc.oper.nobid.supplier.SupplierBean;

@Mapper
public interface SupplierMapper {
    int deleteByPrimaryKey(String supplierId);

    int insert(List<SupplierBean> supplierList);

    int insertSelective(SupplierBean record);

    List<SupplierBean> selectByPrimaryKey(SupplierBean record);
    
    List<SupplierBean> selectBySupplierBean(SupplierBean record);

    int updateByPrimaryKeySelective(SupplierBean record);

    int updateByPrimaryKey(SupplierBean record);
    
    int insertSupplierList(List<ErpSupplierBean> list);

	int insertSupplier(List<SupplierBean> supplierList);
	
	List<Map<String, Object>> getSupplierList(@Param("str")String str);

	List<SupplierBean> selectSupplierInfo(SupplierBean record);

	void insertRelation(List<ErpSupplierBean> list);

	int insertImportSupplierList(@Param("list")List<SupplierBean> supplierList);

	List<String> selectSupplierBySupplierNameList(List<String> suppliernameList);
    
   
}