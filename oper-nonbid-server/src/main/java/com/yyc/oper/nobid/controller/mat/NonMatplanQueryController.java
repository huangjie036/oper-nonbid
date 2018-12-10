package com.yyc.oper.nobid.controller.mat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.mat.MatplanBean;
import com.yyc.oper.nobid.mat.MatplanEditResponse;
import com.yyc.oper.nobid.mat.MatplanIdRequest;
import com.yyc.oper.nobid.mat.MatplanMatBean;
import com.yyc.oper.nobid.mat.MatplanQueryRequest;
import com.yyc.oper.nobid.mat.MatplanQueryResponse;
import com.yyc.oper.nobid.mat.NonMatplanBean;
import com.yyc.oper.nobid.mat.NonMatplanIdRequest;
import com.yyc.oper.nobid.mat.NonMatplanRequest;
import com.yyc.oper.nobid.merge.MergeRecordId;
import com.yyc.oper.nobid.purchase.PurchaseSchemeResponse;
import com.yyc.oper.nobid.service.mat.IMatplanQueryService;
import com.yyc.oper.nobid.service.mat.IMatplanService;
import com.yyc.oper.nobid.service.mat.INonMatplanService;
import com.yyc.oper.nobid.service.purchase.IPurchaseSchmeService;
import com.yyc.oper.nobid.service.supplier.ISupplierWinService;
import com.yyc.oper.nobid.supplier.InvitationSupplierBean;
import com.yyc.oper.nobid.supplier.InvitationSupplierResponse;
import com.yyc.oper.nobid.supplier.SupplierWinDetailBean;
import com.yyc.oper.nobid.supplier.SupplierWinRequest;
import com.yyc.oper.nobid.util.PageInfoCopyUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 非招标非物资计划表查询 nonbid_non_matplan
 * @author huangjie
 * */
@RestController
@RequestMapping(value="/nonMatplanQuery")
@Api(value = "NonMatplanQueryController", tags = "非招标非物资计划表查询 ")
public class NonMatplanQueryController {
	
	@Autowired
	private IMatplanQueryService matplanQueryService;
	
	@Autowired
	private IMatplanService matplanService;
	
	@Autowired
	private IPurchaseSchmeService purchaseSchmeService;
	
	@Autowired
	private ISupplierWinService supplierWinService;
	
