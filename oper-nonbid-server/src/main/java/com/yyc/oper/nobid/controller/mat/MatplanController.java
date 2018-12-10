package com.yyc.oper.nobid.controller.mat;

import java.io.File;
import java.io.UnsupportedEncodingException;
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

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.brace.office.util.ExcelUtil;
import com.yyc.oper.nobid.base.ArrayMatplan;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.file.FileinfoBean;
import com.yyc.oper.nobid.mat.MatplanAndMatResponse;
import com.yyc.oper.nobid.mat.MatplanBean;
import com.yyc.oper.nobid.mat.MatplanEditResponse;
import com.yyc.oper.nobid.mat.MatplanIdRequest;
import com.yyc.oper.nobid.mat.MatplanListRequest;
import com.yyc.oper.nobid.mat.MatplanMatBean;
import com.yyc.oper.nobid.mat.MatplanRequest;
import com.yyc.oper.nobid.mat.MatplanResponse;
import com.yyc.oper.nobid.mat.MergeRecorRequest;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;
import com.yyc.oper.nobid.service.mat.IMatplanService;
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
 * 非招标物资计划表 nonbid_matplan
 * @author huangjie
 * */
@RestController
@RequestMapping(value="/matplan")
@Api(value = "MatplanController", tags = "非招标物资计划表 ")
public class MatplanController {
	
	@Autowired
	private IMatplanService matplanService;
	
	@Autowired
	private IPurchaseSchmeService purchaseSchmeService;

	@Value("${oper.filePath}")
	private String filePath;
	@Autowired
	private IAuditBuServie auditServie;
	@PostMapping(value="/deleteByPrimaryKey")
	public int deleteByPrimaryKey(String matplanId) {
		return matplanService.deleteByPrimaryKey(matplanId);
	}
	
