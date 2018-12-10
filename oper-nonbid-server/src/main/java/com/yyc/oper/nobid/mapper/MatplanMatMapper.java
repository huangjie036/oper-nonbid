package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.mat.MatplanMatBean;

@Mapper
public interface MatplanMatMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<MatplanMatBean> list);

    int insertSelective(MatplanMatBean record);

    List<MatplanMatBean> selectByPrimaryKey(MatplanMatBean record);

    int updateByPrimaryKeySelective(MatplanMatBean record);

    int updateByPrimaryKey(MatplanMatBean record);
    
    int deleteByMatplanId(String matplanId);
}