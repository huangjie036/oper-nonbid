package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.supplier.SupplierExtractResultBean;

@Mapper
public interface SupplierExtractResultMapper {
    int deleteByPrimaryKey(String extractId);

    int insert(SupplierExtractResultBean record);

    int insertSelective(SupplierExtractResultBean record);

    List<SupplierExtractResultBean> selectByPrimaryKey(SupplierExtractResultBean record);

    int updateByPrimaryKeySelective(SupplierExtractResultBean record);

    int updateByPrimaryKey(SupplierExtractResultBean record);
    
    List<SupplierExtractResultBean> selectSupplierExtractByMergeId(SupplierExtractResultBean record);
}