package com.yyc.oper.nobid.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.aostar.audit.domain.AuditFunction;
import com.aostar.audit.domain.AuditItemType;

@Mapper
public interface AuditMapper {

	int insertAudit(Map<String, Object> record);

	AuditFunction getByModuleIdAndPath(Map<String,String> query);
	
	AuditItemType getByAuditSystemIdAndAuditModuleIdAndAuditFunctionId(Map<String,String> query);
	
	
	Map<String,String> getMatPlantById(String ids);
	
	Map<String,String> getBatchById(String ids);
}