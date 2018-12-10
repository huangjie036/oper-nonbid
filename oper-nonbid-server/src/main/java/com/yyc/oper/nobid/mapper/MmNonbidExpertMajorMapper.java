package com.yyc.oper.nobid.mapper;

import com.yyc.oper.nobid.expert.NonbidExpertMajorBean;

public interface MmNonbidExpertMajorMapper {
    int deleteByPrimaryKey(String id);

    int insert(NonbidExpertMajorBean record);

    int insertSelective(NonbidExpertMajorBean record);

    NonbidExpertMajorBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NonbidExpertMajorBean record);

    int updateByPrimaryKey(NonbidExpertMajorBean record);
}