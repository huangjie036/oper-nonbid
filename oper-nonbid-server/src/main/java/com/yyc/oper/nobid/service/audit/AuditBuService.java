package com.yyc.oper.nobid.service.audit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aostar.audit.domain.AuditFunction;
import com.aostar.audit.domain.AuditItemType;
import com.yyc.brace.base.principal.CurrentPrincipalHolder;
import com.yyc.oper.nobid.mapper.AuditMapper;

/**
 * @author 业务日志
 *
 */
@Service
public class AuditBuService implements IAuditBuServie {

	@Autowired
	private AuditMapper auditMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yyc.oper.common.service.audit.IAuditServie#insertAudit(java.util.Map)
	 */
	@Override
	public int insertAudit(Map<String, Object> record) {
		Map<String, Object> recordMap = new HashMap<>();
		String ip = CurrentPrincipalHolder.getAttribute("ip").toString();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		recordMap.put("id", uuid);
		recordMap.put("addTime", System.currentTimeMillis());
		recordMap.put("beginTime", System.currentTimeMillis());
		recordMap.put("endTime", System.currentTimeMillis());
		recordMap.put("url", record.get("url"));
		recordMap.put("method", record.get("method"));
		recordMap.put("parameter", record.get("parameter"));
		recordMap.put("userName", CurrentPrincipalHolder.getAttribute("name"));
		recordMap.put("ip", ip);
		recordMap.put("referer", record.get("url"));
		recordMap.put("raw", "");
		recordMap.put("custom", "");
		recordMap.put("auditSystemId", "8b8d5af2c3844cf69a7c734d8dda3c45");// 审计系统编号
																			// ，目前写死
		recordMap.put("auditModuleId", "8ced66cbeed742ab85b3ca2870b26e42");// 审计模块编号写死
		Map<String, String> query = new HashMap<>();
		query.put("auditModuleId", "8ced66cbeed742ab85b3ca2870b26e42");
		String path = record.get("url").toString();
		query.put("path", record.get("url").toString());
		// 获取当前url得 功能id
		AuditFunction auditFunction = auditMapper.getByModuleIdAndPath(query);

		recordMap.put("auditFunctionId", auditFunction.getId());
		recordMap.put("auditEvent", 1);
		recordMap.put("result", record.get("result"));// 成功还是失败
		recordMap.put("auditLevel", record.get("auditLevel"));// 日志级别

		Map<String, String> queryItem = new HashMap<>();
		queryItem.put("name", record.get("auditItemType").toString());
		AuditItemType auditItemType = auditMapper.getByAuditSystemIdAndAuditModuleIdAndAuditFunctionId(queryItem);

		recordMap.put("auditItemTypeId", auditItemType.getId());// 日志操作项
																// ，增删改--
		recordMap.put("auditSystemName", "oper-nonbid-server");
		recordMap.put("auditModuleName", "默认模块(oper-nonbid-server)");

		recordMap.put("auditFunctionName", record.get("auditFunctionName"));
		recordMap.put("restService", true);

		StringBuffer remakbuf = new StringBuffer();
		remakbuf.append("对【").append("业务").append("】系统中").append("业务数据").append(")模块的【")
				.append(record.get("auditFunctionName")).append("】功能进行了【").append(record.get("remarkdes")).append("】,结果为【")
				.append(((Boolean) record.get("result")) ? "成功" : "失败").append("】");
		recordMap.put("remark", remakbuf.toString());
		recordMap.put("auditItemTypeName", record.get("auditItemType"));
		recordMap.put("tag", record.get("tag"));

		return auditMapper.insertAudit(recordMap);
	}

	@Override
	public Map<String, String> getMatPlantById(String ids) {
		return auditMapper.getMatPlantById(ids);
	}

	public Map<String, String> getBatchById(String ids) {
		return auditMapper.getBatchById(ids);
	}
}
