package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.supplier.SupplierEvalBean;

@Mapper
public interface SupplierEvalMapper {
    int deleteByPrimaryKey(String supplierEvalId);

    int insert(SupplierEvalBean record);

    int insertSelective(SupplierEvalBean record);

    List<SupplierEvalBean> selectByPrimaryKey(SupplierEvalBean record);

    int updateByPrimaryKeySelective(SupplierEvalBean record);

    int updateByPrimaryKey(SupplierEvalBean record);

	List<SupplierEvalBean> selectProjectBySupplier(SupplierEvalBean record);
}