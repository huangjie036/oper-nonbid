package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.mat.NonMatplanBean;

@Mapper
public interface NonMatplanMapper {
    int deleteByPrimaryKey(String nonMatplanId);

    int insert(List<NonMatplanBean> record);

    int insertSelective(NonMatplanBean record);

    List<NonMatplanBean> selectByPrimaryKey(NonMatplanBean record);

    int updateByPrimaryKeySelective(NonMatplanBean record);

    int updateByPrimaryKey(NonMatplanBean record);

	List<NonMatplanBean> selectMatplanAndMat(NonMatplanBean temp1);
}