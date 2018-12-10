package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.expert.NonbidExpertMajorBean;

@Mapper
public interface NonbidExpertMajorMapper {
    int deleteByPrimaryKey(String id);

    int insert(NonbidExpertMajorBean record);

    int insertSelective(NonbidExpertMajorBean record);

    List<NonbidExpertMajorBean> selectByPrimaryKey(NonbidExpertMajorBean record);

    int updateByPrimaryKeySelective(NonbidExpertMajorBean record);

    int updateByPrimaryKey(NonbidExpertMajorBean record);
}