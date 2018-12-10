package com.yyc.oper.nobid.controller.supplier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.brace.office.util.ExcelUtil;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;
import com.yyc.oper.nobid.service.supplier.ISupplierService;
import com.yyc.oper.nobid.supplier.ErpSupplierBean;
import com.yyc.oper.nobid.supplier.SupplierBean;
import com.yyc.oper.nobid.util.YycFileUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 类名:非招标供应商SupplierController<BR>
 * @version 1.0.0
 * author：陈文
 *
 */
@RestController
@RequestMapping(value="/supplier")
@Api(value = "SupplierController", tags = "主数据维护供应商接口")
public class SupplierController {
	
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private IAuditBuServie auditServie;
	
	@PostMapping(value="/deleteByPrimaryKey")
	public int deleteByPrimaryKey(String supplierId) {
		return supplierService.deleteByPrimaryKey(supplierId);
	}
	/**
	 * 
	 * 新增供应商<BR>
	 * 方法名：insertSelective<BR>
	 * @param SupplierBean
	 * @exception <BR>
	 * @since  1.0.0
	 */
	@PostMapping(value="/insertSelective")
	public ResultMessage insertSelective(@RequestBody SupplierBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"新增失败");
		int state= supplierService.insertSelective(record);
		
		if(state==1){
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("新增成功");
		} 
		Map<String,Object> auditRes = new HashMap<>();
		auditRes.put("url", "/supplier/insertSelective");   //接口得url 。必须带   斜杠
		auditRes.put("method", "Post");  //方法得调用方式
		auditRes.put("parameter", record.getSupplierName());//入参 必须为字符串 
		auditRes.put("result", state>=1?true:false);//操作结果.增删改查 sql得返回值。手动成功为0，失败为1。允许手动写true或者false。
		auditRes.put("auditLevel", 1);//日志级别，默认1级 
		auditRes.put("auditItemType", "增加"); 
		auditRes.put("auditFunctionName", "增加供应商"); //操作了什么。
		auditRes.put("tag", "正常");// 写在异常处理 机制中为异常  其他时候均为正常
		auditRes.put("remarkdes", "增加供应商，编号"+record.getSupplierId()+"名称"+record.getSupplierName());
		auditServie.insertAudit(auditRes);//调用接口
		
