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
import com.yyc.oper.nobid.expert.ExpertResponsibilityEvalBean;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;
import com.yyc.oper.nobid.service.expert.IExpertResponsibilityEvalService;
import com.yyc.oper.nobid.util.YycFileUtil;

/**
 * 
 * Description:履责评价controller
 * 
 * @author hlg
 * @date 2018年9月13日
 */
@RestController
@RequestMapping(value = "/responsibility")
public class ExpertResponsibilityEvalController {

	@Autowired
	private IExpertResponsibilityEvalService responsibilityEvalService;
	@Autowired
	private IAuditBuServie auditServie;

	// 履责分页查询
	@PostMapping(value = "/pagelist")
	public ResultMessage getResponsibilitys(@RequestBody ExpertResponsibilityEvalBean responsibilityBean) {
		PageInfo<ExpertResponsibilityEvalBean> pageInfo = new PageInfo<>();
		try {
			pageInfo = responsibilityEvalService.getResponsibilitys(responsibilityBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.OK, "系统错误{}" + e.getMessage());
		}

		return new ResultMessage(StateDictionary.OK, "成功", pageInfo);
	}

	// 新增
	@PostMapping(value = "/addResponsibility")
	public ResultMessage addResponsibility(@RequestBody ExpertResponsibilityEvalBean responsibilityBean) {
		ResultMessage message = new ResultMessage();
		try {
			responsibilityEvalService.addResponsibility(responsibilityBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}" + e.getMessage());
		}
		// 以下字段全部需要根据接口调整，不可遗漏
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/responsibility/addResponsibility"); // 接口得url 。必须带
																	// 斜杠
		auditRes.put("method", "Post"); // 方法得调用方式
		auditRes.put("parameter", responsibilityBean.getName());// 入参 必须为字符串
		auditRes.put("result", message.getCode() >= 1 ? true : false);// 操作结果.增删改查
																		// sql得返回值。手动成功为0，失败为1。允许手动写true或者false。
		auditRes.put("auditLevel", 1);// 日志级别，默认1级
		// 该字段字只能从列出的值进行选择填写 ，不能错//浏览 查询 增加 删除 修改 保存 导入 导出 备份 还原 下载 收集 监视 阻止 通知
		// 处理
		auditRes.put("auditItemType", "增加");
		auditRes.put("auditFunctionName", "增加履责评价"); // 操作了什么。
		auditRes.put("tag", "正常");// 写在异常处理 机制中为异常 其他时候均为正常
		auditRes.put("remarkdes",
				"增加履责评价，编号" + responsibilityBean.getProjectNum() + ",名称" + responsibilityBean.getProjectName());

		auditServie.insertAudit(auditRes);// 调用接口
		return new ResultMessage(StateDictionary.OK, "新增成功");
	}

	// 修改
	@PostMapping(value = "/updateResponsibility")
	public ResultMessage updateResponsibility(@RequestBody ExpertResponsibilityEvalBean responsibilityBean) {
		ResultMessage message = new ResultMessage();
		try {
			responsibilityEvalService.updateResponsibility(responsibilityBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}" + e.getMessage());
		}
		// 以下字段全部需要根据接口调整，不可遗漏
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/responsibility/updateResponsibility"); // 接口得url
																		// 。必须带
																		// 斜杠
		auditRes.put("method", "Post"); // 方法得调用方式
		auditRes.put("parameter", responsibilityBean.getName());// 入参 必须为字符串
		auditRes.put("result", message.getCode() >= 1 ? true : false);// 操作结果.增删改查
																		// sql得返回值。手动成功为0，失败为1。允许手动写true或者false。
		auditRes.put("auditLevel", 1);// 日志级别，默认1级
		// 该字段字只能从列出的值进行选择填写 ，不能错//浏览 查询 增加 删除 修改 保存 导入 导出 备份 还原 下载 收集 监视 阻止 通知
		// 处理
		auditRes.put("auditItemType", "修改");
		auditRes.put("auditFunctionName", "修改履责评价"); // 操作了什么。
		auditRes.put("tag", "正常");// 写在异常处理 机制中为异常 其他时候均为正常
		auditRes.put("remarkdes",
				"修改履责评价，编号" + responsibilityBean.getProjectNum() + ",名称" + responsibilityBean.getProjectName());

		auditServie.insertAudit(auditRes);// 调用接口

		return new ResultMessage(StateDictionary.OK, "修改成功");
	}

	// 删除
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/deleteResponsibility")
	public ResultMessage deleteResponsibility(@RequestBody Map<String, Object> ids) {
		ResultMessage message = new ResultMessage();
		List<String> list = ids.get("ids") == null ? null : (List<String>) ids.get("ids");
		if (null == list || list.size() == 0) {
			return new ResultMessage(StateDictionary.ERROR, "请选择需要删除的数据!");
		}
		try {
			responsibilityEvalService.deleteResponsibility(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}" + e.getMessage());
		}
		Map<String, Object> record = new HashMap<>();
		record.put("url", "/responsibility/deleteResponsibility");
		record.put("method", "Post");
		record.put("parameter", JSON.toJSONString(ids.get("ids")).toString());// 入参
																				// 必须为字符串
		record.put("result", message.getCode() >= 1 ? true : false);// 操作结果.增删改查
																	// sql得返回值。手动成功为0，失败为1
		record.put("auditLevel", 1);// 日志级别，默认1级
		record.put("auditItemType", "删除"); // 新增 ，修改，删除，
		record.put("auditFunctionName", "删除履责评价"); // 操作了什么。
		record.put("tag", "正常");// 没有返回异常或者在异常处理中 。都写正常
		record.put("remarkdes", "删除履责评价，编号" + JSON.toJSONString(ids.get("ids")).toString());

		auditServie.insertAudit(record);

		return new ResultMessage(StateDictionary.OK, "删除成功");
	}

	// 导入
	@PostMapping(value = "/inputResponsibilitys")
	public ResultMessage inputExamEvals(MultipartFile importfile, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 没有标题从第2行开始读.有标题从第三行开始读
			int startRowIndex = 3;
			List<Map<Integer, Object>> readExcel = ExcelUtil.getExcelData(importfile, startRowIndex);
			String msg = responsibilityEvalService.addResponsibilityInput(readExcel);
			return new ResultMessage(StateDictionary.OK, msg);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "导入失败{}" + e.getMessage());
		}
	}

	/**
	 * 下载模板
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/download")
	public ResultMessage download(HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "下载失败");
		String fileName = "responsibilityTemplate.xlsx";// 模板暂时存放在resources/templates下
		String downloadName = "履责评价模板.xlsx";// 下载名请包含后缀
		YycFileUtil.download(downloadName, fileName, response);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg("下载成功");
		return resultMessage;
	}

	/**
	 * 考试评价数据导出
	 * 
	 * @param id
	 *            考试评级ID
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/outResponsibilitys")
	public void export(String id, HttpServletRequest request, HttpServletResponse response) {
		// 利用set去重
		Set<String> set = new HashSet<>();
		String[] split = id.split(",");
		for (String responsibilityId : split) {
			set.add(responsibilityId);
		}
		// 导出
		responsibilityEvalService.exportResponsibility(set, request, response);
	}
}
