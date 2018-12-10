package com.yyc.oper.nobid.mapper;

import com.yyc.oper.nobid.supplier.NonbidSupplierRegion;

public interface NonbidSupplierRegionMapper {
    int deleteByPrimaryKey(String id);

    int insert(NonbidSupplierRegion record);

    int insertSelective(NonbidSupplierRegion record);

    NonbidSupplierRegion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NonbidSupplierRegion record);

    int updateByPrimaryKey(NonbidSupplierRegion record);
}