package com.yyc.oper.nobid.controller.supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.service.supplier.ISupplierWinService;
import com.yyc.oper.nobid.supplier.SupplierResponse;
import com.yyc.oper.nobid.supplier.SupplierWinBean;
import com.yyc.oper.nobid.supplier.SupplierWinDetailBean;
import com.yyc.oper.nobid.supplier.SupplierWinRequest;
import com.yyc.oper.nobid.supplier.SupplierWinResponse;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 非招标中标供应商表  nonbid_supplier_win
 * @author huangjie
 * */
@RestController
@RequestMapping(value="/supplierWin")
@Api(value = "SupplierWinController", tags = "非招标中标供应商表  ")
public class SupplierWinController {
	
	@Autowired
	private ISupplierWinService supplierWinService;
	@Autowired
	private IAuditBuServie auditServie;
	
	@PostMapping(value="/deleteByPrimaryKey")
	public int deleteByPrimaryKey(String supplierWinId) {
		return supplierWinService.deleteByPrimaryKey(supplierWinId);
	}
	
	/**
	 * 新增
	 * @param record 非招标中标供应商表
	 * @return
	 * */
	@PostMapping(value="/insertSelective")
	@ApiOperation(value = "新增非招标中标供应商表-非招标中标供应商表", notes = "根据对象，新增非招标中标供应商表-非招标中标供应商表")
	public ResultMessage insertSelective(@RequestBody SupplierWinBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"新增失败");
		int num = supplierWinService.insertSelective(record);
		if(num == 1) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("新增成功");
		}
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/supplierWin/insertSelective");
		auditRes.put("method", "Post");
		auditRes.put("parameter", record.getSupplierWinId());
		auditRes.put("result", num == 1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "增加");
		auditRes.put("auditFunctionName", "新增采购结果");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "新增采购结果，编号"+record.getId()+"中标供应商编号"+record.getSupplierWinId());
		auditServie.insertAudit(auditRes);
		return resultMessage;
	}
	
	/**
	 * 分页查询
	 * @param record 非招标中标供应商表
	 * @return
	 * */
	@PostMapping(value="/selectByPrimaryKey")
	@ApiOperation(value = "分页查询非招标中标供应商表-非招标中标供应商表", notes = "根据对象，分页查询非招标中标供应商表-非招标中标供应商表")
	public ResultMessage selectByPrimaryKey(@RequestBody SupplierWinRequest record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		SupplierWinBean supplierWinBean = new SupplierWinBean();
		BeanUtils.copyProperties(record, supplierWinBean);
		supplierWinBean.setMergeState("4");//采购方案状态 4通过
		PageInfo<SupplierWinBean> pageInfo = supplierWinService.selectByPrimaryKey(supplierWinBean);
		PageInfo<SupplierWinResponse> data = new PageInfo<SupplierWinResponse>();
		List<SupplierWinResponse> listSupplierWinResponse = new ArrayList<SupplierWinResponse>();
		List<SupplierWinBean> list = pageInfo.getList();
		for(int i=0;i<list.size();i++) {
			SupplierWinResponse supplierWinResponse = new SupplierWinResponse();
			SupplierWinBean temp = list.get(i);
			BeanUtils.copyProperties(temp, supplierWinResponse);
			if(null != temp.getBatchmanageBean()) {
				supplierWinResponse.setBatchName(temp.getBatchmanageBean().getBatchName());
			}
			if(null != temp.getMergeRecordBean()) {
				supplierWinResponse.setPackageNum(temp.getMergeRecordBean().getPackageNum());
				supplierWinResponse.setPurchaseSchmeId(temp.getMergeRecordBean().getPurchaseSchmeId());
				supplierWinResponse.setPurchaseSchmeName(temp.getMergeRecordBean().getPurchaseSchmeName());
				supplierWinResponse.setPurchaseWay(temp.getMergeRecordBean().getPurchaseWay());
				supplierWinResponse.setMergeId(temp.getMergeRecordBean().getMergeId());
			}
			listSupplierWinResponse.add(supplierWinResponse);
		}
		data.setList(listSupplierWinResponse);
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
	
	/**
	 * 修改
	 * @param record 非招标中标供应商表
	 * @return
	 * */
	@PostMapping(value="/updateByPrimaryKeySelective")
	@ApiOperation(value = "修改非招标中标供应商表-非招标中标供应商表", notes = "根据对象，修改非招标中标供应商表-非招标中标供应商表")
	public ResultMessage updateByPrimaryKeySelective(@RequestBody SupplierWinRequest record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"更新失败");
		SupplierWinBean supplierWinBean = new SupplierWinBean();
		BeanUtils.copyProperties(record, supplierWinBean);
		int num = supplierWinService.updateByPrimaryKeySelective(supplierWinBean);
		if(num == 1) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("更新成功");
		}
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/supplierWin/updateByPrimaryKeySelective");
		auditRes.put("method", "Post");
		auditRes.put("parameter", supplierWinBean.getSupplierWinId());
		auditRes.put("result", num == 1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "修改");
		auditRes.put("auditFunctionName", "修改采购结果");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "修改采购结果，编号"+supplierWinBean.getId()+"中标供应商编号"+supplierWinBean.getSupplierWinId());
		auditServie.insertAudit(auditRes);
		return resultMessage;
	}
	
	/**
	 * 采购结果获取详情
	 * @param record 非招标中标供应商表
	 * @return
	 * */
	@PostMapping(value="/selectPurchaseResultDetail")
	@ApiOperation(value = "采购结果获取详情-非招标中标供应商表", notes = "采购结果获取详情，入参为mergeId（合包id）")
	public ResultMessage selectPurchaseResultDetail(@RequestBody SupplierWinRequest record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		SupplierWinDetailBean supplierWinDetailBean = supplierWinService.selectPurchaseResultDetail(record);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg("查询成功");
		resultMessage.setData(supplierWinDetailBean);
		return resultMessage;
	}
	

	/**
	 * 查询采购方案的邀请供应商和抽取供应商
	 * @param record 合包id
	 * @return
	 * */
	@PostMapping(value="/findSupplierWinByMergeId")
	@ApiOperation(value = "查询采购方案的邀请供应商和抽取供应商", notes = "根据mergeId查询采购方案的邀请供应商和抽取供应商")
	public ResultMessage findSupplierWinByMergeId(@RequestBody SupplierWinBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		List<SupplierResponse> list = supplierWinService.findSupplierWinByMergeId(record);
		if(list.size()>0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
    	}
		resultMessage.setData(list);
		return resultMessage;
	}
}
