package com.yyc.oper.nobid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yyc.oper.nobid.expert.ExpertBean;
import com.yyc.oper.nobid.expert.ExpertExtractResultBean;

@Mapper
public interface ExpertExtractResultMapper {
    int deleteByPrimaryKey(String extractId);

    int insert(ExpertExtractResultBean record);

    int insertSelective(ExpertExtractResultBean record);

    List<ExpertExtractResultBean> selectByPrimaryKey(ExpertExtractResultBean record);

    int updateByPrimaryKeySelective(ExpertExtractResultBean record);

    int updateByPrimaryKey(ExpertExtractResultBean record);
    
    List<Map<String, Object>> getExpertExtractResultList(@Param("extractId")String extractId);
    
    List<ExpertBean> selectExpertListByMergerId(@Param("mergerId")String mergerId);
}