package com.yyc.oper.nobid.controller.purchase;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.file.FileinfoBean;
import com.yyc.oper.nobid.merge.MergeRecordId;
import com.yyc.oper.nobid.purchase.PurchaseSchemeBean;
import com.yyc.oper.nobid.purchase.PurchaseSchemeRequest;
import com.yyc.oper.nobid.purchase.PurchaseSchemeResponse;
import com.yyc.oper.nobid.service.purchase.INonPurchaseSchmeService;
import com.yyc.oper.nobid.service.purchase.IPurchaseSchmeService;
import com.yyc.oper.nobid.util.GenerateUUID;
import com.yyc.oper.nobid.util.YycFileUtils;
import com.yyc.oper.nobid.util.YycStringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 采购方案Rest接口(查询接口)
 * 
 * @author 陈文
 * 
 */
@RestController
@RequestMapping(value = "/nonpurchase/schme")
@Api(value = "NonPurchaseSchmeController", tags = "采购方案的提交、审核、驳回、详情查询接口")
public class NonPurchaseSchmeController {

	@Autowired
	private INonPurchaseSchmeService nonpurchaseSchmeService;

	@Autowired
	private IPurchaseSchmeService purchaseSchmeService;

	@Value("${oper.filePath}")
	private String filePath;


	/**
	 * 
	 * 提交方案
	 *
	 * @param record
	 * @return
	 */
	@PostMapping(value = "/submit")
	@ApiOperation(value = "提交采购方案", notes = "请求参数里面传入以下字段：【idList】-采购方案编号集合")
	public ResultMessage submitPurchaseSchme(@RequestBody Map<String, List<String>> param) {
		ResultMessage resultMessage = new ResultMessage();
		try {
			//** 转换实体并组装预置信息 **//*
			if (param.isEmpty() || !param.containsKey("idList")) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("采购方案编号不能为空！");
				return resultMessage;
			}
			boolean detail = nonpurchaseSchmeService.updateState(param.get("idList"), "2");
			if (!detail) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("提交失败！");
				return resultMessage;
			}
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setData(detail);
			resultMessage.setMsg("提交成功！");
			return resultMessage;
		} catch (Exception e) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg(e.getMessage());
			return resultMessage;
		}
	}



	/**
	 * 
	 * 审核方案
	 *
	 * @param record
	 * @return
	 */
	@PostMapping(value = "/verify")
	@ApiOperation(value = "审核采购方案", notes = "请求参数里面传入以下字段：【idList】-采购方案编号集合")
	public ResultMessage verifyPurchaseSchme(@RequestBody Map<String, List<String>> param) {
		ResultMessage resultMessage = new ResultMessage();
		try {
			//** 转换实体并组装预置信息 **//*
			if (param.isEmpty() || !param.containsKey("idList")) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("采购方案编号不能为空！");
				return resultMessage;
			}
			boolean detail = nonpurchaseSchmeService.updateState(param.get("idList"), "3");
			if (!detail) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("审核失败！");
				return resultMessage;
			}
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setData(detail);
			resultMessage.setMsg("审核中！");
			return resultMessage;
		} catch (Exception e) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg(e.getMessage());
			return resultMessage;
		}
	}



	/**
	 * 
	 * 通过方案
	 *
	 * @param record
	 * @return
	 */
	@PostMapping(value = "/pass")
	@ApiOperation(value = "通过采购方案", notes = "请求参数里面传入以下字段：【idList】-采购方案编号集合")
	public ResultMessage passPurchaseSchme(@RequestBody  Map<String, List<String>> param) {
		ResultMessage resultMessage = new ResultMessage();
		try {
			//** 转换实体并组装预置信息 **//*
			if (param.isEmpty() || !param.containsKey("idList")) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("采购方案编号不能为空！");
				return resultMessage;
			}
			boolean detail = nonpurchaseSchmeService.updateState(param.get("idList"), "4");
			if (!detail) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("方案通过失败！");
				return resultMessage;
			}
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setData(detail);
			resultMessage.setMsg("方案成功通过！");
			return resultMessage;
		} catch (Exception e) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg(e.getMessage());
			return resultMessage;
		}
	}


	/**
	 * 
	 * 驳回方案
	 *
	 * @param record
	 * @return
	 */
	@PostMapping(value = "/reject")
	@ApiOperation(value = "驳回采购方案", notes = "请求参数里面传入以下字段：【idList】-采购方案编号集合")
	public ResultMessage rejectPurchaseSchme(@RequestBody  Map<String, List<String>> param) {
		ResultMessage resultMessage = new ResultMessage();
		try {
			//** 转换实体并组装预置信息 **//*
			if (param.isEmpty() || !param.containsKey("idList")) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("采购方案编号不能为空！");
				return resultMessage;
			}
			boolean detail = nonpurchaseSchmeService.updateState(param.get("idList"), "5");
			if (!detail) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("驳回失败！");
				return resultMessage;
			}
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setData(detail);
			resultMessage.setMsg("驳回成功！");
			return resultMessage;
		} catch (Exception e) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg(e.getMessage());
			return resultMessage;
		}
	}


	/**
	 * 
	 * 合包退回
	 *
	 * @param record
	 * @return
	 */
	@PostMapping(value = "/mergeback")
	@ApiOperation(value = "合包退回方案", notes = "请求参数里面传入以下字段：【idList】-采购方案编号集合")
	public ResultMessage mergebackPurchaseSchme(@RequestBody Map<String, List<String>> param) {
		ResultMessage resultMessage = new ResultMessage();
		try {
			/** 转换实体并组装预置信息 **/
			if (param.isEmpty() || !param.containsKey("idList")) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("采购方案编号不能为空！");
				return resultMessage;
			}

			boolean detail = nonpurchaseSchmeService.deleteMergeState(param.get("idList"));
			if (!detail) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("合包退回失败！");
				return resultMessage;
			}
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setData(detail);
			resultMessage.setMsg("合包退回通过！");
			return resultMessage;
		} catch (Exception e) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg(e.getMessage());
			return resultMessage;
		}
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
	@ApiOperation(value = "采购方案附件上传", notes = "采购方案附件上传")
	public ResultMessage uploadDocument(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "fileName", required = true) MultipartFile[] fileName, String purchaseSchemeId,
			String functionName) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "上传失败");
		if (!YycStringUtils.isNotBlank(purchaseSchemeId)) {
			resultMessage.setMsg("采购方案编号不能为空");
			return resultMessage;
		}
		if (!YycStringUtils.isNotBlank(functionName)) {
			resultMessage.setMsg("功能名称不能为空");
			return resultMessage;
		}
		List<FileinfoBean> fileinfo = new ArrayList<>();
		for (MultipartFile file : fileName) {
			String oldName = file.getOriginalFilename();// 得到原文件名
			System.out.println("old---"+oldName);
			try {
				oldName = new String(oldName.getBytes("gbk"), "utf-8");
				System.out.println("old1---"+oldName);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//转换文件名，防止乱码
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
				fileBean.setPurchaseSchmeId(purchaseSchemeId);
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
		int num = nonpurchaseSchmeService.insertFile(fileinfo);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg("成功上传" + num + "个文件");
		return resultMessage;
	}

	@GetMapping(value = "/download")
	@ApiOperation(value = "采购方案附件下载", notes = "采购方案附件下载")
	public ResultMessage download(String id, HttpServletResponse response) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "下载失败");
		FileinfoBean fileBean = nonpurchaseSchmeService.selectByPrimaryKey(id);
		response.setCharacterEncoding("utf-8");
		try {
			File file = new File(fileBean.getPath());
			// 以流的形式下载文件。
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;fileName=" + new String(fileBean.getOriginalName().getBytes("UTF-8"), "iso-8859-1"));
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			resultMessage.setMsg("下载成功");
			resultMessage.setCode(StateDictionary.OK);
			return resultMessage;
		} catch (IOException ex) {
			ex.printStackTrace();
			resultMessage.setMsg("下载失败");
			return resultMessage;
		}

	}

	/**
	 * 查询采购方案附件
	 * 
	 * @param record
	 *            采购方案附件表
	 * @return
	 */
	@PostMapping(value = "/findPurchaseSchemefile")
	@ApiOperation(value = "查询采购方案附件", notes = "查询采购方案附件")
	public ResultMessage findPurchaseSchemefile(@RequestBody PurchaseSchemeBean purchaseSchme) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "查询失败");
		String purchaseSchmeId = purchaseSchme.getPurchaseSchemeId();
		List<FileinfoBean> filelist = nonpurchaseSchmeService.selectFileList(purchaseSchmeId);
		if (filelist.size() > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		} else {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
		}
		resultMessage.setData(filelist);
		return resultMessage;
	}


	/**
	 * 采购方案详情查询
	 * 
	 * @param 采购方案的主键编号
	 * @return
	 */
	@PostMapping(value = "/detail")
	@ApiOperation(value = "查询单个采购方案详情", notes = "根据json字符串，json字符串里面传入以下字段：【purchaseSchmeId】-采购方案编号")
	public ResultMessage getDetail(@RequestBody MergeRecordId mergeRecordId) {
		ResultMessage resultMessage = new ResultMessage();
		// try {
		/** 转换实体并组装预置信息 **/
		/**
		 * JSONObject jsonObj = JSONObject.parseObject(record); if
		 * (!jsonObj.containsKey("purchaseSchmeId") ||
		 * jsonObj.get("purchaseSchmeId") == null) {
		 * resultMessage.setCode(StateDictionary.ERROR);
		 * resultMessage.setMsg("采购方案编号不能为空！"); return resultMessage; }
		 * 
		 * PurchaseSchemeBean detail =
		 * purchaseSchmeService.getDetail(jsonObj.getString("purchaseSchmeId"));
		 * if (detail == null) { resultMessage.setCode(StateDictionary.ERROR);
		 * resultMessage.setMsg("查询失败！"); return resultMessage; }
		 * resultMessage.setCode(StateDictionary.OK);
		 * resultMessage.setData(detail); resultMessage.setMsg("查询成功！"); return
		 * resultMessage; } catch (Exception e) {
		 * resultMessage.setCode(StateDictionary.ERROR);
		 * resultMessage.setMsg(e.getMessage()); return resultMessage; }
		 **/
		PurchaseSchemeResponse detail = nonpurchaseSchmeService.getDetail(mergeRecordId.getMergeId());
		if (detail != null) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		} else {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
		}
		resultMessage.setData(detail);
		return resultMessage;
	}

	/**
	 * 分页查询采购方案列表
	 * 
	 * @param record
	 *            采购方案表
	 * @return
	 */
	@PostMapping(value = "/findPurchaseSchemeByMaintainState")
	@ApiOperation(value = "分页查询采购方案列表", notes = "根据条件分页查询采购方案列表-采购方案表")
	public ResultMessage findPurchaseSchemeByMaintainState(@RequestBody PurchaseSchemeRequest purchaseSchemeRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "查询失败");
		purchaseSchemeRequest.setState("-1");// 表示查询1待提交，5已驳回
		purchaseSchemeRequest.setIsMat("1");//0物资，1非物资
		PageInfo<PurchaseSchemeResponse> pageInfo = purchaseSchmeService
				.selectByPurchaseSchemeRequest(purchaseSchemeRequest);
		if (pageInfo.getList().size() > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		} else {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
		}
		resultMessage.setData(pageInfo);
		return resultMessage;
	}

	/**
	 * 分页查询采购方案审核列表
	 * 
	 * @param record
	 *            采购方案表
	 * @return
	 */
	@PostMapping(value = "/findPurchaseSchemeByExamineState")
	@ApiOperation(value = "分页查询采购方案审核列表", notes = "根据条件分页查询采购方案审核列表-采购方案表")
	public ResultMessage findPurchaseSchemeByExamineState(@RequestBody PurchaseSchemeRequest purchaseSchemeRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "查询失败");
		purchaseSchemeRequest.setState("2");// 表示待审批
		purchaseSchemeRequest.setIsMat("1");//0物资，1非物资
		PageInfo<PurchaseSchemeResponse> pageInfo = purchaseSchmeService
				.selectByPurchaseSchemeRequest(purchaseSchemeRequest);
		if (pageInfo.getList().size() > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		} else {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
		}
		resultMessage.setData(pageInfo);
		return resultMessage;
	}

	/**
	 * 分页查询采购方案确认列表
	 * 
	 * @param record
	 *            采购方案表
	 * @return
	 */
	@PostMapping(value = "/findPurchaseSchemeByConfirmState")
	@ApiOperation(value = "分页查询采购方案确认列表", notes = "根据条件分页查询采购方案确认列表-采购方案表")
	public ResultMessage findPurchaseSchemeByConfirmState(@RequestBody PurchaseSchemeRequest purchaseSchemeRequest) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "查询失败");
		purchaseSchemeRequest.setState("3");// 表示待确认
		purchaseSchemeRequest.setIsMat("1");//0物资，1非物资
		PageInfo<PurchaseSchemeResponse> pageInfo = purchaseSchmeService
				.selectByPurchaseSchemeRequest(purchaseSchemeRequest);
		if (pageInfo.getList().size() > 0) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
		} else {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
		}
		resultMessage.setData(pageInfo);
		return resultMessage;
	}








}
