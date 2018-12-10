package com.yyc.oper.nobid.exception;

import org.springframework.web.bind.annotation.ResponseBody;

import com.sgcc.uap.rest.exception.handler.RestExceptionResolver;
import com.sgcc.uap.rest.support.WrappedResult;

public class OperHandlerExceptionResolver extends RestExceptionResolver {

	@Override
	@ResponseBody
	public WrappedResult getFailedResult(Exception exception) {
		return WrappedResult.failedWrappedResult("业务处理异常");
	}
}
