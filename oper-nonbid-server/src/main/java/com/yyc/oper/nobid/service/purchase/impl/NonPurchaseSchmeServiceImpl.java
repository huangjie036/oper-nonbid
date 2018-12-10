package com.yyc.oper.nobid.service.purchase.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.yyc.brace.base.principal.CurrentPrincipalHolder;
import com.yyc.oper.nobid.expert.ExpertBean;
import com.yyc.oper.nobid.file.FileinfoBean;
import com.yyc.oper.nobid.mapper.ExpertExtractResultMapper;
import com.yyc.oper.nobid.mapper.FileInfoCrudMapper;
import com.yyc.oper.nobid.mapper.InvitationSupplierMapper;
import com.yyc.oper.nobid.mapper.NonMatplanMapper;
import com.yyc.oper.nobid.mapper.NonPurchaseSchmeMapper;
import com.yyc.oper.nobid.mapper.PurchaseSchmeMapper;
import com.yyc.oper.nobid.mapper.SupplierExtractResultMapper;
import com.yyc.oper.nobid.mat.NonMatplanBean;
import com.yyc.oper.nobid.purchase.PurchaseSchemeRequest;
import com.yyc.oper.nobid.purchase.PurchaseSchemeResponse;
import com.yyc.oper.nobid.service.purchase.INonPurchaseSchmeService;
import com.yyc.oper.nobid.supplier.InvitationSupplierBean;
import com.yyc.oper.nobid.supplier.SupplierBean;
import com.yyc.oper.nobid.supplier.SupplierExtractResultBean;

@Service
public class NonPurchaseSchmeServiceImpl implements INonPurchaseSchmeService {

	@Autowired
	private NonPurchaseSchmeMapper nonPurchaseSchmeMapper;
	
	@Autowired
	private FileInfoCrudMapper fileInfoCrudMapper;
	
	@Autowired
	private PurchaseSchmeMapper purchaseSchmeMapper;

	@Autowired
	private SupplierExtractResultMapper supplierExtractResultMapper;
	
	@Autowired
	private NonMatplanMapper nonMatplanMapper;

	@Autowired
	private InvitationSupplierMapper invitationSupplierMapper;

	@Autowired
	private ExpertExtractResultMapper expertExtractResultMapper;

	@Override
	public boolean updateState(List<String> purchaseIdList, String state) {
		try {
			if (CollectionUtils.isEmpty(purchaseIdList)) {
				return false;
			}
			if (StringUtils.isBlank(state)) {
				return false;
			}
			Map<String, Object> map = Maps.newHashMap();
			map.put("idList", purchaseIdList);
			map.put("state", state);
			int updateState = nonPurchaseSchmeMapper.updateState(map);
			if (updateState < 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean deleteMergeState(List<String> mergeIdList) {
		try {
			if (CollectionUtils.isEmpty(mergeIdList)) {
				return false;
			}
			Map<String, Object> map = Maps.newHashMap();
			map.put("idList", mergeIdList);

			int updateState = nonPurchaseSchmeMapper.deleteMergeState(map);
			int updateMatplan = nonPurchaseSchmeMapper.updateMatState(map);
			if (updateState < 0) {
				return false;
			}
			if (updateMatplan < 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public FileinfoBean selectByPrimaryKey(String id) {
		
		return fileInfoCrudMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertFile(List<FileinfoBean> fileinfo) {
		for(FileinfoBean fileinfoBean : fileinfo) {
			fileinfoBean.setCreateBy(CurrentPrincipalHolder.getUid());// 创建人 只有新增才有创建人
			Date currentDate = new Date();
			fileinfoBean.setCreateTime(currentDate);// 创建时间  只有新增才有 
			fileinfoBean.setOpeBy(CurrentPrincipalHolder.getUid());//新增的时候 操作人也是创建人 ，修改或删除其他操作 为操作人。
			fileinfoBean.setOpeTime(currentDate);//新增操作时间 和新增时间一致    修改或者删除 以及其他 操作  时间为操作时间
		}
		return fileInfoCrudMapper.insertFile(fileinfo);
	}

	@Override
	public List<FileinfoBean> selectFileList(String purchaseSchmeId) {
		List<FileinfoBean> filelist = fileInfoCrudMapper.selectFileList(purchaseSchmeId);
		return filelist;
	}

	@Override
	public PurchaseSchemeResponse getDetail(String purchaseId) {
		PurchaseSchemeResponse purchaseSchemeResponse = new PurchaseSchemeResponse();
		PurchaseSchemeRequest purchaseSchemeRequest = new PurchaseSchemeRequest();
		purchaseSchemeRequest.setMergeId(purchaseId);
		List<PurchaseSchemeResponse> list = purchaseSchmeMapper.selectByPurchaseSchemeRequest(purchaseSchemeRequest);
		if(list != null && list.size()>0) {
			PurchaseSchemeResponse temp = list.get(0);
			BeanUtils.copyProperties(temp, purchaseSchemeResponse);
			//抽取供应商
			List<SupplierBean> listSupplierBean = new ArrayList<SupplierBean>();
			SupplierExtractResultBean temp2 = new SupplierExtractResultBean();
			temp2.setMergeId(temp.getMergeId());
			List<SupplierExtractResultBean> list2 = supplierExtractResultMapper.selectSupplierExtractByMergeId(temp2);
			if(null != list2 && list2.size()>0) {
				listSupplierBean.addAll(list2.get(0).getListSupplierBean());
			}
			//推荐邀请供应商
			NonMatplanBean temp1 = new NonMatplanBean();
			temp1.setMergeId(temp.getMergeId());
			List<NonMatplanBean> list3 = nonMatplanMapper.selectByPrimaryKey(temp1);
			String[] matplanIds = new String[list3.size()];
			for(int i=0;i<list3.size();i++) {
				matplanIds[i] = list3.get(i).getNonMatplanId();
			}
			InvitationSupplierBean invitationSupplierBean = new InvitationSupplierBean();
			invitationSupplierBean.setMatplanIds(matplanIds);
			List<InvitationSupplierBean> list4 = invitationSupplierMapper.selectByPrimaryKey(invitationSupplierBean);
			for(int i=0; i<list4.size(); i++) {
				SupplierBean tempSu = list4.get(i).getSupplierBean();
				boolean bool = true;
				for(int j=0;j<listSupplierBean.size();j++) {
					if(tempSu.getSupplierId().equals(listSupplierBean.get(j).getSupplierId())) {
						bool = false;
						break;
					}
				}
				if(bool) {
					listSupplierBean.add(tempSu);
				}
			}
			purchaseSchemeResponse.setExtactedSupplierList(listSupplierBean);
			//抽取专家集合
			List<ExpertBean> expertList = expertExtractResultMapper.selectExpertListByMergerId(purchaseId);
			if(null != expertList && expertList.size()>0) {
				List<ExpertBean> tempList = new ArrayList<>();
				for(int i=0;i<expertList.size();i++) {
					if(expertList.get(i) != null && StringUtils.isNotBlank(expertList.get(i).getExpertCode())) {
						tempList.add(expertList.get(i));
					}
				}
				if(tempList.size()>0) {
					purchaseSchemeResponse.setExtactedExpertBeanList(tempList);
				}
			}
		}
		return purchaseSchemeResponse;
	}


	
	



}
