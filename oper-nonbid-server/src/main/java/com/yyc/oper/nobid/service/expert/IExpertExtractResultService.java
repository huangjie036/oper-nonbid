package com.yyc.oper.nobid.service.expert;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.expert.ExpertExtractRequest;
import com.yyc.oper.nobid.expert.ExpertExtractResultBean;

public interface IExpertExtractResultService {

	int deleteByPrimaryKey(String extractId);

    int insertSelective(ExpertExtractRequest record, MultipartFile importfile);

    PageInfo<ExpertExtractResultBean> selectByPrimaryKey(ExpertExtractResultBean record);

    int updateByPrimaryKeySelective(ExpertExtractResultBean record);
    
    void exportExpertExtractResult(Set<String> set, HttpServletRequest request, HttpServletResponse response);

    List<String> importExpertExtract(List<Map<Integer, Object>> readExcel);
}
