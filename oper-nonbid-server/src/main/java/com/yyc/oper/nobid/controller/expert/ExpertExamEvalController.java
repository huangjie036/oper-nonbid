package com.yyc.oper.nobid.controller.expert;

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
import com.yyc.oper.nobid.expert.ExpertExamEvalBean;
import com.yyc.oper.nobid.service.expert.IExpertExamEvalService;
import com.yyc.oper.nobid.util.YycFileUtil;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;

/**
 * Description:专家评价-考试评价   
 * @author hlg
 * @date 2018年9月12日
 */
@RestController
@RequestMapping(value="/examEval")
public class ExpertExamEvalController {
	
	@Autowired
	private IExpertExamEvalService examEvalService;

	@Autowired
	private IAuditBuServie auditServie;
	
	//分页查询
	@PostMapping(value = "/getExamEvals")
	public ResultMessage getExamEvals(@RequestBody ExpertExamEvalBean examEvalBean) {
		PageInfo<ExpertExamEvalBean> pageInfo = new PageInfo<>();
		try {
			pageInfo =  examEvalService.getExamEvals(examEvalBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.OK, "系统错误{}"+e.getMessage());
		}
		
		return new ResultMessage(StateDictionary.OK, "成功",pageInfo);
	}
	//新增
	@PostMapping(value = "/addExamEval")
	public ResultMessage addExamEval(@RequestBody ExpertExamEvalBean examEvalBean) {
		try {
			examEvalService.addexamEval(examEvalBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}"+e.getMessage());
		}
		ResultMessage result = new ResultMessage(StateDictionary.OK, "新增成功");
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/examEval/addExamEval");
		auditRes.put("method", "Post");
		auditRes.put("parameter", examEvalBean.getExpertName());
		auditRes.put("result", result.getCode()==1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "增加");
		auditRes.put("auditFunctionName", "新增专家评价");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "新增专家评价，编号"+examEvalBean.getIdNum()+",名称"+examEvalBean.getExpertName());
		
		auditServie.insertAudit(auditRes);
		return result;
	}
	
	//修改
	@PostMapping(value = "/updateExamEval")
	public ResultMessage updateExamEval(@RequestBody ExpertExamEvalBean examEvalBean) {
		try {
			examEvalService.updateExamEval(examEvalBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}"+e.getMessage());
		}
		
		ResultMessage result = new ResultMessage(StateDictionary.OK, "修改成功");
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/examEval/updateExamEval");
		auditRes.put("method", "Post");
		auditRes.put("parameter", examEvalBean.getExpertName());
		auditRes.put("result", result.getCode()==1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "修改");
		auditRes.put("auditFunctionName", "修改专家评价");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "修改专家评价，编号"+examEvalBean.getIdNum()+",名称"+examEvalBean.getExpertName());
		
		auditServie.insertAudit(auditRes);
		return result;
	}
	//删除
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/deleteExamEval")
	public ResultMessage deleteExamEvals(@RequestBody Map<String,Object> ids) {
		List<String> list = ids.get("ids") == null ? null :(List<String>)ids.get("ids");
		if(null == list || list.size() == 0 ) {
			return new ResultMessage(StateDictionary.ERROR, "请选择需要删除的数据!");
		}
		try {
			examEvalService.deleteExamEval(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}"+e.getMessage());
		}
		
		ResultMessage result = new ResultMessage(StateDictionary.OK, "删除成功");
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/examEval/deleteExamEval");
		auditRes.put("method", "Post");
		auditRes.put("parameter", JSON.toJSONString(list).toString());
		auditRes.put("result", result.getCode()==1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "删除");
		auditRes.put("auditFunctionName", "删除专家评价");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "删除专家评价，编号"+JSON.toJSONString(list).toString());
		
		auditServie.insertAudit(auditRes);
		return result;
	}
	//导入
	@PostMapping(value = "/inputExamEvals")
	public ResultMessage inputExamEvals(MultipartFile importfile, HttpServletRequest request, 
			HttpServletResponse response) {
		try{
			int startRowIndex = 3;//带表头从第三行开始
	        List<Map<Integer, Object>> readExcel = ExcelUtil.getExcelData(importfile, startRowIndex);
	       String msg =  examEvalService.addEvalsInput(readExcel);
	       return new ResultMessage(StateDictionary.OK, msg);
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
		String fileName = "examEvalTemplate.xlsx";//模板暂时存放在resources/templates下
		String downloadName = "考试评价模板.xlsx";//下载名请包含后缀
		YycFileUtil.download(downloadName, fileName, response);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg("下载成功");
		return resultMessage;
	}
	
	/**
	 * 考试评价数据导出
	 * @param id 考试评级ID
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/outExamEvals")
	public void export(String id, HttpServletRequest request, HttpServletResponse response){
		//利用set去重
		Set<String> set = new HashSet<>();
		String[] split = id.split(",");
		for (String examEvalid : split) {
			set.add(examEvalid);
		}
		//导出
		examEvalService.exportExamEval(set, request, response);
	}
}
