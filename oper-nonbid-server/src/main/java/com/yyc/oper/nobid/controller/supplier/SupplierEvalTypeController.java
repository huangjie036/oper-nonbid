package com.yyc.oper.nobid.controller.supplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.service.supplier.ISupplierEvalTypeService;
import com.yyc.oper.nobid.supplier.SupplierEvalTypeBean;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;

/**
 * 评价分类controller
 * Description:   
 * @author hlg
 * @date 2018年9月19日
 */
@RestController
@RequestMapping(value = "/supplierEvalType")
public class SupplierEvalTypeController{

	
	private static final Logger log = LoggerFactory.getLogger(SupplierEvalTypeController.class);

	
	@Autowired
	private ISupplierEvalTypeService typeService;

	@Autowired
	private IAuditBuServie auditServie;
	
	//查询分页
	@PostMapping(value = "/getList")
	public ResultMessage getList(@RequestBody SupplierEvalTypeBean evalTypeBean) throws Exception {
		PageInfo<SupplierEvalTypeBean> pageInfo = null;
		
		
			pageInfo = typeService.getPageInfo(evalTypeBean);
	
		return new ResultMessage(StateDictionary.OK, "成功",pageInfo);
	}
	
	//新增分类
	@PostMapping(value = "/addType")
	public ResultMessage addType(@RequestBody SupplierEvalTypeBean evalTypeBean) {
		try {
			typeService.addType(evalTypeBean);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultMessage(StateDictionary.ERROR, "系统错误:"+e.getMessage());
		}
		ResultMessage result = new ResultMessage(StateDictionary.OK, "添加成功");
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/supplierEvalType/addType");
		auditRes.put("method", "Post");
		auditRes.put("parameter", evalTypeBean.getEvalTypeName());
		auditRes.put("result", result.getCode()==1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "增加");
		auditRes.put("auditFunctionName", "新增分类");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "新增供应商分类，编号"+evalTypeBean.getSupplierEvalTypeId()+"名称"+evalTypeBean.getEvalTypeName());
		auditServie.insertAudit(auditRes);
		return result;
	}
	
	
	//编辑分类
	@PostMapping(value = "/editType")
	public ResultMessage editType(@RequestBody SupplierEvalTypeBean evalTypeBean) {
		
		try {
			typeService.editType(evalTypeBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误:"+e.getMessage());
		}
		ResultMessage result = new ResultMessage(StateDictionary.OK, "编辑成功");
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/supplierEvalType/editType");
		auditRes.put("method", "Post");
		auditRes.put("parameter", evalTypeBean.getEvalTypeName());
		auditRes.put("result", result.getCode()==1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "修改");
		auditRes.put("auditFunctionName", "编辑分类");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "编辑供应商分类，编号"+evalTypeBean.getSupplierEvalTypeId()+"名称"+evalTypeBean.getEvalTypeName());
		
		auditServie.insertAudit(auditRes);
		return result;
	}
	
	/**
	 * 删除评价分类
	 * @param ids 分类ID集合
	 * @return ResultMessage 消息类
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/deleteType")
	public ResultMessage deleteType(@RequestBody Map<String,Object> ids) {
		List<String> list = ids.get("ids") == null ? null :(List<String>)ids.get("ids");
		if(null == list || list.size() == 0 ) {
			return new ResultMessage(StateDictionary.ERROR, "请选择需要删除的数据!");
		}
		try {
			typeService.deleteType(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误:"+e.getMessage());
		}
		ResultMessage result = new ResultMessage(StateDictionary.OK, "删除成功");
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/supplierEvalType/deleteType");
		auditRes.put("method", "Post");
		auditRes.put("parameter", JSON.toJSONString(list).toString());
		auditRes.put("result", result.getCode()==1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "删除");
		auditRes.put("auditFunctionName", "删除评价分类");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "删除评价分类，编号"+JSON.toJSONString(list).toString());
		
		auditServie.insertAudit(auditRes);
		return result;
	}
	/**
	 * 根据环节ID查询评价分类集合
	 * @param questionLinkId
	 * @return List<Map<String, Object>> 评价分类集合
	 */
	@PostMapping(value = "/selectTypeByLinkId")
	public ResultMessage selectTypeByLinkId(@RequestBody SupplierEvalTypeBean evalTypeBean) {
		try {
			List<Map<String, Object>> typeMaps  = typeService.selectTypeByLinkId(evalTypeBean.getQuestionLinkId());
			return new ResultMessage(StateDictionary.OK, "成功",typeMaps);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误:"+e.getMessage());
		}
	}
}
