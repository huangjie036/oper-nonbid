package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yyc.oper.nobid.supplier.SupplierQualificationBean;

@Mapper
public interface SupplierQualificationMapper {
	//添加资质
	int addQualificationList(@Param("list") List<SupplierQualificationBean> qualificationlist);
}
