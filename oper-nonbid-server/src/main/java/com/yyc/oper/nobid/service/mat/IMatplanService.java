package com.yyc.oper.nobid.service.mat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.ArrayMatplan;
import com.yyc.oper.nobid.mat.MatplanAndMatResponse;
import com.yyc.oper.nobid.mat.MatplanBean;
import com.yyc.oper.nobid.mat.MatplanRequest;
import com.yyc.oper.nobid.mat.MergeRecorRequest;

public interface IMatplanService {

	int deleteByPrimaryKey(String matplanId);

    int insertSelective(MatplanRequest matplanRequest);

    PageInfo<MatplanBean> selectByPrimaryKey(MatplanBean record);
    
    PageInfo<MatplanBean> selectMatplanAndMat(MatplanBean record);

    int updateByPrimaryKeySelective(MatplanRequest matplanRequest);
    
    int updateByPrimaryKey(String[] matplanIds, String mergeId, String bagNum, String qualification, String del);
    
    ResultMessage importExcel(List<Map<Integer, Object>> readExcel);
    
    String deleteByMatplanIds(ArrayMatplan matplanIds);
    
    String matplanSubmitByMatplanIds(ArrayMatplan matplanIds);
    
    String matplanExamineByMatplanIds(ArrayMatplan matplanIds);
    
    String matplanconfirmByMatplanIds(ArrayMatplan matplanIds);
    
    String matplanRejectByMatplanIds(ArrayMatplan matplanIds);
    
    int matplanMerge(MergeRecorRequest mergeRecorRequest);
    
    int matplanUnraveling(ArrayMatplan matplanIds);
    
    int matplanNoMerge(MergeRecorRequest mergeRecorRequest);
    
    int updateMergeStateByMatplanId(ArrayMatplan matplanIds);
    
    int invitationSupplierByMatplanId(MatplanRequest matplanRequest);
    
    ResultMessage editMergeByMatplanIds(ArrayMatplan matplanIds);
    
    void exportMatplanAndMat(List<MatplanAndMatResponse> list, HttpServletRequest request, HttpServletResponse response);

}
