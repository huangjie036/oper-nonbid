package com.yyc.oper.nobid.service.mat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.mat.MatplanAndMatResponse;
import com.yyc.oper.nobid.mat.MatplanRequest;
import com.yyc.oper.nobid.mat.MergeRecorRequest;
import com.yyc.oper.nobid.mat.NonMatplanBean;
import com.yyc.oper.nobid.mat.NonMatplanIdRequest;
import com.yyc.oper.nobid.mat.NonMatplanRequest;

public interface INonMatplanService {

	int deleteByPrimaryKey(String matplanId);

    int insertSelective(NonMatplanRequest nonMatplanRequest);

    PageInfo<NonMatplanBean> selectByPrimaryKey(NonMatplanBean record);
    
    PageInfo<NonMatplanBean> getNonMatplanRequest(NonMatplanBean record);

    int updateByPrimaryKeySelective(NonMatplanRequest matplanRequest);
    
    String deleteByMatplanIds(NonMatplanIdRequest matplanIds);
    
    String matplanSubmitByMatplanIds(NonMatplanIdRequest matplanIds);
    
    String matplanExamineByMatplanIds(NonMatplanIdRequest matplanIds);
    
    String matplanconfirmByMatplanIds(NonMatplanIdRequest matplanIds);
    
    String matplanRejectByMatplanIds(NonMatplanIdRequest matplanIds);
    
    int matplanMerge(MergeRecorRequest mergeRecorRequest);
    
    int matplanNoMerge(MergeRecorRequest mergeRecorRequest);
    
    int invitationSupplierByMatplanId(MatplanRequest matplanRequest);
    
    MergeRecorRequest editMergeByMatplanIds(NonMatplanIdRequest matplanIds);
    
    ResultMessage importExcel(List<Map<Integer, Object>> readExcel);
    
    void exportNonMatplan(List<NonMatplanRequest> list, HttpServletRequest request, HttpServletResponse response);

}
