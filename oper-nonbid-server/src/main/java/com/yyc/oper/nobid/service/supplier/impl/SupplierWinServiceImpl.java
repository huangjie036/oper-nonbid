package com.yyc.oper.nobid.service.supplier.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.yyc.brace.base.principal.CurrentPrincipalHolder;
import com.yyc.oper.nobid.expert.ExpertBean;
import com.yyc.oper.nobid.mapper.SupplierExtractResultMapper;
import com.yyc.oper.nobid.mapper.SupplierWinMapper;
import com.yyc.oper.nobid.mat.MatplanMatBean;
import com.yyc.oper.nobid.service.supplier.ISupplierWinService;
import com.yyc.oper.nobid.supplier.SupplierBean;
import com.yyc.oper.nobid.supplier.SupplierExtractResultBean;
import com.yyc.oper.nobid.supplier.SupplierResponse;
import com.yyc.oper.nobid.supplier.SupplierWinBean;
import com.yyc.oper.nobid.supplier.SupplierWinDetailBean;
import com.yyc.oper.nobid.supplier.SupplierWinRequest;
import com.yyc.oper.nobid.util.GenerateUUID;
import com.yyc.oper.nobid.util.YycStringUtils;

@Service
public class SupplierWinServiceImpl implements ISupplierWinService{
	
	@Autowired
	private SupplierWinMapper supplierWinMapper;
	
	@Autowired
	private SupplierExtractResultMapper supplierExtractResultMapper;
	
	@Override
	public int deleteByPrimaryKey(String batchId) {
		return supplierWinMapper.deleteByPrimaryKey(batchId);
	}
	
	@Override
	public int insertSelective(SupplierWinBean record) {
		List<SupplierWinBean> listSupplierWinBean = supplierWinMapper.selectByPrimaryKey(record);
		if(null != listSupplierWinBean && listSupplierWinBean.size()>0 && StringUtils.isNotBlank(listSupplierWinBean.get(0).getId())) {
			record.setId(listSupplierWinBean.get(0).getId());
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			return supplierWinMapper.updateByPrimaryKeySelective(record);
		}else {
			record.setId(GenerateUUID.getUUID());
			SupplierExtractResultBean temp = new SupplierExtractResultBean();
//			temp.setMergeId(record.getMergeId());
//			List<SupplierExtractResultBean> list = supplierExtractResultMapper.selectByPrimaryKey(temp);
//			if(null != list && list.size()>0) {
				record.setMergeId(record.getMergeId());
				record.setCreateBy((String)CurrentPrincipalHolder.getAttribute("name"));
				record.setCreateTime(new Date());
				record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
				record.setOpeTime(new Date());
				record.setState("1");//0未维护，1已维护
				return supplierWinMapper.insertSelective(record);
//			}
		}
//		return 0;
	}
	
	@Override
	public PageInfo<SupplierWinBean> selectByPrimaryKey(SupplierWinBean record) {
		
        List<SupplierWinBean> mmNonbidExpertList = supplierWinMapper.selectByPrimaryKey(record);
        PageInfo<SupplierWinBean> result = new PageInfo<SupplierWinBean>(mmNonbidExpertList);
        return result;
	}
	
	/*@Override
	public int updateByPrimaryKeySelective(SupplierWinBean record) {
		return supplierWinMapper.updateByPrimaryKeySelective(record);
	}*/
	/**
	 * 结果维护 
	 * 维护中标结果同时,维护附件表,为附件表添加业务ID
	 * hlg2018-10-18修改
	 */
	@Override
	public int updateByPrimaryKeySelective(SupplierWinBean record) {
		int flag = 1;
		Map<String, Object> businessMap = new HashMap<>();
		businessMap.put("fileId", record.getFileId());
		businessMap.put("businessId", record.getId());
		businessMap.put("businessName", "中标结果维护业务");
		flag = supplierWinMapper.updateFile(businessMap);
		if(flag != 1) {
			return flag;
		}
		flag = supplierWinMapper.updateByPrimaryKeySelective(record);
		return flag;
	}