	/**
	 * 新增
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/insertSelective")
	@ApiOperation(value = "新增非招标物资计划表-非招标物资计划表", notes = "根据对象，新增非招标物资计划表-非招标物资计划表")
	public ResultMessage insertSelective(@Valid @RequestBody MatplanRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"新增失败");
		// JSON转换
		//MatplanBean teacher = JSON.parseObject(record.toString(), new TypeReference<MatplanBean>() {});
		List<MatplanMatBean> listMatplanMatBean = matplanRequest.getListMatplanMatBean();
		for(int i=0;i<listMatplanMatBean.size();i++) {
			String str = ValidatorUtils.validate(listMatplanMatBean.get(i));
			System.out.println(str);
		}
		List<InvitationSupplierBean> listInvitationSupplierBean = matplanRequest.getListInvitationSupplierBean();
		for(int i=0;i<listInvitationSupplierBean.size();i++) {
			String str = ValidatorUtils.validate(listInvitationSupplierBean.get(i));
			System.out.println(str);
		}
		int num = matplanService.insertSelective(matplanRequest);
		if(num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("新增成功");
		}else if(num == -2) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("手机号码有误！");
		}else if(num == -3) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("采购方式单一来源，推荐供应商最多只能1个");
		}
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/matplan/insertSelective");
		auditRes.put("method", "Post");
		auditRes.put("parameter", matplanRequest.getPackageName());
		auditRes.put("result", num > 0 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "增加");
		auditRes.put("auditFunctionName", "新增采购计划");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes",
				"新增采购计划，编号" + matplanRequest.getSerialNum() + ",名称" + matplanRequest.getPackageName());
		auditServie.insertAudit(auditRes);
		return resultMessage;
	}
	
	/**
	 * 采购计划维护编辑
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/editMatplanByMatplanId")
	@ApiOperation(value = "采购计划维护编辑-非招标物资计划表", notes = "点击一条数据查看编辑详情")
	public ResultMessage editMatplanByMatplanId(@RequestBody MatplanIdRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		//record.setState("-1");//表示查询 1待提交，5已驳回
		PageInfo<MatplanBean> pageInfo = matplanService.selectMatplanAndMat(record);
		List<MatplanBean> listMatplanBean=pageInfo.getList();
		List<MatplanEditResponse> listMatplanEditResponse = new ArrayList<MatplanEditResponse>();
		PageInfo<MatplanEditResponse> data = new PageInfo<MatplanEditResponse>(listMatplanEditResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			MatplanEditResponse matplanEditResponse = new MatplanEditResponse();
			MatplanBean matplanBean = listMatplanBean.get(i);
			BeanUtils.copyProperties(matplanBean, matplanEditResponse);
			//供应商集合转换对应字段
			if(matplanBean.getListInvitationSupplierBean().size()>0) {
				List<InvitationSupplierResponse> listInvitationSupplierResponse = new ArrayList<InvitationSupplierResponse>();
				for(int j=0; j<matplanBean.getListInvitationSupplierBean().size(); j++) {
					InvitationSupplierResponse invitationSupplierResponse = new InvitationSupplierResponse();
					InvitationSupplierBean temp = matplanBean.getListInvitationSupplierBean().get(j);
					BeanUtils.copyProperties(temp, invitationSupplierResponse);
					invitationSupplierResponse.setSupplierName(temp.getSupplierName());
					//invitationSupplierResponse.setSupplierPhone(matplanBean.getListSupplierBean().get(j).getPhoneNum());
					listInvitationSupplierResponse.add(invitationSupplierResponse);
					/**ErpSupplierBean erpSupplierBean = new ErpSupplierBean();
					BeanUtils.copyProperties(matplanBean.getListErpSupplierBean().get(j), erpSupplierBean);
					listErpSupplierBean.add(erpSupplierBean);**/
				}
				matplanEditResponse.setListInvitationSupplierResponse(listInvitationSupplierResponse);
			}
			//物料转换
			if(matplanBean.getListMatplanMatBean().size()>0) {
				List<MatplanMatBean> listMatplanMatBean = new ArrayList<MatplanMatBean>();
				for(int j=0; j<matplanBean.getListMatplanMatBean().size(); j++) {
					MatplanMatBean matplanMatBean = new MatplanMatBean();
					BeanUtils.copyProperties(matplanBean.getListMatplanMatBean().get(j), matplanMatBean);
					listMatplanMatBean.add(matplanMatBean);
				}
				matplanEditResponse.setListMatplanMatBean(listMatplanMatBean);
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
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/updateByPrimaryKeySelective")
	@ApiOperation(value = "修改采购计划-非招标物资计划表", notes = "根据json字符串，修改采购计划")
	public ResultMessage updateByPrimaryKeySelective(@Valid @RequestBody MatplanRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"更新失败");
		// JSON转换
		//MatplanBean teacher = JSON.parseObject(record.toString(), new TypeReference<MatplanBean>() {});
		List<MatplanMatBean> listMatplanMatBean = matplanRequest.getListMatplanMatBean();
		for(int i=0;i<listMatplanMatBean.size();i++) {
			String str = ValidatorUtils.validate(listMatplanMatBean.get(i));
			System.out.println(str);
		}
		List<InvitationSupplierBean> listInvitationSupplierBean = matplanRequest.getListInvitationSupplierBean();
		for(int i=0;i<listInvitationSupplierBean.size();i++) {
			String str = ValidatorUtils.validate(listInvitationSupplierBean.get(i));
			System.out.println(str);
		}
		int num = matplanService.updateByPrimaryKeySelective(matplanRequest);
		if(num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("更新成功");
		}
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/matplan/updateByPrimaryKeySelective");
		auditRes.put("method", "Post");
		auditRes.put("parameter", matplanRequest.getPackageName());
		auditRes.put("result", num > 0 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "修改");
		auditRes.put("auditFunctionName", "修改采购计划");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes",
				"修改采购计划，编号" + matplanRequest.getSerialNum() + ",名称" + matplanRequest.getPackageName());
		auditServie.insertAudit(auditRes);
		return resultMessage;
	}
	
	
	/**
	 * 分页查询采购计划维护列表
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/findMatplanByMaintainState")
	@ApiOperation(value = "分页查询采购计划维护列表-非招标物资计划表", notes = "根据条件分页查询采购计划维护列表-非招标物资计划表")
	public ResultMessage findMatplanByMaintainState(@RequestBody MatplanListRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		record.setState("-1");//表示查询 1待提交，5已驳回
		PageInfo<MatplanBean> pageInfo = matplanService.selectByPrimaryKey(record);
		List<MatplanBean> listMatplanBean=pageInfo.getList();
		List<MatplanResponse> listMatplanResponse = new ArrayList<MatplanResponse>();
		PageInfo<MatplanResponse> data = new PageInfo<MatplanResponse>(listMatplanResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			MatplanResponse matplanResponse = new MatplanResponse();
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
	 * @param record 非招标物资计划表
	 * @return
	 */
	@PostMapping(value="/findMatplanByExamineState")
	@ApiOperation(value = "分页查询采购计划审核列表-非招标物资计划表", notes = "根据条件分页查询采购计划审核列表-非招标物资计划表")
	public ResultMessage findMatplanByExamineState(@RequestBody MatplanListRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		record.setState("2");//2待审核
		PageInfo<MatplanBean> pageInfo = matplanService.selectByPrimaryKey(record);
		List<MatplanBean> listMatplanBean=pageInfo.getList();
		List<MatplanResponse> listMatplanResponse = new ArrayList<MatplanResponse>();
		PageInfo<MatplanResponse> data = new PageInfo<MatplanResponse>(listMatplanResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			MatplanResponse matplanResponse = new MatplanResponse();
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
	 * 分页查询采购计划汇总列表
	 * @param record 非招标物资计划表
	 * @return
	 */
	@PostMapping(value="/findMatplanBySummary")
	@ApiOperation(value = "分页查询采购计划汇总列表-非招标物资计划表", notes = "根据条件分页查询采购计划汇总列表-非招标物资计划表")
	public ResultMessage findMatplanBySummary(@RequestBody MatplanListRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		record.setState("3");//3待确认
		PageInfo<MatplanBean> pageInfo = matplanService.selectMatplanAndMat(record);
		List<MatplanBean> listMatplanBean=pageInfo.getList();
		List<MatplanAndMatResponse> listMatplanAndMatResponse = new ArrayList<MatplanAndMatResponse>();
		PageInfo<MatplanAndMatResponse> data = new PageInfo<MatplanAndMatResponse>(listMatplanAndMatResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			MatplanAndMatResponse matplanAndMatResponse = new MatplanAndMatResponse();
			MatplanBean matplanBean = listMatplanBean.get(i);
			BeanUtils.copyProperties(matplanBean, matplanAndMatResponse);
			//供应商集合转换对应字段
			if(matplanBean.getListInvitationSupplierBean().size()>0) {
				for(int j=0; j<matplanBean.getListInvitationSupplierBean().size(); j++) {
					InvitationSupplierBean erpSupplierBean = matplanBean.getListInvitationSupplierBean().get(j);
					if(j == 0) {
						matplanAndMatResponse.setInvitationSupplier1(erpSupplierBean.getSupplierName());
					}else if(j == 1) {
						matplanAndMatResponse.setInvitationSupplier2(erpSupplierBean.getSupplierName());
					}else if(j == 2) {
						matplanAndMatResponse.setInvitationSupplier3(erpSupplierBean.getSupplierName());
					}else if(j == 3) {
						matplanAndMatResponse.setInvitationSupplier4(erpSupplierBean.getSupplierName());
					}else if(j == 4) {
						matplanAndMatResponse.setInvitationSupplier5(erpSupplierBean.getSupplierName());
					}
				}
			}
			//物料转换
			if(matplanBean.getListMatplanMatBean().size()>0) {
				List<MatplanMatBean> listMatplanMatBean = new ArrayList<MatplanMatBean>();
				for(int j=0; j<matplanBean.getListMatplanMatBean().size(); j++) {
					MatplanMatBean matplanMatBean = new MatplanMatBean();
					BeanUtils.copyProperties(matplanBean.getListMatplanMatBean().get(j), matplanMatBean);
					listMatplanMatBean.add(matplanMatBean);
				}
				matplanAndMatResponse.setListMatplanMatBean(listMatplanMatBean);
			}
			listMatplanAndMatResponse.add(matplanAndMatResponse);
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
	 * 分页查询采购计划确认列表
	 * @param record 非招标物资计划表
	 * @return
	 */
	@PostMapping(value="/findMatplanByConfirmState")
	@ApiOperation(value = "分页查询采购计划确认列表-非招标物资计划表", notes = "根据条件分页查询采购计划确认列表-非招标物资计划表")
	public ResultMessage findMatplanByConfirmState(@RequestBody MatplanListRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		record.setState("3");//3待确认
		PageInfo<MatplanBean> pageInfo = matplanService.selectByPrimaryKey(record);
		List<MatplanBean> listMatplanBean=pageInfo.getList();
		List<MatplanResponse> listMatplanResponse = new ArrayList<MatplanResponse>();
		PageInfo<MatplanResponse> data = new PageInfo<MatplanResponse>(listMatplanResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			MatplanResponse matplanResponse = new MatplanResponse();
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
	 * @param record 非招标物资计划表
	 * @return
	 */
	@PostMapping(value="/findMatplanByMerge")
	@ApiOperation(value = "分页查询采购计划合包列表-非招标物资计划表", notes = "根据条件分页查询采购计划合包列表-非招标物资计划表")
	public ResultMessage findMatplanByMerge(@RequestBody MatplanListRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		record.setState("4");//4已确认
		record.setMergeState("-1");//0未合包 1已合包 -1不为1or是null
		PageInfo<MatplanBean> pageInfo = matplanService.selectMatplanAndMat(record);
		List<MatplanBean> listMatplanBean=pageInfo.getList();
		List<MatplanAndMatResponse> listMatplanAndMatResponse = new ArrayList<MatplanAndMatResponse>();
		PageInfo<MatplanAndMatResponse> data = new PageInfo<MatplanAndMatResponse>(listMatplanAndMatResponse);
		data = PageInfoCopyUtil.pageInfoCopy(pageInfo, data);
		for(int i=0; i<listMatplanBean.size(); i++) {
			MatplanAndMatResponse matplanAndMatResponse = new MatplanAndMatResponse();
			MatplanBean matplanBean = listMatplanBean.get(i);
			BeanUtils.copyProperties(matplanBean, matplanAndMatResponse);
			//供应商集合转换对应字段
			if(matplanBean.getListInvitationSupplierBean().size()>0) {
				for(int j=0; j<matplanBean.getListInvitationSupplierBean().size(); j++) {
					InvitationSupplierBean erpSupplierBean = matplanBean.getListInvitationSupplierBean().get(j);
					if(j == 0) {
						matplanAndMatResponse.setInvitationSupplier1(erpSupplierBean.getSupplierName());
					}else if(j == 1) {
						matplanAndMatResponse.setInvitationSupplier2(erpSupplierBean.getSupplierName());
					}else if(j == 2) {
						matplanAndMatResponse.setInvitationSupplier3(erpSupplierBean.getSupplierName());
					}else if(j == 3) {
						matplanAndMatResponse.setInvitationSupplier4(erpSupplierBean.getSupplierName());
					}else if(j == 4) {
						matplanAndMatResponse.setInvitationSupplier5(erpSupplierBean.getSupplierName());
					}
				}
			}
			//物料转换
			if(matplanBean.getListMatplanMatBean().size()>0) {
				List<MatplanMatBean> listMatplanMatBean = new ArrayList<MatplanMatBean>();
				for(int j=0; j<matplanBean.getListMatplanMatBean().size(); j++) {
					MatplanMatBean matplanMatBean = new MatplanMatBean();
					BeanUtils.copyProperties(matplanBean.getListMatplanMatBean().get(j), matplanMatBean);
					listMatplanMatBean.add(matplanMatBean);
				}
				matplanAndMatResponse.setListMatplanMatBean(listMatplanMatBean);
			}
			listMatplanAndMatResponse.add(matplanAndMatResponse);
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
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/editMergeByMatplanIds")
	@ApiOperation(value = "采购计划合包/不合包编辑-非招标物资计划表", notes = "选中要合包的数据，编辑合包信息")
	public ResultMessage editMergeByMatplanIds(@RequestBody ArrayMatplan matplanIds) {
//		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"编辑失败");
//		MergeRecorRequest mergeRecorRequest = matplanService.editMergeByMatplanIds(matplanIds);
//		if(null != mergeRecorRequest) {
//			resultMessage.setCode(StateDictionary.OK);
//			resultMessage.setMsg("编辑成功");
//		}else{
//    		resultMessage.setCode(StateDictionary.ERROR);
//			resultMessage.setMsg("编辑失败");
//    	}
//		resultMessage.setData(mergeRecorRequest);
		return matplanService.editMergeByMatplanIds(matplanIds);
	}
	
	
	/**
	 * 物资计划合包
	 * 
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/matplanMerge")
	@ApiOperation(value = "采购计划的合包-非招标物资计划表", notes = "根据条件进行采购计划的合包操作")
	public ResultMessage matplanMerge(@RequestBody MergeRecorRequest mergeRecorRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"合包失败");
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(mergeRecorRequest, record);
		int num = matplanService.matplanMerge(mergeRecorRequest);
		if(num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("合包成功");
		}else if(num == -3) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("采购方式单一来源，需求供应商只能为1");
		}
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/matplan/matplanMerge");
		auditRes.put("method", "Post");
		auditRes.put("parameter", mergeRecorRequest.getPackageNum());
		auditRes.put("result", num > 0 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "处理");
		auditRes.put("auditFunctionName", "采购计划合包");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "合包采购计划，编号" + mergeRecorRequest.getPurchaseSchmeId() + ",名称"
				+ mergeRecorRequest.getPurchaseSchmeName());
		auditServie.insertAudit(auditRes);
		return resultMessage;
	}
	
	/**
	 * 解包
	 * 
	 * @param matplanIds 选中所有物资计划id 集合
	 * @return
	 * */
	@PostMapping(value="/matplanUnraveling")
	@ApiOperation(value = "采购计划的解包-非招标物资计划表", notes = "根据条件进行采购计划的解包操作")
	public ResultMessage matplanUnraveling(@RequestBody ArrayMatplan matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"采购计划撤销失败");
		int num = matplanService.matplanUnraveling(matplanIds);
		if(num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("采购计划撤销成功");
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
	@ApiOperation(value = "采购计划的不合包-非招标物资计划表", notes = "根据条件进行采购计划的不合包操作")
	public ResultMessage matplanNoMerge(@RequestBody MergeRecorRequest mergeRecorRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"不合包失败");
		int num = matplanService.matplanNoMerge(mergeRecorRequest);
		if(num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("不合包成功");
		}
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/matplan/matplanNoMerge");
		auditRes.put("method", "Post");
		auditRes.put("parameter", mergeRecorRequest.getPurchaseSchmeId());
		auditRes.put("result", num > 0 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "处理");
		auditRes.put("auditFunctionName", "采购计划不合包");
		auditRes.put("tag", "正常");
		auditRes.put("remarkdes", "不合包采购计划，编号" + mergeRecorRequest.getPurchaseSchmeId() + ",名称"
				+ mergeRecorRequest.getPurchaseSchmeName());
		auditServie.insertAudit(auditRes);
		return resultMessage;
	}
	
	/**
	 * 合包确认
	 * 
	 * @param matplanIds 选中所有物资计划id 集合
	 * @return
	 * */
	@PostMapping(value="/updateMergeStateByMatplanId")
	@ApiOperation(value = "采购计划的合包确认-非招标物资计划表", notes = "根据条件进行采购计划的合包确认操作")
	public ResultMessage updateMergeStateByMatplanId(@RequestBody ArrayMatplan matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"确认失败");
		int num = matplanService.updateMergeStateByMatplanId(matplanIds);
		if(num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("确认成功");
		}
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/matplan/updateMergeStateByMatplanId");
		auditRes.put("method", "Post");
		auditRes.put("parameter", JSON.toJSONString(matplanIds).toString());
		auditRes.put("result", num > 0 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "处理");
		auditRes.put("auditFunctionName", "确认采购计划");
		auditRes.put("tag", "正常");
		Map maps = new HashMap<>();
		if (matplanIds != null) {
			String[] ids = matplanIds.getMatplanIds();
			maps = auditServie.getMatPlantById(matplanIds.getMatplanIds()[0]);
		}
		auditRes.put("remarkdes", "确认采购计划，编号" + maps.get("serialNum") + "名称" + maps.get("packageNum"));
		auditServie.insertAudit(auditRes);
		return resultMessage;
	}
	
	/**
	 * 邀请供应商
	 * 
	 * @param matplanIds 选中所有物资计划id 集合
	 * @return
	 * */
	@PostMapping(value="/invitationSupplierByMatplanId")
	@ApiOperation(value = "邀请供应商-非招标物资计划表", notes = "对采购计划新增邀请推荐供应商")
	public ResultMessage invitationSupplierByMatplanId(@RequestBody MatplanRequest matplanRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"邀请供应商失败");
		int num = matplanService.invitationSupplierByMatplanId(matplanRequest);
		if(num > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("邀请供应商成功");
		}
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
		String fileName = "matplanTemplate.xlsx";//模板同意存放在resources/templates下
		String downloadName = "采购计划导入模板.xlsx";//下载名请包含后缀
		YycFileUtil.download(downloadName, fileName, response);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg("下载成功");
		return resultMessage;
	}
	
	
	/**
	 * 批量删除
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/updateByBatchIds")
	@ApiOperation(value = "批量删除非招标物资计划表-非招标批次管理", notes = "根据数组批量删除非招标物资计划表-非招标批次管理")
	public ResultMessage updateByBatchIds(@RequestBody ArrayMatplan matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"批量删除失败");
		String msg = matplanService.deleteByMatplanIds(matplanIds);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg(msg);
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/matplan/updateByBatchIds");
		auditRes.put("method", "Post");
		auditRes.put("parameter", JSON.toJSONString(matplanIds).toString());
		auditRes.put("result", resultMessage.getCode() == 1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "删除");
		auditRes.put("auditFunctionName", "删除采购计划");
		auditRes.put("tag", "正常");
		Map maps = new HashMap<>();
		if (matplanIds != null) {
			String[] ids = matplanIds.getMatplanIds();
			maps = auditServie.getMatPlantById(matplanIds.getMatplanIds()[0]);
		}
		auditRes.put("remarkdes", "确认采购计划，编号" + maps.get("serialNum") + "名称" + maps.get("packageNum"));
		auditServie.insertAudit(auditRes);
		return resultMessage;
	}
	
	/**
	 * 批量提交
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/matplanSubmitByMatplanIds")
	@ApiOperation(value = "批量提交非招标物资计划表-非招标批次管理", notes = "根据数组批量提交非招标物资计划表-非招标批次管理")
	public ResultMessage matplanSubmitByMatplanIds(@RequestBody ArrayMatplan matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"批量提交失败");
		String msg = matplanService.matplanSubmitByMatplanIds(matplanIds);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg(msg);
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/matplan/matplanSubmitByMatplanIds");
		auditRes.put("method", "Post");
		auditRes.put("parameter", JSON.toJSONString(matplanIds).toString());
		auditRes.put("result", resultMessage.getCode() == 1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "处理");
		auditRes.put("auditFunctionName", "提交采购计划");
		auditRes.put("tag", "正常");
		Map maps = new HashMap<>();
		if (matplanIds != null) {
			String[] ids = matplanIds.getMatplanIds();
			maps = auditServie.getMatPlantById(matplanIds.getMatplanIds()[0]);
		}
		auditRes.put("remarkdes", "提交采购计划，编号" + maps.get("serialNum") + "名称" + maps.get("packageNum"));
		auditServie.insertAudit(auditRes);
		return resultMessage;
	}
	
	/**
	 * 批量审核
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/matplanExamineByMatplanIds")
	@ApiOperation(value = "批量审核非招标物资计划表-非招标批次管理", notes = "根据数组批量审核非招标物资计划表-非招标批次管理")
	public ResultMessage matplanExamineByMatplanIds(@RequestBody ArrayMatplan matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"批量审核失败");
		String msg = matplanService.matplanExamineByMatplanIds(matplanIds);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg(msg);
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/matplan/matplanSubmitByMatplanIds");
		auditRes.put("method", "Post");
		auditRes.put("parameter", JSON.toJSONString(matplanIds).toString());
		auditRes.put("result", resultMessage.getCode() == 1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "处理");
		auditRes.put("auditFunctionName", "审核采购计划");
		auditRes.put("tag", "正常");
		Map maps = new HashMap<>();
		if (matplanIds != null) {
			String[] ids = matplanIds.getMatplanIds();
			maps = auditServie.getMatPlantById(matplanIds.getMatplanIds()[0]);
		}
		auditRes.put("remarkdes", "审核采购计划，编号" + maps.get("serialNum") + "名称" + maps.get("packageNum"));
		auditServie.insertAudit(auditRes);
		return resultMessage;
	}
	
	/**
	 * 批量确认
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/matplanconfirmByMatplanIds")
	@ApiOperation(value = "批量确认非招标物资计划表-非招标批次管理", notes = "根据数组批量确认非招标物资计划表-非招标批次管理")
	public ResultMessage matplanconfirmByMatplanIds(@RequestBody ArrayMatplan matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"批量审核失败");
		String msg = matplanService.matplanconfirmByMatplanIds(matplanIds);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg(msg);
		Map<String, Object> auditRes = new HashMap<>();
		auditRes.put("url", "/matplan/matplanconfirmByMatplanIds");
		auditRes.put("method", "Post");
		auditRes.put("parameter", JSON.toJSONString(matplanIds).toString());
		auditRes.put("result", resultMessage.getCode() == 1 ? true : false);
		auditRes.put("auditLevel", 1);
		auditRes.put("auditItemType", "处理");
		auditRes.put("auditFunctionName", "确认采购计划");
		auditRes.put("tag", "正常");
		Map maps = new HashMap<>();
		if (matplanIds != null) {
			String[] ids = matplanIds.getMatplanIds();
			maps = auditServie.getMatPlantById(matplanIds.getMatplanIds()[0]);
		}
		auditRes.put("remarkdes", "确认采购计划，编号" + maps.get("serialNum") + "名称" + maps.get("packageNum"));
		auditServie.insertAudit(auditRes);
		return resultMessage;
	}
	
	/**
	 * 批量驳回
	 * @param record 非招标物资计划表
	 * @return
	 * */
	@PostMapping(value="/matplanRejectByMatplanIds")
	@ApiOperation(value = "批量驳回非招标物资计划表-非招标批次管理", notes = "根据数组批量驳回非招标物资计划表-非招标批次管理")
	public ResultMessage matplanRejectByMatplanIds(@RequestBody ArrayMatplan matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"批量驳回失败");
		String msg = matplanService.matplanRejectByMatplanIds(matplanIds);
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
	 * 查询采购方案附件
	 * 
	 * @param record
	 *            采购方案附件表
	 * @return
	 */
	@PostMapping(value = "/findMatplanfile")
	@ApiOperation(value = "查询采购方案附件", notes = "查询采购方案附件")
	public ResultMessage findMatplanfile(@RequestBody MatplanIdRequest matplanIdRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "查询失败");
		FileinfoBean fileinfoBean = new FileinfoBean();
		fileinfoBean.setMatplanId(matplanIdRequest.getMatplanId());
		PageInfo<FileinfoBean> info = purchaseSchmeService.selectFileListByFileinfoBean(fileinfoBean);
		List<FileinfoBean> list = info.getList();
		if (list.size() > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		} else {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
		}
		resultMessage.setData(list);
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
	        resultMessage = matplanService.importExcel(readExcel);
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
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"查询失败");
		String[] split = matplanIds.split(",");
		MatplanBean record = new MatplanBean();
		record.setMatplanIds(split);
		record.setState("3");//3待确认
		PageInfo<MatplanBean> pageInfo = matplanService.selectMatplanAndMat(record);
		List<MatplanBean> listMatplanBean=pageInfo.getList();
		List<MatplanAndMatResponse> listMatplanAndMatResponse = new ArrayList<MatplanAndMatResponse>();
		for(int i=0; i<listMatplanBean.size(); i++) {
			MatplanAndMatResponse matplanAndMatResponse = new MatplanAndMatResponse();
			MatplanBean matplanBean = listMatplanBean.get(i);
			BeanUtils.copyProperties(matplanBean, matplanAndMatResponse);
			//供应商集合转换对应字段
			if(matplanBean.getListInvitationSupplierBean().size()>0) {
				for(int j=0; j<matplanBean.getListInvitationSupplierBean().size(); j++) {
					InvitationSupplierBean erpSupplierBean = matplanBean.getListInvitationSupplierBean().get(j);
					if(j == 0) {
						matplanAndMatResponse.setInvitationSupplier1(erpSupplierBean.getSupplierName());
					}else if(j == 1) {
						matplanAndMatResponse.setInvitationSupplier2(erpSupplierBean.getSupplierName());
					}else if(j == 2) {
						matplanAndMatResponse.setInvitationSupplier3(erpSupplierBean.getSupplierName());
					}else if(j == 3) {
						matplanAndMatResponse.setInvitationSupplier4(erpSupplierBean.getSupplierName());
					}else if(j == 4) {
						matplanAndMatResponse.setInvitationSupplier5(erpSupplierBean.getSupplierName());
					}
				}
			}
			//物料转换
			if(matplanBean.getListMatplanMatBean().size()>0) {
				List<MatplanMatBean> listMatplanMatBean = new ArrayList<MatplanMatBean>();
				for(int j=0; j<matplanBean.getListMatplanMatBean().size(); j++) {
					MatplanMatBean matplanMatBean = new MatplanMatBean();
					BeanUtils.copyProperties(matplanBean.getListMatplanMatBean().get(j), matplanMatBean);
					listMatplanMatBean.add(matplanMatBean);
				}
				matplanAndMatResponse.setListMatplanMatBean(listMatplanMatBean);
			}
			listMatplanAndMatResponse.add(matplanAndMatResponse);
		}
		//导出
		matplanService.exportMatplanAndMat(listMatplanAndMatResponse, request, response);
	}
	
		
}
