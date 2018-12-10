package com.yyc.oper.nobid.service.mat.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.mapper.MatplanQueryMapper;
import com.yyc.oper.nobid.mat.MatGroupBean;
import com.yyc.oper.nobid.mat.MatplanQueryRequest;
import com.yyc.oper.nobid.mat.MatplanQueryResponse;
import com.yyc.oper.nobid.service.mat.IMatplanQueryService;

@Service
public class MatplanQueryServiceImpl implements IMatplanQueryService{
	
	@Autowired
	private MatplanQueryMapper matplanQueryMapper;

	@Override
	public PageInfo<MatplanQueryResponse> selectByMatplanQueryRequest(MatplanQueryRequest record) {
		//流程状态查询条件
		if(StringUtils.isNotBlank(record.getState())) {
			int state = Integer.parseInt(record.getState());
			if(state>=1 && state<=5) {
				record.setPlanState(record.getState());
			}else if(state>=6 && state<=10) {
				record.setSchemeState(String.valueOf(state-5));
			}else if(state==11) {
				record.setResultState(String.valueOf(state-10));
			}
		}
		//物料分类查询条件
		String[] matGroupIds = record.getMatGroupIds();
		if(matGroupIds!=null && matGroupIds.length>0){
			List<MatGroupBean> matGroupList = matplanQueryMapper.getMatGroupList();
			List<String> resultMatGroupIds = new ArrayList<>();
			resultMatGroupIds.addAll(Arrays.asList(matGroupIds));
			for (String matGroupId : matGroupIds) {
				getReslutMatGroupIds(matGroupList, resultMatGroupIds, matGroupId);
			}
	        String[] newMatGroupIds = new String[resultMatGroupIds.size()]; 
	        resultMatGroupIds.toArray(newMatGroupIds);
	        record.setMatGroupIds(newMatGroupIds);
		}
		List<MatplanQueryResponse> list = matplanQueryMapper.selectByMatplanQueryRequest(record);
		//设置流程状态
		for(MatplanQueryResponse matplanQueryResponse : list) {
			if(StringUtils.isNotBlank(matplanQueryResponse.getResultId())) {
				//采购结果 11已维护
				if("1".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("11");//采购结果已维护
				}
			}else if(StringUtils.isNotBlank(matplanQueryResponse.getMergeId())) {
				//采购方案 6待提交，7待审批，8待确认，9已确认，10已驳回
				if("1".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("6");//采购方案待提交
				}else if("2".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("7");//采购方案待审批
				}else if("3".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("8");//采购方案待确认
				}else if("4".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("9");//采购方案已确认
				}else if("5".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("10");//采购方案已驳回
				}
			}else {
				//采购计划 1待提交，2待审核，3待确认，4已确认，5已驳回
				if("1".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("1");//采购计划待提交
				}else if("2".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("2");//采购计划待审核
				}else if("3".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("3");//采购计划待确认
				}else if("4".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("4");//采购计划已确认
				}else if("5".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("5");//采购计划已驳回
				}
			}
		}
        return new PageInfo<>(list);
	}

	@Override
	public PageInfo<MatplanQueryResponse> selectByNonMatplanQueryRequest(MatplanQueryRequest record) {
		//流程状态查询条件
		if(StringUtils.isNotBlank(record.getState())) {
			int state = Integer.parseInt(record.getState());
			if(state>=1 && state<=5) {
				record.setPlanState(record.getState());
			}else if(state>=6 && state<=10) {
				record.setSchemeState(String.valueOf(state-5));
			}else if(state==11) {
				record.setResultState(String.valueOf(state-10));
			}
		}
		//物料分类查询条件
		String[] matGroupIds = record.getMatGroupIds();
		if(matGroupIds!=null && matGroupIds.length>0){
			List<MatGroupBean> matGroupList = matplanQueryMapper.getMatGroupList();
			List<String> resultMatGroupIds = new ArrayList<>();
			resultMatGroupIds.addAll(Arrays.asList(matGroupIds));
			for (String matGroupId : matGroupIds) {
				getReslutMatGroupIds(matGroupList, resultMatGroupIds, matGroupId);
			}
	        String[] newMatGroupIds = new String[resultMatGroupIds.size()]; 
	        resultMatGroupIds.toArray(newMatGroupIds);
	        record.setMatGroupIds(newMatGroupIds);
		}
		List<MatplanQueryResponse> list = matplanQueryMapper.selectByNonMatplanQueryRequest(record);
		//设置流程状态
		for(MatplanQueryResponse matplanQueryResponse : list) {
			if(StringUtils.isNotBlank(matplanQueryResponse.getResultId())) {
				//采购结果 11已维护
				if("1".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("11");//采购结果已维护
				}
			}else if(StringUtils.isNotBlank(matplanQueryResponse.getMergeId())) {
				//采购方案 6待提交，7待审批，8待确认，9已确认，10已驳回
				if("1".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("6");//采购方案待提交
				}else if("2".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("7");//采购方案待审批
				}else if("3".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("8");//采购方案待确认
				}else if("4".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("9");//采购方案已确认
				}else if("5".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("10");//采购方案已驳回
				}
			}else {
				//采购计划 1待提交，2待审核，3待确认，4已确认，5已驳回
				if("1".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("1");//采购计划待提交
				}else if("2".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("2");//采购计划待审核
				}else if("3".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("3");//采购计划待确认
				}else if("4".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("4");//采购计划已确认
				}else if("5".equals(matplanQueryResponse.getState())) {
					matplanQueryResponse.setState("5");//采购计划已驳回
				}
			}
		}
        return new PageInfo<>(list);
	}
	
	private void getReslutMatGroupIds(List<MatGroupBean> matGroupList, List<String> resultMatGroupIds, String matGroupId) {
		for (MatGroupBean matGroupBean : matGroupList) {
			if (matGroupId.equals(matGroupBean.getParentId())) {
				resultMatGroupIds.add(matGroupBean.getMatGroupId());
				getReslutMatGroupIds(matGroupList, resultMatGroupIds, matGroupBean.getMatGroupId());
			}
		}
	}

}