	@Override
	public SupplierWinDetailBean selectPurchaseResultDetail(SupplierWinRequest record) {
		SupplierWinDetailBean supplierWinDetailBean = supplierWinMapper.selectPurchaseResultDetail(record);
		//获取采购结果的物料信息
		String matIds = supplierWinDetailBean.getMatIds();
		if(matIds!=null){
			String matId[] = matIds.split(",");
			if(YycStringUtils.isNotBlank(matIds)){
				List<MatplanMatBean> matlist =supplierWinMapper.selectMat(matId);
				List<MatplanMatBean> matplanlist = supplierWinMapper.selectMatPlanId(record);
				List<MatplanMatBean> newMatlist = new ArrayList();
				for(MatplanMatBean matplan:matplanlist ){
					String matplanId = matplan.getMatplanId();
					
					for(MatplanMatBean matplanMatBean:matlist ){
						if(matplanMatBean.getMatplanId().equals(matplanId)){
							newMatlist.add(matplanMatBean);
						}
					}
				}
				
				supplierWinDetailBean.getMatlist().addAll(newMatlist);
			}
		}
		
		

		//获取采购结果的供应商信息
		String supplierWinIds = supplierWinDetailBean.getSupplierWinIds();
		String recommendSupplierIds = supplierWinDetailBean.getRecommendSupplierIds();
			if(YycStringUtils.isNotBlank(supplierWinIds)&&recommendSupplierIds==null){
				String supplierWinId[] = supplierWinIds.split(",");
					List<SupplierBean> supplierlist = supplierWinMapper.selectSupplier(supplierWinId);
					supplierWinDetailBean.getSupplierlist().addAll(supplierlist);
			}else if(YycStringUtils.isNotBlank(recommendSupplierIds)&&supplierWinIds==null){
				String recommendSupplierId[] = recommendSupplierIds.split(",");
				List<SupplierBean> supplierlist = supplierWinMapper.selectSupplier(recommendSupplierId);
				supplierWinDetailBean.getSupplierlist().addAll(supplierlist);
			}else if(YycStringUtils.isNotBlank(supplierWinIds)&&YycStringUtils.isNotBlank(recommendSupplierIds)){
				String supplierWinId[] = supplierWinIds.split(",");
				String recommendSupplierId[] = recommendSupplierIds.split(",");
				//合并两个数组
				String[] both = (String[]) ArrayUtils.addAll(supplierWinId, recommendSupplierId);
				List<SupplierBean> supplierlist = supplierWinMapper.selectSupplier(both);
				supplierWinDetailBean.getSupplierlist().addAll(supplierlist);
			}else{
				
			}
			
		
		
		
			
		
		
		
		
		
	
		//获取采购结果的专家信息
		
		String expertIds = supplierWinDetailBean.getExpertWinIds();
		if(expertIds!=null){
			String expertCode[] = expertIds.split(",");
			if(YycStringUtils.isNotBlank(expertIds)){
				List<ExpertBean> expertlist =supplierWinMapper.selectExpert(expertCode);
				supplierWinDetailBean.getExpertlist().addAll(expertlist);
			}
		}
	
		return supplierWinDetailBean;
	}

	@Override
	public List<SupplierResponse> findSupplierWinByMergeId(SupplierWinBean record) {
		List<SupplierResponse> list = supplierWinMapper.selectSupplierByMergeId(record);
		for(int i=0;i<list.size();i++) {
			List<SupplierBean> listSupplierBean = new ArrayList<SupplierBean>();
			SupplierResponse temp = list.get(i);
			//抽取供应商
			if(StringUtils.isNotBlank(temp.getExtractSupplierIds()) && StringUtils.isNotBlank(temp.getExtractSupplierNames())) {
				String[] arrayIdsExtract = temp.getExtractSupplierIds().split(",");
				String[] arrayNamesExtract = temp.getExtractSupplierNames().split(",");
				for(int j=0;j<arrayIdsExtract.length;j++) {
					SupplierBean supplierBean = new SupplierBean();
					supplierBean.setSupplierId(arrayIdsExtract[j]);
					supplierBean.setSupplierName(arrayNamesExtract[j]);
					listSupplierBean.add(supplierBean);
				}
			}
			//邀请供应商
			if(StringUtils.isNotBlank(temp.getInvitationSupplierIds()) && StringUtils.isNotBlank(temp.getInvitationSupplierNames())) {
				String[] arrayIdsInvitation = temp.getInvitationSupplierIds().split(",");
				String[] arrayNamesInvitation = temp.getInvitationSupplierNames().split(",");
				for(int j=0;j<arrayIdsInvitation.length;j++) {
					SupplierBean supplierBean = new SupplierBean();
					supplierBean.setSupplierId(arrayIdsInvitation[j]);
					supplierBean.setSupplierName(arrayNamesInvitation[j]);
					listSupplierBean.add(supplierBean);
				}
			}
			temp.setListSupplierBean(listSupplierBean);
		}
		return list;
	}
}
