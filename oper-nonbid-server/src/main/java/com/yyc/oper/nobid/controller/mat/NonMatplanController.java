package com.yyc.oper.nobid.controller.mat;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.brace.office.util.ExcelUtil;
import com.yyc.oper.nobid.base.ArrayMatplan;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.file.FileinfoBean;
import com.yyc.oper.nobid.mat.MatplanAndMatResponse;
import com.yyc.oper.nobid.mat.MatplanBean;
import com.yyc.oper.nobid.mat.MatplanEditResponse;
import com.yyc.oper.nobid.mat.MatplanListRequest;
import com.yyc.oper.nobid.mat.MatplanMatBean;
import com.yyc.oper.nobid.mat.MatplanResponse;
import com.yyc.oper.nobid.mat.MergeRecorRequest;
import com.yyc.oper.nobid.mat.NonMatplanBean;
import com.yyc.oper.nobid.mat.NonMatplanIdRequest;
import com.yyc.oper.nobid.mat.NonMatplanRequest;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;
import com.yyc.oper.nobid.service.mat.INonMatplanService;
import com.yyc.oper.nobid.service.purchase.IPurchaseSchmeService;
import com.yyc.oper.nobid.supplier.InvitationSupplierBean;
import com.yyc.oper.nobid.supplier.InvitationSupplierResponse;
import com.yyc.oper.nobid.util.GenerateUUID;
import com.yyc.oper.nobid.util.PageInfoCopyUtil;
import com.yyc.oper.nobid.util.ValidatorUtils;
import com.yyc.oper.nobid.util.YycFileUtil;
import com.yyc.oper.nobid.util.YycFileUtils;
import com.yyc.oper.nobid.util.YycStringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 非招标非物资计划表 nonbid_non_matplan
 * @author huangjie
 * */
@RestController
@RequestMapping(value="/nonMatplan")
@Api(value = "NonMatplanController", tags = "非招标非物资计划表 ")
public class NonMatplanController {
	
	@Autowired
	private INonMatplanService nonMatplanService;
	
	@Autowired
	private IPurchaseSchmeService purchaseSchmeService;

	@Value("${oper.filePath}")
	private String filePath;
	
	@Autowired
	private IAuditBuServie auditServie;
	
