package com.yyc.oper.nobid.service.audit;

import java.util.Map;

public interface IAuditBuServie {

	/**
	 * 插入日志
	 * 
	 * @param record
	 * @return
	 */
	int insertAudit(Map<String, Object> record);

	Map<String,String> getMatPlantById(String ids);
	
	Map<String,String> getBatchById(String ids);
}