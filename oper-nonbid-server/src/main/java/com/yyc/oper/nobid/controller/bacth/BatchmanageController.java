package com.yyc.oper.nobid.controller.bacth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.batch.BatchmanageBean;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;
import com.yyc.oper.nobid.service.batch.IBatchmanageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 非招标批次管理 nonbid_batchmanage
 * 
 * @author huangjie
 */
@RestController
@RequestMapping(value = "/batchmanage")
@Api(value = "BatchmanageController", tags = "非招标批次管理")
public class BatchmanageController {

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@Autowired
	private IBatchmanageService batchmanageService;

	@Autowired
	private IAuditBuServie auditServie;

	@PostMapping(value = "/deleteByPrimaryKey")
	public int deleteByPrimaryKey(String batchId) {
		return batchmanageService.deleteByPrimaryKey(batchId);
	}

	/**
	 * 新增
	 * 
	 * @param record
	 *            非招标批次管理
	 * @return
	 */
	@PostMapping(value = "/insertSelective")
	@ApiOperation(value = "新增非招标批次管理-非招标批次管理", notes = "根据对象新增非招标批次管理-非招标批次管理")
	public ResultMessage insertSelective(@RequestBody BatchmanageBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "新增失败");
		int num = batchmanageService.insertSelective(record);
		if (num > 1) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("新增成功");
		} else {
			resultMessage.setCode(StateDictionary.ERROR);
			String msg = num == -1 ? "结束时间不能小于开始时间" : num == -2 ? "批次编码已存在" : "新增成功";
			// 当返回信息为 "新增成功"时,返回状态码为1 2018-10-19 hlg star
			if ("新增成功".equals(msg)) {
				resultMessage.setCode(StateDictionary.OK);
			}
			// 当返回信息为 "新增成功"时,返回状态码为1 2018-10-19 hlg end
			resultMessage.setMsg(msg);
		}
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/batchmanage/insertSelective");
		auditRes.put("method", "Post");
		auditRes.put("parameter", record.getBatchId());
		auditRes.put("result", true);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "增加");
		auditRes.put("auditFunctionName", "新增批次");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "新增批次，编号" + record.getBatchId() + ",名称" + record.getBatchName());

		auditServie.insertAudit(auditRes);
		return resultMessage;
	}

	/**
	 * 分页查询
	 * 
	 * @param record
	 *            非招标批次管理
	 * @return
	 */
	@PostMapping(value = "/selectByPrimaryKey")
	@ApiOperation(value = "分页查询非招标批次管理-非招标批次管理", notes = "根据对象分页查询非招标批次管理-非招标批次管理")
	public ResultMessage selectByPrimaryKey(@RequestBody BatchmanageBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "查询失败");
		PageInfo<BatchmanageBean> pageInfo = batchmanageService.selectByPrimaryKey(record);
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
	 * @param record
	 *            非招标批次管理
	 * @return
	 */
	@PostMapping(value = "/updateByPrimaryKeySelective")
	@ApiOperation(value = "修改/删除非招标批次管理-非招标批次管理", notes = "根据对象修改/删除非招标批次管理-非招标批次管理")
	public ResultMessage updateByPrimaryKeySelective(@RequestBody BatchmanageBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "更新失败");
		int num = batchmanageService.updateByPrimaryKeySelective(record);
		if (num == 1) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("更新成功");
		}
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/batchmanage/updateByPrimaryKeySelective");
		auditRes.put("method", "Post");
		auditRes.put("parameter", record.getBatchId());
		auditRes.put("result", num == 1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "修改");
		auditRes.put("auditFunctionName", "修改批次");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "修改批次，编号" + record.getBatchId() + ",名称" + record.getBatchName());

		auditServie.insertAudit(auditRes);
		return resultMessage;
	}

	/**
	 * 批量修改状态
	 * 
	 * @param record
	 *            非招标批次管理
	 * @return
	 */
	@PostMapping(value = "/updateByPrimaryKey")
	@ApiOperation(value = "批量修改状态非招标批次管理-非招标批次管理", notes = "根据对象批量修改状态非招标批次管理-非招标批次管理")
	public ResultMessage updateByPrimaryKey(@RequestBody BatchmanageBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "批量更新失败");
		int num = batchmanageService.updateByPrimaryKey(record);
		if (num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("批量更新成功");
		}
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/batchmanage/updateByPrimaryKey");
		auditRes.put("method", "Post");
		auditRes.put("parameter", record.getBatchId());
		auditRes.put("result", num > 0 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "修改");

		String ids = "";
		if (record != null) {
			ids = record.getBatchIds()[0];
		}
		Map<String, String> map = auditServie.getBatchById(ids);
		if (record.getState().equals("0")) {
			auditRes.put("auditFunctionName", "开启批次状态");
			auditRes.put("remarkdes", "开启批次，编号" + map.get("batchNum") + ",名称" + map.get("batchName"));
		} else {
			auditRes.put("auditFunctionName", "关闭批次状态");

			auditRes.put("remarkdes", "关闭批次，编号" + map.get("batchNum") + ",名称" + map.get("batchName"));
		}
		auditRes.put("tag", "正常");

		auditServie.insertAudit(auditRes);
		return resultMessage;
	}

	/**
	 * 批量删除
	 * 
	 * @param record
	 *            非招标批次管理
	 * @return
	 */
	@PostMapping(value = "/updateByBatchIds")
	@ApiOperation(value = "批量删除非招标批次管理-非招标批次管理", notes = "根据对象批量删除非招标批次管理-非招标批次管理")
	public ResultMessage updateByBatchIds(@RequestBody String[] batchIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "批量删除失败");
		int num = batchmanageService.updateByBatchIds(batchIds);
		if (num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("批量删除成功");
		}
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/batchmanage/updateByBatchIds");
		auditRes.put("method", "Post");
		auditRes.put("parameter", JSON.toJSONString(batchIds).toString());
		auditRes.put("result", num > 0 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "删除");
		auditRes.put("auditFunctionName", "删除批次");
		auditRes.put("tag", "正常");

		String ids = "";
		if (batchIds.length > 0) {
			ids = batchIds[0];
		}
		Map<String, String> map = auditServie.getBatchById(ids);
		auditRes.put("remarkdes", "删除批次，编号" + map.get("batchNum") + ",名称" + map.get("batchName"));
		auditServie.insertAudit(auditRes);
		return resultMessage;
	}

	/**
	 * 查询所有已开启批次
	 * 
	 * @param record
	 *            非招标批次管理
	 * @return
	 */
	@PostMapping(value = "/findListByState")
	@ApiOperation(value = "查询所有已开启批次-非招标批次管理", notes = "查询所有已开启批次-非招标批次管理")
	public ResultMessage findListByState(BatchmanageBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "查询失败");
		record.setState("1");// 1开启 0关闭
		List<BatchmanageBean> list = batchmanageService.findListByState(record);
		if (list.size() > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		} else {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
		}
		resultMessage.setData(list);
		return resultMessage;
	}
}
