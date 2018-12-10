package com.yyc.oper.nobid.service.supplier;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.supplier.SupplierEvalBean;

public interface ISupplierEvalService {

	int deleteByPrimaryKey(String supplierEvalId);

    int insertSelective(SupplierEvalBean record);

    PageInfo<SupplierEvalBean> selectByPrimaryKey(SupplierEvalBean record);

    int updateByPrimaryKeySelective(SupplierEvalBean record);

	List<SupplierEvalBean> selectProjectBySupplier(SupplierEvalBean record);

	int updateByPrimaryKey(String[] supplierEvalIds);

}
