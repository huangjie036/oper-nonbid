package com.yyc.oper.nobid.util;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;

public class ValidatorUtils {

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();



	public static <T> String validate(T obj) {
		Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
		List<String> errorMsgList = Lists.newArrayList();
		if (set != null && set.size() > 0) {
			for (ConstraintViolation<T> cv : set) {
				errorMsgList.add(cv.getMessage());
			}
		}
		if (CollectionUtils.isEmpty(errorMsgList)) {
			return null;
		}
		return JSONArray.toJSONString(errorMsgList);
	}

}
