package com.yyc.oper.nobid.service.supplier;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yyc.oper.nobid.supplier.SupplierColatitudeBean;

public interface ISupplierColatitudeService {
	//供应商添加资质
	int addColatitudeList(@Param("list")List<SupplierColatitudeBean> list);

	
}
