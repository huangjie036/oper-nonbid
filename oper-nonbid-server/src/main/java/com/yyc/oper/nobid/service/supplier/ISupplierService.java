package com.yyc.oper.nobid.service.supplier;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.supplier.ErpSupplierBean;
import com.yyc.oper.nobid.supplier.SupplierBean;

public interface ISupplierService {

	int deleteByPrimaryKey(String supplierId);



	int insertSelective(SupplierBean record);



	PageInfo<SupplierBean> selectByPrimaryKey(SupplierBean record);



	int updateByPrimaryKeySelective(SupplierBean record);



	int updateByPrimaryKey(String[] supplierIds);



	ResultMessage insertSupplier(List<Map<Integer, Object>> readExcel);
	
	List<Map<String, Object>> getSupplierList(@Param("str")String str);



	void exportSupplier(Set<String> set, HttpServletRequest request, HttpServletResponse response);



	int insertSupplierList(List<ErpSupplierBean> supplierlist);



	List<SupplierBean> selectSupplierInfo(SupplierBean record);



	int updateStateByPrimaryKey(String[] supplierIds);

}
