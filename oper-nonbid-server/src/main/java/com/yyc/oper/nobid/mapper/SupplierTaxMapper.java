package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yyc.oper.nobid.supplier.SupplierTaxBean;

@Mapper
public interface SupplierTaxMapper {

	

	int addTaxList(@Param("taxlist")List<SupplierTaxBean> taxlist);

}
