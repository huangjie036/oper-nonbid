package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.expert.ExpertBean;

@Mapper
public interface ExpertMapper {
    int deleteByPrimaryKey(String expertCode);

    int insert(ExpertBean record);

    int insertSelective(ExpertBean record);

    List<ExpertBean> selectByPrimaryKey(ExpertBean record);

    int updateByPrimaryKeySelective(ExpertBean record);

    int updateByPrimaryKey(ExpertBean record);
    
    List<ExpertBean> findExpertByRand(ExpertBean record);
}