		 return resultMessage;
	}
	
	/**
	 * 
	 * 新增erp供应商<BR>
	 * 方法名：insertSupplierList<BR>
	 * @param SupplierBean
	 * @exception <BR>
	 * @since  1.0.0
	 */
	@PostMapping(value="/insertSupplierlist")
	public ResultMessage insertSupplierList(@RequestBody SupplierBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"新增失败");
		List<ErpSupplierBean> list = record.getSupplierlist();
		int state= supplierService.insertSupplierList(list);
		
		if(state>=1){
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("新增"+state+"条成功");
		} 
		
		 return resultMessage;
	}
	/**
	 * 分页&条件查询
	 * @param page
	 * @param rows
	 * @param SupplierBean
	 * @return
	 */
	@ApiOperation(value = "条件查询非招标-供应商表", notes = "用作供应商档案维护")
	@PostMapping(value="/selectByPrimaryKey")
	public ResultMessage selectByPrimaryKey(@RequestBody SupplierBean record) {
    	ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
    	PageInfo<SupplierBean> result = supplierService.selectByPrimaryKey(record);
    	if(result.getList().size()!=0){
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
			resultMessage.setData(result);
    	}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
			resultMessage.setData(result);
    	}
    	
    	 return resultMessage;
	}
	
	/**
	 * 条件查询供应商id和名称
	 * @param page
	 * @param rows
	 * @param SupplierBean
	 * @return
	 */
	@ApiOperation(value = "条件查询非招标-供应商表", notes = "查询条件地域数组regionIds[],资质数组qualificationIds[],合作范围数组colatitudeIds[]")
	@PostMapping(value="/selectSupplierInfo")
	public ResultMessage selectSupplierInfo(@RequestBody SupplierBean record) {
    	ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
    	List<SupplierBean> supplierList = supplierService.selectSupplierInfo(record);
    	if(supplierList.size()!=0){
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
			resultMessage.setData(supplierList);
    	}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
			resultMessage.setData(supplierList);
    	}
    	
    	 return resultMessage;
	}
	/**
	 * 
	 * 编辑<BR>
	 * 方法名：updateByPrimaryKeySelective<BR>
	 * @param SupplierBean
	 * @exception <BR>
	 * @since  1.0.0
	 */
	
	@PostMapping(value="/updateByPrimaryKeySelective")
	public ResultMessage updateByPrimaryKeySelective(@RequestBody SupplierBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"修改失败");
		 int state = supplierService.updateByPrimaryKeySelective(record);
			if(state==1){
				resultMessage.setCode(StateDictionary.OK);
				resultMessage.setMsg("修改成功");
			}
			Map<String,Object> auditRes = new HashMap<>();
			auditRes.put("url", "/supplier/updateByPrimaryKeySelective");   //接口得url 。必须带   斜杠
			auditRes.put("method", "Post");  //方法得调用方式
			auditRes.put("parameter", record.getSupplierName());//入参 必须为字符串 
			auditRes.put("result", state>=1?true:false);//操作结果.增删改查 sql得返回值。手动成功为0，失败为1。允许手动写true或者false。
			auditRes.put("auditLevel", 1);//日志级别，默认1级 
			auditRes.put("auditItemType", "修改"); 
			auditRes.put("auditFunctionName", "修改供应商"); //操作了什么。
			auditRes.put("tag", "正常");// 写在异常处理 机制中为异常  其他时候均为正常
			auditRes.put("remarkdes", "修改供应商，编号"+record.getSupplierId()+"名称"+record.getSupplierName());
			auditServie.insertAudit(auditRes);//调用接口 
			return resultMessage;
	}
	/**
	 * 批量删除 （逻辑删除）
	 *  @param supplierIds 供应商数组
	 *  @return
	 * */
	@PostMapping(value="/updateByPrimaryKey")
	public ResultMessage updateByPrimaryKey(@RequestBody String[] supplierIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"删除失败");
		supplierService.updateByPrimaryKey(supplierIds);
		 int state = supplierService.updateByPrimaryKey(supplierIds);
			if(state>0){
				resultMessage.setCode(StateDictionary.OK);
				resultMessage.setMsg("成功删除"+state+"条数据");
			}
				Map<String,Object> record = new HashMap<>();
			record.put("url", "/supplier/updateByPrimaryKey");  
			record.put("method", "Post"); 
			record.put("parameter", JSON.toJSONString(supplierIds).toString());//入参 必须为字符串 
			record.put("result", state>=1?true:false);//操作结果.增删改查 sql得返回值。手动成功为0，失败为1
			record.put("auditLevel", 1);//日志级别，默认1级 
			record.put("auditItemType", "删除"); //新增  ，修改，删除，
			record.put("auditFunctionName", "删除供应商"); //操作了什么。
			record.put("tag", "正常");//没有返回异常或者在异常处理中 。都写正常
			record.put("remarkdes", "删除供应商，编号"+JSON.toJSONString(supplierIds).toString());
			
			auditServie.insertAudit(record);
			return resultMessage;
	}
	
	/**
	 * 供应商审核 
	 *  @param supplierIds 供应商数组
	 *  @return
	 * */
	@ApiOperation(value = "供应商审核 -供应商表", notes = "String[] supplierIds")
	@PostMapping(value="/verify")
	public ResultMessage verify(@RequestBody String[] supplierIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"审核失败");
		supplierService.updateStateByPrimaryKey(supplierIds);
		 int state = supplierService.updateByPrimaryKey(supplierIds);
			if(state>0){
				resultMessage.setCode(StateDictionary.OK);
				resultMessage.setMsg("成功审核"+state+"条数据");
			}
			return resultMessage;
	}
	
	/**
	 * 导入
	 *  @param MultipartFile 导入
	 *  @return
	 * */

	@PostMapping(value="/import")
	public ResultMessage importExcel(@RequestParam(value = "fileName", required = true) MultipartFile fileName, HttpServletRequest request, 
			HttpServletResponse response){
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"导入失败");
		try{
			int startRowIndex = 3;
	        List<Map<Integer, Object>> readExcel = ExcelUtil.getExcelData(fileName,startRowIndex);
	        resultMessage = supplierService.insertSupplier(readExcel);
	        return resultMessage;
		}catch(Exception e){
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("供应商模板不正确或者数据有误！");
			return resultMessage;
		}
	}
	
	/**
	 * 下载模板
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/download")
	public ResultMessage download(HttpServletResponse response, HttpServletRequest request) throws Exception{
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"下载失败");
		String fileName = "supplierTemplate.xlsx";//模板暂时存放在resources/templates下
		String downloadName = "供应商模板.xlsx";//下载名请包含后缀
		YycFileUtil.download(downloadName, fileName, response);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg("下载成功");
		return resultMessage;
	}
	
	
	@GetMapping(value = "/export")
	public void export(String supplierIds, HttpServletRequest request, HttpServletResponse response){
		//利用set去重
		Set<String> set = new HashSet<>();
		String[] split = supplierIds.split(",");
		for (String id : split) {
			set.add(id);
		}
		//导出
		supplierService.exportSupplier(set, request, response);
	}
}
