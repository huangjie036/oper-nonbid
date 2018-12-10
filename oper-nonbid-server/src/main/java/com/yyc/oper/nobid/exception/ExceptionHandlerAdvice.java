package com.yyc.oper.nobid.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;

@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {
	@Autowired
	private IAuditBuServie auditServie;

	@ExceptionHandler(Exception.class)
	public ResultMessage handleException(Exception e) {
		e.printStackTrace();

		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/");
		auditRes.put("method", "Post");
		// auditRes.put("parameter",
		// JSON.toJSONString(examEvalBean).toString());
		auditRes.put("result", false);
		auditRes.put("auditLevel", 2);
		auditRes.put("auditItemType", "处理");
		auditRes.put("auditFunctionName", "处理异常"); 
		auditRes.put("tag", "异常");
		auditServie.insertAudit(auditRes);

		return new ResultMessage(0, "操作异常");
	}

}
