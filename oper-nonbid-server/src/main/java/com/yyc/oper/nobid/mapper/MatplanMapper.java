package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.mat.MatplanBean;

@Mapper
public interface MatplanMapper {
    int deleteByPrimaryKey(String matplanId);

    int insert(List<MatplanBean> list);

    int insertSelective(MatplanBean record);

    List<MatplanBean> selectByPrimaryKey(MatplanBean record);

    int updateByPrimaryKeySelective(MatplanBean record);

    int updateByPrimaryKey(MatplanBean record);
    
    List<MatplanBean> selectMatplanAndMat(MatplanBean record);
    
}