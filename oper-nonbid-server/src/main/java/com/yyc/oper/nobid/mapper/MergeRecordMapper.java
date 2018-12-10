package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.merge.MergeRecordBean;

@Mapper
public interface MergeRecordMapper {
    int deleteByPrimaryKey(String mergeId);

    int insert(MergeRecordBean record);

    int insertSelective(MergeRecordBean record);

    List<MergeRecordBean> selectByPrimaryKey(MergeRecordBean record);

    int updateByPrimaryKeySelective(MergeRecordBean record);

    int updateByPrimaryKey(MergeRecordBean record);
}