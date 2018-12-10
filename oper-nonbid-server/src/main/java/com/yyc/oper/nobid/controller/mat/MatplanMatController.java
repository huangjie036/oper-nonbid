package com.yyc.oper.nobid.controller.mat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.mat.MatplanMatBean;
import com.yyc.oper.nobid.service.mat.IMatplanMatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 非招标-物资计划的物料表  nonbid_matplan_mat
 * @author huangjie
 * */
@RestController
@RequestMapping(value="/matplanMat")
@Api(value = "MatplanMatController", tags = "非招标-物资计划的物料表 ")
public class MatplanMatController {
	
	@Autowired
	private IMatplanMatService matplanMatService;
	
	@PostMapping(value="/deleteByPrimaryKey")
	public int deleteByPrimaryKey(String id) {
		return matplanMatService.deleteByPrimaryKey(id);
	}
	
	/**
	 * 新增
	 * @param record 非招标-物资计划的物料表
	 * @return
	 * */
	@PostMapping(value="/insertSelective")
	@ApiOperation(value = "新增非招标-物资计划的物料表", notes = "根据对象，新增非招标-物资计划的物料表")
	public ResultMessage insertSelective(@RequestBody MatplanMatBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"新增失败");
		int num = matplanMatService.insertSelective(record);
		if(num == 1) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("新增成功");
		}
		return resultMessage;
	}
	
	/**
	 * 分页查询
	 * @param record 非招标-物资计划的物料表
	 * @return
	 * */
	@PostMapping(value="/selectByPrimaryKey")
	@ApiOperation(value = "分页查询非招标-物资计划的物料表", notes = "根据条件分页查询非招标-物资计划的物料表")
	public ResultMessage selectByPrimaryKey(@RequestBody MatplanMatBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		PageInfo<MatplanMatBean> pageInfo = matplanMatService.selectByPrimaryKey(record);
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
	 * @param record 非招标-物资计划的物资表
	 * @return
	 * */
	@PostMapping(value="/updateByPrimaryKeySelective")
	public ResultMessage updateByPrimaryKeySelective(@RequestBody MatplanMatBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"更新失败");
		int num = matplanMatService.updateByPrimaryKeySelective(record);
		if(num == 1) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("更新成功");
		}
		return resultMessage;
	}
}
