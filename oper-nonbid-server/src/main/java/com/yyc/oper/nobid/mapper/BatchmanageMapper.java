package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.batch.BatchmanageBean;

@Mapper
public interface BatchmanageMapper {
    int deleteByPrimaryKey(String batchId);

    int insert(BatchmanageBean record);

    int insertSelective(BatchmanageBean record);

    List<BatchmanageBean> selectByPrimaryKey(BatchmanageBean record);

    int updateByPrimaryKeySelective(BatchmanageBean record);

    int updateByPrimaryKey(BatchmanageBean record);
}