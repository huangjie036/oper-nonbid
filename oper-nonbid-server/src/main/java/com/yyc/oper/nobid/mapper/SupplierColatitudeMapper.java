package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yyc.oper.nobid.supplier.SupplierColatitudeBean;
@Mapper
public interface SupplierColatitudeMapper {

    
  //添加合作范围
  	int addColatitudeList(@Param("colatitudelist") List<SupplierColatitudeBean> colatitudelist);
}