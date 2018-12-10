package com.yyc.oper.nobid.service.question;

import java.util.List;
import java.util.Map;

import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.qualification.QuestionLinkBean;

/**
 * Description:问题发生环节   service接口
 * @author hlg
 * @date 2018年9月18日
 */
public interface IQuestionLinkServic {

	/**
	 * 获取所有问题环节
	 * @return
	 */
	List<Map<String, Object>> getQuestionLinks() throws Exception;

	/**
	 * 新增问题环节
	 * @param questionLinkBean
	 * @return 
	 * @throws Exception 
	 */
	void addQuestionlink(QuestionLinkBean questionLinkBean) throws Exception;

	/**
	 * 编辑问题环节
	 * @param questionLinkBean
	 * @return
	 */
	ResultMessage editQuestionlink(QuestionLinkBean questionLinkBean) throws Exception;

	/**
	 * 删除问题环节
	 * @param questionLinkBean
	 * @throws Exception 
	 */
	void deleteQuestionlink(QuestionLinkBean questionLinkBean) throws Exception;

}
