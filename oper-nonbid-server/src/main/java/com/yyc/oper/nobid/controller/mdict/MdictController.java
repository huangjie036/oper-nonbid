package com.yyc.oper.nobid.controller.mdict;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.mdict.MdictBean;
import com.yyc.oper.nobid.service.mdict.IMdictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * Description:数据字典维护 controller
 * 
 * @author hlg
 * @date 2018年9月26日
 */
@RestController
@RequestMapping(value = "/mdict")
@Api(tags = "数据字典维护接口")/*swagger文档资源:tags:接口描述*/
@SuppressWarnings({"rawtypes" })
public class MdictController {

	private static final Logger log = LoggerFactory.getLogger(MdictController.class);

	@Autowired
	private IMdictService mdictService;

	/**
	 * 获取分页字典数据
	 * 
	 * @param mdictBean
	 * @return
	 */
	@ApiOperation(
			value = "获取分页字典数据",/*方法说明*/
			notes = "此方法返回为分页对象,由于分页对象无法添加swagger注解,导致返回数据null")/*备注信息*/
	@PostMapping(value = "/getMdicts")
	public ResultMessage getMdicts(
			@ApiParam(
					value = "数据字典bean", /*参数描述*/ 
					name = "mdictBean", /*参数名称*/
					required= false/*参数是否必传*/
			) @RequestBody MdictBean mdictBean) {
		ResultMessage message = new ResultMessage();
		PageInfo<MdictBean> pageInfo = new PageInfo<>();
		try {
			log.info("获取分页字典数据");
			pageInfo = mdictService.getMdicts(mdictBean);
			message.setCode(StateDictionary.OK);
			message.setData(pageInfo);
			return message;
		} catch (Exception e) {
			message.setCode(StateDictionary.ERROR);
			message.setMsg("系统错误:" + e.getMessage());
			e.printStackTrace();
			return message;
		}

	}


	/**
	 * 新增字典数据
	 * 
	 * @param mdictBean
	 * @return
	 */
	@PostMapping(value = "/addMdict")
	@ApiOperation(value = "新增字典数据",notes = "备注")/*方法描述*/
	public ResultMessage addMdict(@ApiParam(
	value = "数据字典bean",/*参数描述*/
	required = true/*是否必填*/)
	@RequestBody MdictBean mdictBean) {
		ResultMessage message = new ResultMessage();
		try {
			mdictService.addMdict(mdictBean);
			message.setCode(StateDictionary.OK);
			message.setMsg("添加成功");
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			message.setCode(StateDictionary.OK);
			message.setMsg("系统错误:" + e.getMessage());
			return message;
		}
	}

	/**
	 * 删除字典数据
	 * 
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "删除字典数据")
	@PostMapping(value = "/deleteMdict")
	public ResultMessage deleteMdict(
			@ApiParam(value = "字典数据ID编号集合[\"123\",\"234\"]") @RequestBody List<String> list) {
		ResultMessage message = new ResultMessage();
		if (null == list || list.size() == 0) {
			message.setCode(StateDictionary.ERROR);
			message.setMsg("请选择需要删除的数据!");
			return message;
		}

		try {
			mdictService.deleteMdict(list);
			message.setCode(StateDictionary.OK);
			message.setMsg("删除成功");
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			message.setCode(StateDictionary.ERROR);
			message.setMsg("系统错误:" + e.getMessage());
			return message;
		}
	}

	/**
	 * 更新字典数据
	 * 
	 * @param mdictBean
	 * @return
	 */
	@ApiOperation(value = "更新字典数据")
	@PostMapping(value = "/updateMdict")
	public ResultMessage updateMaict(@ApiParam(value = "需要更新的字典数据bean") @RequestBody MdictBean mdictBean) {
		ResultMessage message = new ResultMessage();
		try {
			mdictService.updateMaict(mdictBean);
			message.setCode(StateDictionary.OK);
			message.setMsg("修改成功");
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			message.setCode(StateDictionary.ERROR);
			message.setMsg("系统错误:" + e.getMessage());
			return message;
		}
	}

	/**
	 * 根据keyword查询字典数据
	 * 
	 * @param keyword
	 * @return
	 */
	@ApiOperation(value = "根据keyword查询字典数据:[{id,key,value,type,sort}]")
	@PostMapping(value = "/findMdicstByType")
	public ResultMessage findMdicstByType(
			@ApiParam(value = "字典类型", required = true) @RequestBody String keyword) {
		ResultMessage message = new ResultMessage();
		try {
			List<Map<String, Object>> mdictMaps = mdictService.findMdictsByType(keyword);
			message.setCode(StateDictionary.OK);
			message.setData(mdictMaps);
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			message.setCode(StateDictionary.ERROR);
			message.setMsg("系统错误:" + e.getMessage());
			return message;
		}
	}

}
