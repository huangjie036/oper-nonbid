package com.yyc.oper.nobid.controller.questionlink;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.qualification.QuestionLinkBean;
import com.yyc.oper.nobid.service.question.IQuestionLinkServic;

/**
 * Description:问题发生环节 controller   
 * @author hlg
 * @date 2018年9月18日
 */
@RestController
@RequestMapping(value = "/questionlink")
public class QuestionlinkController {
	
	@Autowired
	private IQuestionLinkServic questionlinkServic;
	
	
	@PostMapping(value = "/getList")
	public ResultMessage getQuestionlinks() {
		List<Map<String, Object>> linkBeans = null;
		try {
			linkBeans =  questionlinkServic.getQuestionLinks();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}"+e.getMessage());
		}
		return  new ResultMessage(StateDictionary.OK, "成功",linkBeans);
	}
	
	@PostMapping(value = "/addQuestionlink")
	public ResultMessage addQuestionlink(@RequestBody QuestionLinkBean questionLinkBean) {
		if(null == questionLinkBean.getQuestionLinkName() || "".equals(questionLinkBean.getQuestionLinkName())) {
			return  new ResultMessage(StateDictionary.ERROR, "环节名称不能为空!");
		}
		try {
			questionlinkServic.addQuestionlink(questionLinkBean);
		}catch (RuntimeException e) {
			return new ResultMessage(StateDictionary.ERROR, e.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}"+e.getMessage());
		}
		
		return  new ResultMessage(StateDictionary.OK, "新增成功");
	}
	
	@PostMapping("/edit")
	public ResultMessage editQuestionlink(@RequestBody QuestionLinkBean questionLinkBean) {
		if(null == questionLinkBean.getQuestionLinkName() || "".equals(questionLinkBean.getQuestionLinkName())) {
			return new ResultMessage(StateDictionary.ERROR, "环节名称不能为空!");
		}
		try {
			return  questionlinkServic.editQuestionlink(questionLinkBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}"+e.getMessage());
		}
	}
	
	@PostMapping("/delete")
	public ResultMessage deleteQuestionlink(@RequestBody QuestionLinkBean questionLinkBean) {
		try {
			  questionlinkServic.deleteQuestionlink(questionLinkBean);
		}catch (RuntimeException e) {
			return new ResultMessage(StateDictionary.ERROR, e.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(StateDictionary.ERROR, "系统错误{}"+e.getMessage());
		}
		return  new ResultMessage(StateDictionary.OK, "删除成功");
	}
}
