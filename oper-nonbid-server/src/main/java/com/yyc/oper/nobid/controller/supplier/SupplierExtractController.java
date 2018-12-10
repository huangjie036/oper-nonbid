package com.yyc.oper.nobid.controller.supplier;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.brace.office.util.ExcelUtil;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.service.audit.IAuditBuServie;
import com.yyc.oper.nobid.service.expert.IExtractSupplierService;
import com.yyc.oper.nobid.supplier.SupplierBean;
import com.yyc.oper.nobid.supplier.SupplierBeanEnum;
import com.yyc.oper.nobid.util.YycFileUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 供应商抽取Rest接口(增删改查)
 * 
 * @author WangWei/chenwen
 */
@RestController
@RequestMapping(value = "/extract/supplier")
@Api(value = "SupplierExtractController", tags = "采购方案抽取供应商接口")
public class SupplierExtractController {

	@Autowired
	private IExtractSupplierService extractSupplierService;
	@Autowired
	private IAuditBuServie auditServie;

	/**
	 * 新增
	 * 
	 * @param record
	 *            供应商抽取结果
	 * @return
	 */
	@PostMapping(value = "/new")
	@ApiOperation(value = "采购方案抽取供应商", notes = "根据json字符串，json字符串里面传入以下字段： 【purchaseSchmeId】-采购方案编号，"
			+ "【mergeId】-合包编号，" + "【opertionMethod】-操作方式，" + "【supplierCount】-总的供应商需求数量，"+"【recommendSupplierCount】-推荐供应商需求数量，" + "【supplierIdList】-供应商的编号集合")
	public ResultMessage insertSelective(@RequestBody String param) {
		ResultMessage resultMessage = new ResultMessage();
		try {
			/** 转换实体并组装预置信息 **/
			JSONObject jsonObj = JSONObject.parseObject(param);
			if (!jsonObj.containsKey("purchaseSchmeId") || jsonObj.get("purchaseSchmeId") == null) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("采购方案不能为空！");
				return resultMessage;
			}
			if (!jsonObj.containsKey("mergeId") || jsonObj.get("mergeId") == null) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("采购计划合包编号不能为空！");
				return resultMessage;
			}
			if (!jsonObj.containsKey("opertionMethod") || jsonObj.get("opertionMethod") == null) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("操作方式不能为空！");
				return resultMessage;
			}
			if (!jsonObj.containsKey("supplierCount") || jsonObj.get("supplierCount") == null) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("供应商数量不能为空！");
				return resultMessage;
			}

			/** 获取参数 **/

			String purchaseSchmeId = jsonObj.getString("purchaseSchmeId");
			String mergeId = jsonObj.getString("mergeId");
			String opertionMethod = jsonObj.getString("opertionMethod");
			Integer supplierCount = jsonObj.getInteger("supplierCount");
			Integer recommendSupplierCount = jsonObj.getInteger("recommendSupplierCount");

			// 手工抽取
			if (opertionMethod.equals("2")) {
				if (!jsonObj.containsKey("supplierIdList") || jsonObj.get("supplierIdList") == null) {
					resultMessage.setCode(StateDictionary.ERROR);
					resultMessage.setMsg("抽取的供应商编号集合不能为空！");
					return resultMessage;
				}
				JSONArray jsonArray = jsonObj.getJSONArray("supplierIdList");
				List<String> supplierIdList = jsonArray.toJavaList(String.class);
				JSONArray jsonArray1 = jsonObj.getJSONArray("recommendSupplierIdList");
				List<String> recommendSupplierIdList = jsonArray1.toJavaList(String.class);
				resultMessage = extractSupplierService.saveSupplierExtractManual(purchaseSchmeId, mergeId,
						opertionMethod, supplierCount,recommendSupplierCount,recommendSupplierIdList, supplierIdList);
				// 随机抽取
			} else if (opertionMethod.equals("3")) {
				JSONArray regionIdsjsonArray = jsonObj.getJSONArray("regionIds");
				JSONArray qualificationIdsjsonArray = jsonObj.getJSONArray("qualificationIds");
				JSONArray colatitudeIdsjsonArray = jsonObj.getJSONArray("colatitudeIds");
				JSONArray supplierIdsjsonArray = jsonObj.getJSONArray("supplierIds");
				String taxType = jsonObj.getString("taxType");
				SupplierBean supplierBean = new SupplierBean();
				if (regionIdsjsonArray != null) {
					String[] regionIds = (String[]) regionIdsjsonArray.toArray(new String[regionIdsjsonArray.size()]);
					supplierBean.setRegionIds(regionIds);
				}
				if (qualificationIdsjsonArray != null) {
					String[] qualificationIds = (String[]) qualificationIdsjsonArray
							.toArray(new String[qualificationIdsjsonArray.size()]);
					supplierBean.setQualificationIds(qualificationIds);
				}
				if (colatitudeIdsjsonArray != null) {
					String[] colatitudeIds = (String[]) colatitudeIdsjsonArray
							.toArray(new String[colatitudeIdsjsonArray.size()]);
					supplierBean.setColatitudeIds(colatitudeIds);
				}
				if (supplierIdsjsonArray != null) {
					String[] supplierIds = (String[]) supplierIdsjsonArray
							.toArray(new String[supplierIdsjsonArray.size()]);
					supplierBean.setSupplierIds(supplierIds);

				}
				supplierBean.setTaxType(taxType);
				List<String> supplierIdList = extractSupplierService.selectSupplierIdList(supplierBean);
				JSONArray jsonArray = jsonObj.getJSONArray("recommendSupplierIdList");
				List<String> recommendSupplierIdList = jsonArray.toJavaList(String.class);
				resultMessage = extractSupplierService.saveSupplierExtractRandom(purchaseSchmeId, mergeId,
						opertionMethod, supplierCount,recommendSupplierCount,recommendSupplierIdList,supplierIdList);
			}

			return resultMessage;
			/** 保存业务 **/

		} catch (Exception e) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg(e.getMessage());
			return resultMessage;
		}
	}
	@PostMapping(value = "/list")
	public ResultMessage selectByPrimaryKey(@RequestBody SupplierBean record) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "查询失败");
		List<SupplierBean> result = extractSupplierService.getSupplierByParam(record);
		if (CollectionUtils.isNotEmpty(result)) {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("查询成功");
			resultMessage.setData(result);
		} else {
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("未查询出结果");
			resultMessage.setData(result);
		}
		return resultMessage;
	}

	/**
	 * erp物料信息导入
	 * 
	 * @param MultipartFile
	 *            导入抽取
	 * @return
	 */
	@ApiOperation(value = "导入抽取-供应商表", notes = "导入抽取-供应商表")
	@PostMapping(value = "/import")
	public ResultMessage importExcel(@RequestParam(value = "fileName", required = true) MultipartFile fileName,
			@RequestParam String param, HttpServletResponse response) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "导入失败");
		try {
			int startRowIndex = 3;
			List<Map<Integer, Object>> readExcel = ExcelUtil.getExcelData(fileName, startRowIndex);
			/** 转换实体并组装预置信息 **/
			JSONObject jsonObj = JSONObject.parseObject(param);
			List<SupplierBean> supplierList = new ArrayList<>();

			for (Map<Integer, Object> map : readExcel) {
				// 当前行 对象。
				SupplierBean supplierBean = new SupplierBean();
				for (Entry<Integer, Object> entry : map.entrySet()) {
					// 遍历枚举 ， 根据对应列 下标 获取对应的set方法名称
					for (SupplierBeanEnum supplierBeanEnum : SupplierBeanEnum.values()) {
						if (supplierBeanEnum.getIndex() == entry.getKey()) {
							Method methods;
							try {
								// 根据set方法名称 和实体获取 对应Method。
								methods = supplierBean.getClass().getDeclaredMethod(supplierBeanEnum.getSetMethod(),
										String.class);
								// 根据method 对象和实体反射填充 对象属性值。
								methods.invoke(supplierBean, entry.getValue());
							} catch (Exception e) {

								e.printStackTrace();
							}
						}
					}
				}

				supplierList.add(supplierBean);
			}
			List<String> supplierNameList = new ArrayList<String>();
			for (SupplierBean supplierBean : supplierList) {
				String supplierName = supplierBean.getSupplierName();
				supplierNameList.add(supplierName);
			}
			List<String> supplierIdList = extractSupplierService.selectSupplierBySupplierNameList(supplierNameList);

			/* 如果都不存在，则直接返回错误提示 */
			if (CollectionUtils.isEmpty(supplierIdList)) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("您提供的供应商不存在，请核对后再进行抽取！");
				return resultMessage;
			}

			/* 如果部分不存在，则直接返回哪些供应商不存在的消息 */
			if (supplierNameList.size() != supplierIdList.size()) {
				List<String> supplierNewNameList = extractSupplierService
						.selectNewSupplierBySupplierIdList(supplierIdList);
				supplierNameList.removeAll(supplierNewNameList);
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("您提供的供应商:" + JSONArray.toJSONString(supplierNameList) + "不存在，请核对后再进行抽取！");
				return resultMessage;
			}

			if (!jsonObj.containsKey("purchaseSchmeId") || jsonObj.get("purchaseSchmeId") == null) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("采购方案不能为空！");
				return resultMessage;
			}
			if (!jsonObj.containsKey("mergeId") || jsonObj.get("mergeId") == null) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("采购计划合包编号不能为空！");
				return resultMessage;
			}
			if (!jsonObj.containsKey("opertionMethod") || jsonObj.get("opertionMethod") == null) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("操作方式不能为空！");
				return resultMessage;
			}
			if (!jsonObj.containsKey("supplierCount") || jsonObj.get("supplierCount") == null) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("供应商数量不能为空！");
				return resultMessage;
			}

			/** 获取参数 **/

			String purchaseSchmeId = jsonObj.getString("purchaseSchmeId");
			String mergeId = jsonObj.getString("mergeId");
			String opertionMethod = jsonObj.getString("opertionMethod");
			Integer supplierCount = jsonObj.getInteger("supplierCount");
			Integer recommendSupplierCount = jsonObj.getInteger("recommendSupplierCount");
			if(recommendSupplierCount>=supplierCount){
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("推荐供应商数量大于需要供应商数量，不能导入抽取！");
				return resultMessage;
			}
			resultMessage = extractSupplierService.saveSupplierExtractImport(purchaseSchmeId, mergeId, opertionMethod,
					supplierCount, supplierIdList);
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("导入成功");
			return resultMessage;
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("供应商模板不正确或者数据有误！");
			return resultMessage;
		}
	}

	/**
	 * 下载模板
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/download")
	public ResultMessage download(HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR, "下载失败");
		String fileName = "supplierExtractImportTemplate.xlsx";// 模板暂时存放在resources/templates下
		String downloadName = "供应商导入抽取模板.xlsx";// 下载名请包含后缀
		YycFileUtil.download(downloadName, fileName, response);
		resultMessage.setCode(StateDictionary.OK);
		resultMessage.setMsg("下载成功");
		return resultMessage;
	}

}