	@Autowired
	private INonMatplanService nonMatplanService;

	
	/**
	 * 分页查询
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/findMatplanByMaintainState")
	@ApiOperation(value = "查询-非招标物资计划表", notes = "根据条件分页查询-非招标物资计划表")
	public ResultMessage selectByMatplanQueryRequest(@RequestBody MatplanQueryRequest record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		PageInfo<MatplanQueryResponse> pageInfo = matplanQueryService.selectByNonMatplanQueryRequest(record);
		if(pageInfo.getList().size()>0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
    	}
		resultMessage.setData(pageInfo);
		return resultMessage;
	}
	
	/**
	 * 查询详情
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/selectEditByMatplanQueryRequest")
	@ApiOperation(value = "查询详情-非招标物资计划表", notes = "根据条件查询详情-非招标物资计划表")
	public ResultMessage selectEditByMatplanQueryRequest(@RequestBody MatplanQueryRequest record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		if(StringUtils.isNotBlank(record.getResultId())){
			//调用采购结果详情
			SupplierWinRequest temp = new SupplierWinRequest();
			temp.setMergeId(record.getMergeId());
			return selectPurchaseResultDetail(temp);
		}else if(StringUtils.isNotBlank(record.getMergeId())){
			//调用采购方案详情
			MergeRecordId temp = new MergeRecordId();
			temp.setMergeId(record.getMergeId());
			return getDetail(temp);
		}else if(StringUtils.isNotBlank(record.getMatplanId())){
			//调用物资采购计划编辑
			MatplanIdRequest temp = new MatplanIdRequest();
			temp.setMatplanId(record.getMatplanId());
			return editMatplanByMatplanId(temp);
		}else if(StringUtils.isNotBlank(record.getNonMatplanId())){
			//调用非物资采购计划编辑
			NonMatplanIdRequest temp = new NonMatplanIdRequest();
			temp.setNonMatplanId(record.getNonMatplanId());
			return editMatplanByNonMatplanId(temp);
		}
		PageInfo<MatplanQueryResponse> pageInfo = matplanQueryService.selectByMatplanQueryRequest(record);
		if(pageInfo.getList().size()>0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
    	}
		resultMessage.setData(pageInfo);
		return resultMessage;
	}
	
	public ResultMessage editMatplanByMatplanId(MatplanIdRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		//record.setState("-1");//表示查询 1待提交，5已驳回
		PageInfo<MatplanBean> pageInfo = matplanService.selectMatplanAndMat(record);
		List<MatplanBean> listMatplanBean=pageInfo.getList();
		List<MatplanEditResponse> listMatplanEditResponse = new ArrayList<MatplanEditResponse>();
		PageInfo<MatplanEditResponse> data = new PageInfo<MatplanEditResponse>(listMatplanEditResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			MatplanEditResponse matplanEditResponse = new MatplanEditResponse();
			MatplanBean matplanBean = listMatplanBean.get(i);
			BeanUtils.copyProperties(matplanBean, matplanEditResponse);
			//供应商集合转换对应字段
			if(matplanBean.getListInvitationSupplierBean().size()>0) {
				List<InvitationSupplierResponse> listInvitationSupplierResponse = new ArrayList<InvitationSupplierResponse>();
				for(int j=0; j<matplanBean.getListInvitationSupplierBean().size(); j++) {
					InvitationSupplierResponse invitationSupplierResponse = new InvitationSupplierResponse();
					InvitationSupplierBean temp = matplanBean.getListInvitationSupplierBean().get(j);
					BeanUtils.copyProperties(temp, invitationSupplierResponse);
					invitationSupplierResponse.setSupplierName(temp.getSupplierName());
					//invitationSupplierResponse.setSupplierPhone(matplanBean.getListSupplierBean().get(j).getPhoneNum());
					listInvitationSupplierResponse.add(invitationSupplierResponse);
					/**ErpSupplierBean erpSupplierBean = new ErpSupplierBean();
					BeanUtils.copyProperties(matplanBean.getListErpSupplierBean().get(j), erpSupplierBean);
					listErpSupplierBean.add(erpSupplierBean);**/
				}
				matplanEditResponse.setListInvitationSupplierResponse(listInvitationSupplierResponse);
			}
			//物料转换
			if(matplanBean.getListMatplanMatBean().size()>0) {
				List<MatplanMatBean> listMatplanMatBean = new ArrayList<MatplanMatBean>();
				for(int j=0; j<matplanBean.getListMatplanMatBean().size(); j++) {
					MatplanMatBean matplanMatBean = new MatplanMatBean();
					BeanUtils.copyProperties(matplanBean.getListMatplanMatBean().get(j), matplanMatBean);
					listMatplanMatBean.add(matplanMatBean);
				}
				matplanEditResponse.setListMatplanMatBean(listMatplanMatBean);
			}
			//获取批次名称
			if(null != matplanBean.getBatchmanageBean()) {
				matplanEditResponse.setBatchName(matplanBean.getBatchmanageBean().getBatchName());
			}
			listMatplanEditResponse.add(matplanEditResponse);
		}
		if(pageInfo.getList().size()>0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
    	}
		resultMessage.setData(data);
		return resultMessage;
	}
	
	public ResultMessage editMatplanByNonMatplanId(NonMatplanIdRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		NonMatplanBean record = new NonMatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		PageInfo<NonMatplanBean> pageInfo = nonMatplanService.getNonMatplanRequest(record);
		List<NonMatplanBean> listMatplanBean = pageInfo.getList();
		List<NonMatplanRequest> listMatplanEditResponse = new ArrayList<NonMatplanRequest>();
		PageInfo<NonMatplanRequest> data = new PageInfo<NonMatplanRequest>(listMatplanEditResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			NonMatplanRequest matplanEditResponse = new NonMatplanRequest();
			NonMatplanBean matplanBean = listMatplanBean.get(i);
			BeanUtils.copyProperties(matplanBean, matplanEditResponse);
			//供应商集合转换对应字段
			if(matplanBean.getListInvitationSupplierBean().size()>0) {
				List<InvitationSupplierResponse> listInvitationSupplierResponse = new ArrayList<InvitationSupplierResponse>();
				for(int j=0; j<matplanBean.getListInvitationSupplierBean().size(); j++) {
					InvitationSupplierResponse invitationSupplierResponse = new InvitationSupplierResponse();
					InvitationSupplierBean temp = matplanBean.getListInvitationSupplierBean().get(j);
					BeanUtils.copyProperties(temp, invitationSupplierResponse);
					invitationSupplierResponse.setSupplierName(temp.getSupplierName());
					listInvitationSupplierResponse.add(invitationSupplierResponse);
				}
				matplanEditResponse.setListInvitationSupplierResponse(listInvitationSupplierResponse);
			}
			//获取批次名称
			if(null != matplanBean.getBatchmanageBean()) {
				matplanEditResponse.setBatchName(matplanBean.getBatchmanageBean().getBatchName());
			}
			listMatplanEditResponse.add(matplanEditResponse);
		}
		if(pageInfo.getList().size()>0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
    	}
		resultMessage.setData(data);
		return resultMessage;
	}
	
	public ResultMessage getDetail(MergeRecordId mergeRecordId) {
		ResultMessage resultMessage = new ResultMessage();
		PurchaseSchemeResponse detail = purchaseSchmeService.getDetail(mergeRecordId.getMergeId());
		if (detail !=null) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		} else {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
		}
		resultMessage.setData(detail);
		return resultMessage;
	}
	
	public ResultMessage selectPurchaseResultDetail(SupplierWinRequest record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		SupplierWinDetailBean supplierWinDetailBean = supplierWinService.selectPurchaseResultDetail(record);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg("查询成功");
		resultMessage.setData(supplierWinDetailBean);
		return resultMessage;
	}
	
}
