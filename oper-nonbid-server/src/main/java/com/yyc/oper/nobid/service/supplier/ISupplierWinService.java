package com.yyc.oper.nobid.service.supplier;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.supplier.SupplierResponse;
import com.yyc.oper.nobid.supplier.SupplierWinBean;
import com.yyc.oper.nobid.supplier.SupplierWinDetailBean;
import com.yyc.oper.nobid.supplier.SupplierWinRequest;

public interface ISupplierWinService {

	int deleteByPrimaryKey(String supplierWinId);

    int insertSelective(SupplierWinBean record);

    PageInfo<SupplierWinBean> selectByPrimaryKey(SupplierWinBean record);

    int updateByPrimaryKeySelective(SupplierWinBean record);
    
    List<SupplierResponse> findSupplierWinByMergeId(SupplierWinBean record);

	

	SupplierWinDetailBean selectPurchaseResultDetail(SupplierWinRequest record);

	

}
