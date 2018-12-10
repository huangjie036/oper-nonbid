package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.mat.MatBean;

@Mapper
public interface MatMapper {
    int deleteByPrimaryKey(String matId);

    int insert(MatBean record);

    int insertSelective(MatBean record);

    List<MatBean> selectByPrimaryKey(MatBean record);

    int updateByPrimaryKeySelective(MatBean record);

    int updateByPrimaryKey(MatBean record);

	int insertMatlist(List<MatBean> list);
}