	/**
	 * 新增
	 * @param record 非招标非物资计划表
	 * @return
	 * */
	@PostMapping(value="/insertSelective")
	@ApiOperation(value = "新增非招标非物资计划表-非招标非物资计划表", notes = "根据对象，新增非招标非物资计划表-非招标非物资计划表")
	public ResultMessage insertSelective(@Valid @RequestBody NonMatplanRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"新增失败");
		List<InvitationSupplierBean> listInvitationSupplierBean = matplanRequest.getListInvitationSupplierBean();
		for(int i=0;i<listInvitationSupplierBean.size();i++) {
			String str = ValidatorUtils.validate(listInvitationSupplierBean.get(i));
			System.out.println(str);
		}
		int num = nonMatplanService.insertSelective(matplanRequest);
		if(num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("新增成功");
		}else if(num == -2) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("手机号码有误！");
		}
		return resultMessage;
	}
	
	/**
	 * 采购计划维护编辑
	 * @param record 非招标非物资计划表
	 * @return
	 * */
	@PostMapping(value="/editMatplanByNonMatplanId")
	@ApiOperation(value = "采购计划维护编辑-非招标非物资计划表", notes = "点击一条数据查看编辑详情")
	public ResultMessage editMatplanByNonMatplanId(@RequestBody NonMatplanIdRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		NonMatplanBean record = new NonMatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		PageInfo<NonMatplanBean> pageInfo = nonMatplanService.getNonMatplanRequest(record);
		List<NonMatplanBean> listMatplanBean = pageInfo.getList();
		List<NonMatplanRequest> listMatplanEditResponse = new ArrayList<NonMatplanRequest>();
		PageInfo<NonMatplanRequest> data = new PageInfo<NonMatplanRequest>(listMatplanEditResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			NonMatplanRequest matplanEditResponse = new NonMatplanRequest();
			NonMatplanBean matplanBean = listMatplanBean.get(i);
			BeanUtils.copyProperties(matplanBean, matplanEditResponse);
			//供应商集合转换对应字段
			if(matplanBean.getListInvitationSupplierBean().size()>0) {
				List<InvitationSupplierResponse> listInvitationSupplierResponse = new ArrayList<InvitationSupplierResponse>();
				for(int j=0; j<matplanBean.getListInvitationSupplierBean().size(); j++) {
					InvitationSupplierResponse invitationSupplierResponse = new InvitationSupplierResponse();
					InvitationSupplierBean temp = matplanBean.getListInvitationSupplierBean().get(j);
					BeanUtils.copyProperties(temp, invitationSupplierResponse);
					invitationSupplierResponse.setSupplierName(temp.getSupplierName());
					listInvitationSupplierResponse.add(invitationSupplierResponse);
				}
				matplanEditResponse.setListInvitationSupplierResponse(listInvitationSupplierResponse);
			}
			//获取批次名称
			if(null != matplanBean.getBatchmanageBean()) {
				matplanEditResponse.setBatchName(matplanBean.getBatchmanageBean().getBatchName());
			}
			listMatplanEditResponse.add(matplanEditResponse);
		}
		if(pageInfo.getList().size()>0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
    	}
		resultMessage.setData(data);
		return resultMessage;
	}
	
	/**
	 * 修改
	 * @param record 非招标非物资计划表
	 * @return
	 * */
	@PostMapping(value="/updateByPrimaryKeySelective")
	@ApiOperation(value = "修改采购计划-非招标非物资计划表", notes = "根据json字符串，修改采购计划")
	public ResultMessage updateByPrimaryKeySelective(@Valid @RequestBody NonMatplanRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"更新失败");
		List<InvitationSupplierBean> listInvitationSupplierBean = matplanRequest.getListInvitationSupplierBean();
		for(int i=0;i<listInvitationSupplierBean.size();i++) {
			String str = ValidatorUtils.validate(listInvitationSupplierBean.get(i));
			System.out.println(str);
		}
		int num = nonMatplanService.updateByPrimaryKeySelective(matplanRequest);
		if(num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("更新成功");
		}
		return resultMessage;
	}
	
	
	/**
	 * 分页查询采购计划维护列表
	 * @param record 非招标非物资计划表
	 * @return
	 * */
	@PostMapping(value="/findMatplanByMaintainState")
	@ApiOperation(value = "分页查询采购计划维护列表-非招标非物资计划表", notes = "根据条件分页查询采购计划维护列表-非招标非物资计划表")
	public ResultMessage findMatplanByMaintainState(@RequestBody MatplanListRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		NonMatplanBean record = new NonMatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		record.setState("-1");//表示查询 1待提交，5已驳回
		PageInfo<NonMatplanBean> pageInfo = nonMatplanService.selectByPrimaryKey(record);
		List<NonMatplanBean> listMatplanBean=pageInfo.getList();
		List<NonMatplanRequest> listMatplanResponse = new ArrayList<NonMatplanRequest>();
		PageInfo<NonMatplanRequest> data = new PageInfo<NonMatplanRequest>(listMatplanResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			NonMatplanRequest matplanResponse = new NonMatplanRequest();
			BeanUtils.copyProperties(listMatplanBean.get(i), matplanResponse);
			listMatplanResponse.add(matplanResponse);
		}
		if(pageInfo.getList().size()>0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
    	}
		resultMessage.setData(data);
		return resultMessage;
	}
	
	
	/**
	 * 分页查询采购计划审核列表
	 * @param record 非招标非物资计划表
	 * @return
	 */
	@PostMapping(value="/findMatplanByExamineState")
	@ApiOperation(value = "分页查询采购计划审核列表-非招标非物资计划表", notes = "根据条件分页查询采购计划审核列表-非招标非物资计划表")
	public ResultMessage findMatplanByExamineState(@RequestBody MatplanListRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		NonMatplanBean record = new NonMatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		record.setState("2");//2待审核
		PageInfo<NonMatplanBean> pageInfo = nonMatplanService.selectByPrimaryKey(record);
		List<NonMatplanBean> listMatplanBean=pageInfo.getList();
		List<NonMatplanRequest> listMatplanResponse = new ArrayList<NonMatplanRequest>();
		PageInfo<NonMatplanRequest> data = new PageInfo<NonMatplanRequest>(listMatplanResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			NonMatplanRequest matplanResponse = new NonMatplanRequest();
			BeanUtils.copyProperties(listMatplanBean.get(i), matplanResponse);
			listMatplanResponse.add(matplanResponse);
		}
		if(pageInfo.getList().size()>0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
    	}
		resultMessage.setData(data);
		return resultMessage;
	}
	
	/**
	 * 分页查询采购计划汇总、确认列表
	 * @param record 非招标非物资计划表
	 * @return
	 */
	@PostMapping(value="/findMatplanBySummary")
	@ApiOperation(value = "分页查询采购计划汇总、确认列表-非招标非物资计划表", notes = "根据条件分页查询采购计划汇总、确认列表-非招标非物资计划表")
	public ResultMessage findMatplanBySummary(@RequestBody MatplanListRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		NonMatplanBean record = new NonMatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		record.setState("3");//3待确认
		PageInfo<NonMatplanBean> pageInfo = nonMatplanService.selectByPrimaryKey(record);
		List<NonMatplanBean> listMatplanBean = pageInfo.getList();
		List<NonMatplanRequest> listMatplanResponse = new ArrayList<NonMatplanRequest>();
		PageInfo<NonMatplanRequest> data = new PageInfo<NonMatplanRequest>(listMatplanResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			NonMatplanRequest matplanResponse = new NonMatplanRequest();
			BeanUtils.copyProperties(listMatplanBean.get(i), matplanResponse);
			listMatplanResponse.add(matplanResponse);
		}
		if(pageInfo.getList().size()>0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
    	}
		resultMessage.setData(data);
		return resultMessage;
	}

	/**
	 * 分页查询采购计划合包列表
	 * @param record 非招标非物资计划表
	 * @return
	 */
	@PostMapping(value="/findMatplanByMerge")
	@ApiOperation(value = "分页查询采购计划合包列表-非招标非物资计划表", notes = "根据条件分页查询采购计划合包列表-非招标非物资计划表")
	public ResultMessage findMatplanByMerge(@RequestBody MatplanListRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		NonMatplanBean record = new NonMatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		record.setState("4");//4已确认
		PageInfo<NonMatplanBean> pageInfo = nonMatplanService.selectByPrimaryKey(record);
		List<NonMatplanBean> listMatplanBean=pageInfo.getList();
		List<NonMatplanRequest> listMatplanResponse = new ArrayList<NonMatplanRequest>();
		PageInfo<NonMatplanRequest> data = new PageInfo<NonMatplanRequest>(listMatplanResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			NonMatplanRequest matplanResponse = new NonMatplanRequest();
			BeanUtils.copyProperties(listMatplanBean.get(i), matplanResponse);
			listMatplanResponse.add(matplanResponse);
		}
		if(pageInfo.getList().size()>0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
    	}
		resultMessage.setData(data);
		return resultMessage;
	}
	
	
	

	/**
	 * 采购计划合包/不合包编辑
	 * @param record 非招标非物资计划表
	 * @return
	 * */
	@PostMapping(value="/editMergeByMatplanIds")
	@ApiOperation(value = "采购计划合包/不合包编辑-非招标非物资计划表", notes = "选中要合包的数据，编辑合包信息")
	public ResultMessage editMergeByMatplanIds(@RequestBody NonMatplanIdRequest matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"编辑失败");
		MergeRecorRequest mergeRecorRequest = nonMatplanService.editMergeByMatplanIds(matplanIds);
		if(null != mergeRecorRequest) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("编辑成功");
		}else{
    		resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("编辑失败");
    	}
		resultMessage.setData(mergeRecorRequest);
		return resultMessage;
	}
	
	
	/**
	 * 物资计划合包
	 * 
	 * @param record 非招标非物资计划表
	 * @return
	 * */
	@PostMapping(value="/matplanMerge")
	@ApiOperation(value = "采购计划的合包-非招标非物资计划表", notes = "根据条件进行采购计划的合包操作")
	public ResultMessage matplanMerge(@RequestBody MergeRecorRequest mergeRecorRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"合包失败");
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(mergeRecorRequest, record);
		int num = nonMatplanService.matplanMerge(mergeRecorRequest);
		if(num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("合包成功");
		}
		return resultMessage;
	}
	
	/**
	 * 不合包
	 * 
	 * @param matplanIds 选中所有物资计划id 集合
	 * @return
	 * */
	@PostMapping(value="/matplanNoMerge")
	@ApiOperation(value = "采购计划的不合包-非招标非物资计划表", notes = "根据条件进行采购计划的不合包操作")
	public ResultMessage matplanNoMerge(@RequestBody MergeRecorRequest mergeRecorRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"不合包失败");
		int num = nonMatplanService.matplanNoMerge(mergeRecorRequest);
		if(num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("不合包成功");
		}
		return resultMessage;
	}
	
	
	/**
	 * 批量删除
	 * @param record 非招标非物资计划表
	 * @return
	 * */
	@PostMapping(value="/updateByBatchIds")
	@ApiOperation(value = "批量删除非招标非物资计划表-非招标批次管理", notes = "根据数组批量删除非招标非物资计划表-非招标批次管理")
	public ResultMessage updateByBatchIds(@RequestBody NonMatplanIdRequest matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"批量删除失败");
		String msg = nonMatplanService.deleteByMatplanIds(matplanIds);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg(msg);
		return resultMessage;
	}
	
	/**
	 * 批量提交
	 * @param record 非招标非物资计划表
	 * @return
	 * */
	@PostMapping(value="/matplanSubmitByMatplanIds")
	@ApiOperation(value = "批量提交非招标非物资计划表-非招标批次管理", notes = "根据数组批量提交非招标非物资计划表-非招标批次管理")
	public ResultMessage matplanSubmitByMatplanIds(@RequestBody NonMatplanIdRequest matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"批量提交失败");
		String msg = nonMatplanService.matplanSubmitByMatplanIds(matplanIds);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg(msg);
		return resultMessage;
	}
	
	/**
	 * 批量审核
	 * @param record 非招标非物资计划表
	 * @return
	 * */
	@PostMapping(value="/matplanExamineByMatplanIds")
	@ApiOperation(value = "批量审核非招标非物资计划表-非招标批次管理", notes = "根据数组批量审核非招标非物资计划表-非招标批次管理")
	public ResultMessage matplanExamineByMatplanIds(@RequestBody NonMatplanIdRequest matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"批量审核失败");
		String msg = nonMatplanService.matplanExamineByMatplanIds(matplanIds);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg(msg);
		return resultMessage;
	}
	
	/**
	 * 批量确认
	 * @param record 非招标非物资计划表
	 * @return
	 * */
	@PostMapping(value="/matplanconfirmByMatplanIds")
	@ApiOperation(value = "批量确认非招标非物资计划表-非招标批次管理", notes = "根据数组批量确认非招标非物资计划表-非招标批次管理")
	public ResultMessage matplanconfirmByMatplanIds(@RequestBody NonMatplanIdRequest matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"批量审核失败");
		String msg = nonMatplanService.matplanconfirmByMatplanIds(matplanIds);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg(msg);
		return resultMessage;
	}
	
	/**
	 * 批量驳回
	 * @param record 非招标非物资计划表
	 * @return
	 * */
	@PostMapping(value="/matplanRejectByMatplanIds")
	@ApiOperation(value = "批量驳回非招标非物资计划表-非招标批次管理", notes = "根据数组批量驳回非招标非物资计划表-非招标批次管理")
	public ResultMessage matplanRejectByMatplanIds(@RequestBody NonMatplanIdRequest matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"批量驳回失败");
		String msg = nonMatplanService.matplanRejectByMatplanIds(matplanIds);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg(msg);
		return resultMessage;
	}
	
	/**
	 * 
	 * 附件上传
	 *
	 * @param files
	 *            文件对象
	 * @return
	 */
	@PostMapping(value = "/uploadDocument")
	@ApiOperation(value = "采购计划附件上传", notes = "采购计划附件上传")
	public ResultMessage uploadDocument( HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="fileName",required=true)MultipartFile[] fileName,String matplanId,String functionName) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "上传失败");
		if (!YycStringUtils.isNotBlank(matplanId)) {
			resultMessage.setMsg("物资计划id不能为空");
			return resultMessage;
		}
		if (!YycStringUtils.isNotBlank(functionName)) {
			resultMessage.setMsg("功能名称不能为空");
			return resultMessage;
		}
		List<FileinfoBean> fileinfo = new ArrayList<>();
		for (MultipartFile file : fileName) {
			String oldName = file.getOriginalFilename();// 得到原文件名
			String suffix = oldName.substring(oldName.lastIndexOf("."), oldName.length());// 得到后缀名
			// 用当前时间毫秒生成新名字，预防重名
			String newName = System.currentTimeMillis() + suffix;
			String uploadPath = filePath;
			try {
				File path = new File(uploadPath);
				// 判断文件夹是否存在，不存在则创建
				if (!path.exists()) {
					path.mkdir();
				}
				String realPath = uploadPath + File.separator + newName;// 文件上传的路径
				file.transferTo(new File(realPath));
				FileinfoBean fileBean = new FileinfoBean();
				fileBean.setId(GenerateUUID.getUUID());
				fileBean.setMatplanId(matplanId);
				fileBean.setFunctionName(functionName);
				fileBean.setPath(realPath);
				fileBean.setOriginalName(oldName);
				fileBean.setNewName(newName);
				fileBean.setSize(YycFileUtils.GetLength(file.getSize()));
				fileBean.setDel("0");
				fileBean.setCreateTime(new Date());
				fileinfo.add(fileBean);
			} catch (Exception e) {
				e.printStackTrace();
				resultMessage.setMsg("上传失败");
				return resultMessage;
			}
		}
		int num = purchaseSchmeService.insertFile(fileinfo);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg("成功上传"+num+"个文件");
		return resultMessage;
	}
	
	/**
	 * 采购计划导入模板下载
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/download")
	@ApiOperation(value = "采购计划导入模板下载", notes = "采购计划导入模板下载")
	public ResultMessage download(HttpServletResponse response, HttpServletRequest request) throws Exception{
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"下载失败");
		String fileName = "nonMatplanTemplate.xlsx";//模板同意存放在resources/templates下
		String downloadName = "非物资采购计划导入模板.xlsx";//下载名请包含后缀
		YycFileUtil.download(downloadName, fileName, response);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg("下载成功");
		return resultMessage;
	}
	
	/**
	 * 采购计划导入
	 *  @param MultipartFile 导入
	 *  @return
	 * */
	@ApiOperation(value = "采购计划导入", notes = "采购计划导入，包含物资计划表、物料表、推荐供应商表的导入")
	@PostMapping(value="/import")
	public ResultMessage importExcel(MultipartFile fileName, HttpServletRequest request, 
			HttpServletResponse response){
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"导入失败");
		try{
			int startRowIndex = 2;
	        List<Map<Integer, Object>> readExcel = ExcelUtil.getExcelData(fileName,startRowIndex);
	        resultMessage = nonMatplanService.importExcel(readExcel);
	        return resultMessage;
		}catch(Exception e){
			e.printStackTrace();
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("采购计划导入模板不正确或者数据有误！");
			return resultMessage;
		}
	}
	
	/**
	 * 采购计划汇总导出
	 * @param id 考试评级ID
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/exportMatplanAndMat")
	@ApiOperation(value = "采购计划汇总导出", notes = "采购计划汇总导出")
	public void exportMatplanAndMat(String matplanIds, HttpServletRequest request, HttpServletResponse response){
		NonMatplanBean record = new NonMatplanBean();
		record.setNonMatplanIds(matplanIds.split(","));
		record.setState("3");//3待确认
		PageInfo<NonMatplanBean> pageInfo = nonMatplanService.getNonMatplanRequest(record);
		List<NonMatplanBean> listMatplanBean=pageInfo.getList();
		List<NonMatplanRequest> listMatplanResponse = new ArrayList<NonMatplanRequest>();
		PageInfo<NonMatplanRequest> data = new PageInfo<NonMatplanRequest>(listMatplanResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			NonMatplanRequest matplanResponse = new NonMatplanRequest();
			BeanUtils.copyProperties(listMatplanBean.get(i), matplanResponse);
			listMatplanResponse.add(matplanResponse);
		}
		//导出
		nonMatplanService.exportNonMatplan(listMatplanResponse, request, response);
	}

}

