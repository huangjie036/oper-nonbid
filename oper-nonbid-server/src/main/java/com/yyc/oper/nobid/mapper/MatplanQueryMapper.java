package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.mat.MatGroupBean;
import com.yyc.oper.nobid.mat.MatplanQueryRequest;
import com.yyc.oper.nobid.mat.MatplanQueryResponse;

@Mapper
public interface MatplanQueryMapper {

    List<MatplanQueryResponse> selectByMatplanQueryRequest(MatplanQueryRequest record);

    List<MatplanQueryResponse> selectByNonMatplanQueryRequest(MatplanQueryRequest record);
    
	List<MatGroupBean> getMatGroupList();
    
}