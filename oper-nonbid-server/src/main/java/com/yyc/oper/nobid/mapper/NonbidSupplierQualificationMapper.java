package com.yyc.oper.nobid.mapper;

import com.yyc.oper.nobid.supplier.SupplierQualificationBean;

public interface NonbidSupplierQualificationMapper {
    int deleteByPrimaryKey(String id);

    int insert(SupplierQualificationBean record);

    int insertSelective(SupplierQualificationBean record);

    SupplierQualificationBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SupplierQualificationBean record);

    int updateByPrimaryKey(SupplierQualificationBean record);
}