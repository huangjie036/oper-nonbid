package com.yyc.oper.nobid.controller.expert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.brace.office.util.ExcelUtil;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.expert.ExpertSumEvalBean;
import com.yyc.oper.nobid.service.expert.IExpertSumEvalService;
import com.yyc.oper.nobid.util.YycFileUtil;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;

/**
 * Description:年度评价controller   
 * @author hlg
 * @date 2018年9月13日
 */
@RestController
@RequestMapping(value = "/sumEval")
public class ExpertSumEvalController {

	@Autowired
	private IExpertSumEvalService sumEvalService;

	@Autowired
	private IAuditBuServie auditServie;
	
	
	//分页查询
	@PostMapping(value = "/getSumEvals")
	public ResultMessage getSumEvals(@RequestBody ExpertSumEvalBean sumEvalBean) {
		PageInfo<ExpertSumEvalBean> pageInfo = new PageInfo<>();
		try {
			pageInfo =  sumEvalService.getExpertSumEvals(sumEvalBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.OK, "系统错误{}"+e.getMessage());
		}
		
		return new ResultMessage(StateDictionary.OK, "成功",pageInfo);
	}
	//新增
	@PostMapping(value = "/addSumEval")
	public ResultMessage addSumEval(@RequestBody ExpertSumEvalBean sumEvalBean) {
		try {
			sumEvalService.addExpertSumEval(sumEvalBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}"+e.getMessage());
		}
		ResultMessage result = new ResultMessage(StateDictionary.OK, "新增成功");
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/sumEval/addSumEval");
		auditRes.put("method", "Post");
		auditRes.put("parameter", sumEvalBean.getName());
		auditRes.put("result", result.getCode()==1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "增加");
		auditRes.put("auditFunctionName", "新增专家年度评价");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "新增专家年度评价，编号"+sumEvalBean.getId()+",名称"+sumEvalBean.getName());
		
		auditServie.insertAudit(auditRes);
		return result;
	}
	
	//修改
	@PostMapping(value = "/updateSumEval")
	public ResultMessage updateSumEval(@RequestBody ExpertSumEvalBean sumEvalBean) {
		try {
			sumEvalService.updateExpertSumEval(sumEvalBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}"+e.getMessage());
		}
		
		ResultMessage result = new ResultMessage(StateDictionary.OK, "修改成功");
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/sumEval/updateSumEval");
		auditRes.put("method", "Post");
		auditRes.put("parameter", sumEvalBean.getName());
		auditRes.put("result", result.getCode()==1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "修改");
		auditRes.put("auditFunctionName", "修改专家年度评价");
		auditRes.put("tag", "正常");
		
		auditRes.put("remarkdes", "修改专家年度评价，编号"+sumEvalBean.getId()+",名称"+sumEvalBean.getName());
		
		auditServie.insertAudit(auditRes);
		return result;
	}
	//删除
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/deleteSumEval")
	public ResultMessage deleteSumEvals(@RequestBody Map<String,Object> ids) {
		List<String> list = ids.get("ids") == null ? null :(List<String>)ids.get("ids");
		if(null == list || list.size() == 0 ) {
			return new ResultMessage(StateDictionary.ERROR, "请选择需要删除的数据!");
		}
		try {
			sumEvalService.deleteExpertSumEval(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}"+e.getMessage());
		}
		
		ResultMessage result = new ResultMessage(StateDictionary.OK, "删除成功");
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/sumEval/deleteSumEval");
		auditRes.put("method", "Post");
		auditRes.put("parameter", JSON.toJSONString(list).toString());
		auditRes.put("result", result.getCode()==1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "删除");
		auditRes.put("auditFunctionName", "删除专家年度评价");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "删除专家年度评价，编号"+JSON.toJSONString(list).toString());
		
		auditServie.insertAudit(auditRes);
		return result;
	}
	
	//导入
	@PostMapping(value = "/inputSumEvals")
	public ResultMessage inputSumEvals(MultipartFile importfile, HttpServletRequest request, 
			HttpServletResponse response) {
		try{
			//没有标题从第二行还是读
			int startRowIndex = 3;
	        List<Map<Integer, Object>> readExcel = ExcelUtil.getExcelData(importfile, startRowIndex);
	       String msg =  sumEvalService.addResponsibilityInput(readExcel);
	       	List<String> list = new ArrayList<>();
	       		for(Map map :readExcel){
	       			list.add(map.get("1").toString());
	       		}
	       ResultMessage result = new ResultMessage(StateDictionary.OK, msg);
			Map<String,Object> auditRes = new HashMap<>();
			auditRes.put("url", "/sumEval/inputSumEvals");
			auditRes.put("method", "Post");
			auditRes.put("parameter", JSON.toJSONString(list).toString());
			auditRes.put("result", result.getCode()==1 ? true : false);
			auditRes.put("auditLevel", 1);
			auditRes.put("auditItemType", "导入");
			auditRes.put("auditFunctionName", "导入专家年度评价");
			auditRes.put("tag", "正常");
			auditRes.put("remarkdes", "导入专家年度评价，编号"+JSON.toJSONString(list).toString());
			
			auditServie.insertAudit(auditRes);
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "导入失败{}"+e.getMessage());
		}
			
	}
	
	/**
	 * 下载模板
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/download")
	public ResultMessage download(HttpServletResponse response, HttpServletRequest request) throws Exception{
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"下载失败");
		String fileName = "sunmEvalTemplate.xlsx";//模板暂时存放在resources/templates下
		String downloadName = "年度评价模板.xlsx";//下载名请包含后缀
		YycFileUtil.download(downloadName, fileName, response);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg("下载成功");
		return resultMessage;
	}
	
	/**
	 * 年度评价数据导出
	 * @param id 考试评级ID
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/outSunmEvals")
	public void export(String id, HttpServletRequest request, HttpServletResponse response){
		//利用set去重
		Set<String> set = new HashSet<>();
		String[] split = id.split(",");
		for (String sunmEvalId : split) {
			set.add(sunmEvalId);
		}
		//导出
		sumEvalService.exportSumEval(set, request, response);
	}
}
