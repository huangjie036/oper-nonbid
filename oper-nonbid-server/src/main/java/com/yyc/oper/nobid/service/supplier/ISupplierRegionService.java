package com.yyc.oper.nobid.service.supplier;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yyc.oper.nobid.supplier.SupplierRegionBean;

public interface ISupplierRegionService {

	int addRegionList(@Param("list")List<SupplierRegionBean> supplierlist);

}
