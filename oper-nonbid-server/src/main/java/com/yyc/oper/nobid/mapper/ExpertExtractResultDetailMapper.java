package com.yyc.oper.nobid.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.expert.ExpertExtractResultDetailBean;

@Mapper
public interface ExpertExtractResultDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExpertExtractResultDetailBean record);

    int insertSelective(ExpertExtractResultDetailBean record);

    ExpertExtractResultDetailBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExpertExtractResultDetailBean record);

    int updateByPrimaryKey(ExpertExtractResultDetailBean record);
}