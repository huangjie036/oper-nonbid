package com.yyc.oper.nobid.service.supplier;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yyc.oper.nobid.supplier.SupplierTaxBean;

public interface ISupplierTaxService {

	int addTaxList(@Param("list")List<SupplierTaxBean> list);

}
