package com.yyc.oper.nobid.controller.expert;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.expert.ExpertExtractRequest;
import com.yyc.oper.nobid.expert.ExpertExtractResultBean;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;
import com.yyc.oper.nobid.service.expert.IExpertExtractResultService;
import com.yyc.oper.nobid.util.YycFileUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 专家抽取结果表 nonbid_expert_extract_result
 * 
 * @author huangjie
 */
@RestController
@RequestMapping(value = "/expertExtractResult")
@Api(value = "ExpertExtractResultController", tags = "专家抽取结果表")
public class ExpertExtractResultController {

	@Autowired
	private IExpertExtractResultService expertExtractResultService;

	@Autowired
	private IAuditBuServie auditServie;
	@PostMapping(value = "/deleteByPrimaryKey")
	public int deleteByPrimaryKey(String extractId) {
		return expertExtractResultService.deleteByPrimaryKey(extractId);
	}

	/**
	 * 随机、手动抽取专家
	 * 
	 * @param record 专家抽取结果表
	 * @return
	 */
	@PostMapping(value = "/insertSelective")
	@ApiOperation(value = "随机、手动抽取专家", notes = "根据请求对象，对物资计划抽取专家")
	public ResultMessage insertSelective(@RequestBody ExpertExtractRequest expertExtractRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "新增失败");
		//ExpertExtractRequest expertExtractRequest = JSON.parseObject(param.toString(), new TypeReference<ExpertExtractRequest>() {});
		ExpertExtractResultBean teacher = new ExpertExtractResultBean();
		BeanUtils.copyProperties(expertExtractRequest, teacher);
		//ExpertExtractRequest
		int num = expertExtractResultService.insertSelective(expertExtractRequest, null);
		if (num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("抽取成功");
		}else if(num == -2) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("记录已抽取，不能重复抽取");
		}else if(num == -1) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("商务专家数量和技术专家数量不能为为空");
		}else if(num == -3) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("商务专家数量和技术专家数量不能都为0");
		}else if(num == -5) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("选取专家数量与商务专家和技术专家数量不相等");
		}else if(num == -8) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("选取商务专家数量大于商务专家数量");
		}else if(num == -9) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("选取技术专家数量大于商务技术数量");
		}else if(num == -10) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("未找到采购方案");
		}else if(num == -11) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("专家编码不在库中或专家为冻结状态");
		}
		return resultMessage;
	}
	
	/**
	 * 导入抽取专家
	 * 
	 * @param record 专家抽取结果表
	 * @return
	 */
	@PostMapping(value = "/importExtractExpert")
	@ApiOperation(value = "导入抽取专家", notes = "根据请求对象，对物资计划抽取专家")
	public ResultMessage importExtractExpert(@RequestParam String param, @RequestParam(value = "fileName", required = false) MultipartFile fileName, HttpServletRequest request, HttpServletResponse response) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "新增失败");
		ExpertExtractRequest expertExtractRequest = JSON.parseObject(param.toString(), new TypeReference<ExpertExtractRequest>() {});
		//ExpertExtractResultBean teacher = new ExpertExtractResultBean();
		//BeanUtils.copyProperties(expertExtractRequest, teacher);
		//ExpertExtractRequest
		int num = expertExtractResultService.insertSelective(expertExtractRequest, fileName);
		if (num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("抽取成功");
		}else if(num == -2) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("记录已抽取，不能重复抽取");
		}else if(num == -1) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("商务专家数量和技术专家数量不能为为空");
		}else if(num == -3) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("商务专家数量和技术专家数量不能都为0");
		}else if(num == -5) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("选取专家数量与商务专家和技术专家数量不相等");
		}else if(num == -8) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("选取商务专家数量大于商务专家数量");
		}else if(num == -9) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("选取技术专家数量大于商务技术数量");
		}else if(num == -10) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("未找到采购方案");
		}else if(num == -11) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("专家编码不在库中或专家为冻结状态");
		}
		// Map<String,Object> auditRes = new HashMap<>();
		// auditRes.put("url", "/expertExtractResult/insertSelective");
		// auditRes.put("method", "Post");
		// auditRes.put("parameter", param.getMergeId());
		// auditRes.put("result", num > 0 ? true : false);
		// auditRes.put("auditLevel", 1);
		// auditRes.put("auditItemType", "处理");
		// auditRes.put("auditFunctionName", "专家抽取");
		// auditRes.put("tag", "正常");
		// auditRes.put("remarkdes",
		// "专家抽取，编号"+record.getMergeId()+",名称"+record.getPurchaseSchmeName());
		//
		// auditServie.insertAudit(auditRes);
		return resultMessage;
	}
	
	/**
	 * 下载模板
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/download")
	@ApiOperation(value = "专家导入抽取下载模板", notes = "专家导入抽取下载模板")
	public ResultMessage download(HttpServletResponse response, HttpServletRequest request) throws Exception{
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"下载失败");
		String fileName = "expertExtractImportTemplate.xlsx";//模板暂时存放在resources/templates下
		String downloadName = "专家导入抽取模板.xlsx";//下载名请包含后缀
		YycFileUtil.download(downloadName, fileName, response);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg("下载成功");
		return resultMessage;
	}

	/**
	 * 分页查询
	 * 
	 * @param record 专家抽取结果表
	 * @return
	 */
	@PostMapping(value = "/selectByPrimaryKey")
	public ResultMessage selectByPrimaryKey(@RequestBody ExpertExtractRequest record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "查询失败");
		ExpertExtractResultBean teacher = new ExpertExtractResultBean();
		BeanUtils.copyProperties(record, teacher);
		PageInfo<ExpertExtractResultBean> pageInfo = expertExtractResultService.selectByPrimaryKey(teacher);
		if (pageInfo.getList().size() > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		} else {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
		}
		resultMessage.setData(pageInfo);
		return resultMessage;
	}

	/**
	 * 修改
	 * 
	 * @param record 专家抽取结果表
	 * @return
	 */
	@PostMapping(value = "/updateByPrimaryKeySelective")
	public ResultMessage updateByPrimaryKeySelective(@RequestBody ExpertExtractRequest record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "更新失败");
		ExpertExtractResultBean teacher = new ExpertExtractResultBean();
		BeanUtils.copyProperties(record, teacher);
		int num = expertExtractResultService.updateByPrimaryKeySelective(teacher);
		if (num == 1) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("更新成功");
		}
		return resultMessage;
	}
	
	
	/**
	 * 导出专家抽取结果
	 * 
	 * @param extractIds 抽取记录编号 集合
	 * @return
	 */
	@GetMapping(value = "/export")
	public void export(String extractIds, HttpServletRequest request, HttpServletResponse response){
		//利用set去重
		Set<String> set = new HashSet<>();
		String[] split = extractIds.split(",");
		for (String id : split) {
			set.add(id);
		}
		//导出
		expertExtractResultService.exportExpertExtractResult(set, request, response);
	}
}
