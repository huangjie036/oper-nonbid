package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yyc.oper.nobid.supplier.SupplierRegionBean;

@Mapper
public interface SupplierRegionMapper {
	int addRegionList(@Param("regionlist")List<SupplierRegionBean> regionlist);
}
