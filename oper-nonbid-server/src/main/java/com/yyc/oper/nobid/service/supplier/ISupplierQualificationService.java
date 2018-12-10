package com.yyc.oper.nobid.service.supplier;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yyc.oper.nobid.supplier.SupplierQualificationBean;

public interface ISupplierQualificationService {
	//供应商添加资质
	int addQualificationList(@Param("list")List<SupplierQualificationBean> list);
}
