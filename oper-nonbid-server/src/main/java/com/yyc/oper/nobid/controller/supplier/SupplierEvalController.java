package com.yyc.oper.nobid.controller.supplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;
import com.yyc.oper.nobid.service.supplier.ISupplierEvalService;
import com.yyc.oper.nobid.supplier.SupplierEvalBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 非招标供应商评价 nonbid_supplier_eval
 * @author 陈文
 * */
@RestController
@RequestMapping(value="/supplierEval")
@Api(value = "SupplierEvalController", tags = "非招标供应商评价表")
public class SupplierEvalController {
	
	@Autowired
	private ISupplierEvalService supplierEvalService;
	@Autowired
	private IAuditBuServie auditServie;
	
	@PostMapping(value="/deleteByPrimaryKey")
	public int deleteByPrimaryKey(String supplierEvalId) {
		return supplierEvalService.deleteByPrimaryKey(supplierEvalId);
	}
	
	/**
	 * 新增
	 * @param record 非招标供应商评价
	 * @return
	 * */
	@ApiOperation(value = "非招标-供应商评价新增", notes = "非招标-供应商评价新增")
	@PostMapping(value="/insertSupplierEval")
	public ResultMessage insertSelective(@RequestBody SupplierEvalBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"新增失败");
		int num = supplierEvalService.insertSelective(record);
		if(num == 1) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("新增成功");
		}
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/supplierEval/insertSupplierEval");   //接口得url 。必须带   斜杠
		auditRes.put("method", "Post");  //方法得调用方式
		auditRes.put("parameter", record.getSupplierName());//入参 必须为字符串 
		auditRes.put("result", num>=1?true:false);//操作结果.增删改查 sql得返回值。手动成功为0，失败为1。允许手动写true或者false。
		auditRes.put("auditLevel", 1);//日志级别，默认1级 
		auditRes.put("auditItemType", "增加"); 
		auditRes.put("auditFunctionName", "增加供应商评价"); //操作了什么。
		auditRes.put("tag", "正常");// 写在异常处理 机制中为异常  其他时候均为正常
		auditRes.put("remarkdes", "增加供应商评价，编号"+record.getSupplierId()+"名称"+record.getSupplierName());
		auditServie.insertAudit(auditRes);//调用接口
		return resultMessage;
	}
	
	
	/**
	 * 根据供应商查询项目
	 * @param record 非招标供应商评价
	 * @return
	 * */
	@ApiOperation(value = "非招标-根据供应商查询项目", notes = "用于新增里的获取供应商的项目信息 入参：供应商编码supplierId")
	@PostMapping(value="/selectProjectBySupplier")
	public ResultMessage selectProjectBySupplier(@RequestBody SupplierEvalBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		List<SupplierEvalBean> supplierEvalBean = supplierEvalService.selectProjectBySupplier(record);
		if(supplierEvalBean.size()>0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
			resultMessage.setData(supplierEvalBean);
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
    	}
		return resultMessage;
	}
	
	
	/**
	 * 分页查询
	 * @param record 非招标供应商评价
	 * @return
	 * */
	@ApiOperation(value = "非招标-供应商评价列表", notes = "非招标-供应商评价列表")
	@PostMapping(value="/selectSupplierEval")
	public ResultMessage selectByPrimaryKey(@RequestBody SupplierEvalBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		PageInfo<SupplierEvalBean> pageInfo = supplierEvalService.selectByPrimaryKey(record);
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
	 * 修改
	 * @param record 非招标供应商评价
	 * @return
	 * */
	@ApiOperation(value = "修改-供应商评价列表", notes = "修改-供应商评价列表")
	@PostMapping(value="/updateByPrimaryKeySelective")
	public ResultMessage updateByPrimaryKeySelective(@RequestBody SupplierEvalBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"更新失败");
		int num = supplierEvalService.updateByPrimaryKeySelective(record);
		if(num == 1) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("更新成功");
		}
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/supplierEval/updateByPrimaryKeySelective");   //接口得url 。必须带   斜杠
		auditRes.put("method", "Post");  //方法得调用方式
		auditRes.put("parameter", record.getSupplierEvalId());//入参 必须为字符串 
		auditRes.put("result", num>=1?true:false);//操作结果.增删改查 sql得返回值。手动成功为0，失败为1。允许手动写true或者false。
		auditRes.put("auditLevel", 1);//日志级别，默认1级 
		auditRes.put("auditItemType", "修改"); 
		auditRes.put("auditFunctionName", "修改供应商评价"); //操作了什么。
		auditRes.put("tag", "正常");// 写在异常处理 机制中为异常  其他时候均为正常
		auditRes.put("remarkdes", "修改供应商评价，编号"+record.getSupplierId()+"名称"+record.getSupplierName());
		auditServie.insertAudit(auditRes);//调用接口 
		return resultMessage;
	}
	
	/**
	 * 批量删除 （逻辑删除）
	 *  @param supplierEvalIds 供应商评价id数组
	 *  @return
	 * */
	@ApiOperation(value = "批量删除-非招标的供应商评价表", notes = "批量删除-supplierEvalIds[]")
	@PostMapping(value="/delete")
	public ResultMessage updateByPrimaryKey(@RequestBody SupplierEvalBean supplierEvalBean) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"删除失败");
		String [] supplierEvalIds = supplierEvalBean.getSupplierEvalIds();
		supplierEvalService.updateByPrimaryKey(supplierEvalIds);
		 int state = supplierEvalService.updateByPrimaryKey(supplierEvalIds);
			if(state>=1){
				resultMessage.setCode(StateDictionary.OK);
				resultMessage.setMsg("成功删除"+state+"条数据");
			}
			Map<String,Object> record = new HashMap<>();
			record.put("url", "/supplierEval/delete");  
			record.put("method", "Post"); 
			record.put("parameter", JSON.toJSONString(supplierEvalIds).toString());//入参 必须为字符串 
			record.put("result", state>=1?true:false);//操作结果.增删改查 sql得返回值。手动成功为0，失败为1
			record.put("auditLevel", 1);//日志级别，默认1级 
			record.put("auditItemType", "删除"); //新增  ，修改，删除，
			record.put("auditFunctionName", "删除供应商评价"); //操作了什么。
			record.put("tag", "正常");//没有返回异常或者在异常处理中 。都写正常
			record.put("remarkdes", "删除供应商评价，编号"+JSON.toJSONString(supplierEvalIds).toString());
			auditServie.insertAudit(record);
			return resultMessage;
	}
}
