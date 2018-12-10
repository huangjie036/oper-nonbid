package com.yyc.oper.nobid.controller.expert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.expert.NonbidExpertMajorBean;
import com.yyc.oper.nobid.service.expert.INonbidExpertMajorService;

/**
 * 非招标主数据专家专业中间表  mm_nonbid_expert_major
 * @author huangjie
 * */
@RestController
@RequestMapping(value="/nonbidExpertMajor")
public class NonbidExpertMajorController {
	
	@Autowired
	private INonbidExpertMajorService nonbidExpertMajorService;
	
	@PostMapping(value="/deleteByPrimaryKey")
	public int deleteByPrimaryKey(String id) {
		return nonbidExpertMajorService.deleteByPrimaryKey(id);
	}
	
	/**
	 * 新增
	 * @param record 非招标主数据专家专业中间表
	 * @return
	 * */
	@PostMapping(value="/insertSelective")
	public ResultMessage insertSelective(@RequestBody NonbidExpertMajorBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"新增失败");
		int num = nonbidExpertMajorService.insertSelective(record);
		if(num == 1) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("新增成功");
		}
		return resultMessage;
	}
	
	/**
	 * 分页查询
	 * @param record 非招标主数据专家专业中间表
	 * @return
	 * */
	@PostMapping(value="/selectByPrimaryKey")
	public ResultMessage selectByPrimaryKey(@RequestBody NonbidExpertMajorBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		PageInfo<NonbidExpertMajorBean> pageInfo = nonbidExpertMajorService.selectByPrimaryKey(record);
		if(pageInfo.getList().size()>0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
    	}
		resultMessage.setData(pageInfo);
		return resultMessage;
	}
	
	/**
	 * 修改
	 * @param record 非招标主数据专家专业中间表
	 * @return
	 * */
	@PostMapping(value="/updateByPrimaryKeySelective")
	public ResultMessage updateByPrimaryKeySelective(@RequestBody NonbidExpertMajorBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"更新失败");
		int num = nonbidExpertMajorService.updateByPrimaryKeySelective(record);
		if(num == 1) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("更新成功");
		}
		return resultMessage;
	}
}
