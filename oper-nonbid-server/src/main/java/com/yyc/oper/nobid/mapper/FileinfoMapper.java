package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.file.FileinfoBean;

@Mapper
public interface FileinfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileinfoBean record);

    int insertSelective(FileinfoBean record);

    List<FileinfoBean> selectByPrimaryKey(FileinfoBean record);

    int updateByPrimaryKeySelective(FileinfoBean record);

    int updateByPrimaryKey(FileinfoBean record);
}