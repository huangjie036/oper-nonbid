package com.yyc.oper.nobid.service.question.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyc.brace.base.principal.CurrentPrincipalHolder;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.mapper.QuestionLinkMapper;
import com.yyc.oper.nobid.mapper.SupplierEvalTypeMapper;
import com.yyc.oper.nobid.qualification.QuestionLinkBean;
import com.yyc.oper.nobid.service.question.IQuestionLinkServic;
import com.yyc.oper.nobid.util.GenerateUUID;

/**
 * Description: 问题发生环节 service实现
 * @author hlg
 * @date 2018年9月18日
 */
@Service
public class QuestionLinkServiceImpl implements IQuestionLinkServic {

	@Autowired
	private QuestionLinkMapper questionLinkMapper;
	
	@Autowired
	private SupplierEvalTypeMapper typeMapper;
	
	@Override
	public List<Map<String, Object>> getQuestionLinks() throws Exception {
		return questionLinkMapper.selectQuestionMaps();
	}

	@Override
	public void addQuestionlink(QuestionLinkBean questionLinkBean) throws Exception {
		String uid = CurrentPrincipalHolder.getUid();
		if(null == uid || "".equals(uid)) {
			throw new RuntimeException("未获取操作用户信息,新增失败");
		}
		int count =  questionLinkMapper.selectQuestionByName(questionLinkBean.getQuestionLinkName());
		if(count > 0 ) {
			throw new RuntimeException(questionLinkBean.getQuestionLinkName()+"已存在不允许添加为同名环节");
		}
		questionLinkBean.setQuestionLinkId(GenerateUUID.getUUID());
		questionLinkBean.setCreateBy(CurrentPrincipalHolder.getUid());
		questionLinkBean.setOpeBy(CurrentPrincipalHolder.getUid());
		questionLinkBean.setOpeTime(new Date());
		questionLinkMapper.insert(questionLinkBean);
	}

	@Override
	public ResultMessage editQuestionlink(QuestionLinkBean questionLinkBean) throws Exception {
		String uid = CurrentPrincipalHolder.getUid();
		if(null == uid || "".equals(uid)) {
			return new ResultMessage(StateDictionary.ERROR, "未获取操作用户信息,编辑失败");
		}
		int count =  questionLinkMapper.selectQuestionByName(questionLinkBean.getQuestionLinkName());
		if(count > 0 ) {
			return new ResultMessage(StateDictionary.ERROR, questionLinkBean.getQuestionLinkName()+"已存在不允许修改为同名环节");
		}
		questionLinkBean.setOpeBy(CurrentPrincipalHolder.getUid());
		questionLinkBean.setOpeTime(new Date());
		questionLinkMapper.updateByPrimaryKeySelective(questionLinkBean);
		return new ResultMessage(StateDictionary.OK, "修改成功");
	}

	@Override
	public void deleteQuestionlink(QuestionLinkBean questionLinkBean) throws Exception {
		int count  = typeMapper.getTypeByLinkId(questionLinkBean.getQuestionLinkId());
		if(count > 0 ) {
			throw new RuntimeException("环节下存在有效的评价分类不允许删除!");
		}
		questionLinkMapper.deleteQuestion(questionLinkBean.getQuestionLinkId());
	}
	
}
