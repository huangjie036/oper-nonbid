package com.yyc.oper.nobid.service.supplier.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.mapper.SupplierMapper;
import com.yyc.oper.nobid.service.supplier.ISupplierColatitudeService;
import com.yyc.oper.nobid.service.supplier.ISupplierQualificationService;
import com.yyc.oper.nobid.service.supplier.ISupplierRegionService;
import com.yyc.oper.nobid.service.supplier.ISupplierService;
import com.yyc.oper.nobid.service.supplier.ISupplierTaxService;
import com.yyc.oper.nobid.supplier.ErpSupplierBean;
import com.yyc.oper.nobid.supplier.SupplierBean;
import com.yyc.oper.nobid.supplier.SupplierColatitudeBean;
import com.yyc.oper.nobid.supplier.SupplierImportEnum;
import com.yyc.oper.nobid.supplier.SupplierQualificationBean;
import com.yyc.oper.nobid.supplier.SupplierRegionBean;
import com.yyc.oper.nobid.supplier.SupplierTaxBean;
import com.yyc.oper.nobid.util.GenerateUUID;
import com.yyc.oper.nobid.util.YycFileUtil;
import com.yyc.oper.nobid.util.YycStringUtils;


@Service
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	private SupplierMapper supplierMapper;
	
	@Autowired
	private ISupplierQualificationService supplierQualificationService;
	
	@Autowired
	private ISupplierColatitudeService supplierColatitudeService;
	
	@Autowired
	private ISupplierRegionService supplierRegionService;
	
	@Autowired
	private ISupplierTaxService supplierTaxService;
	




	@Override
	public int deleteByPrimaryKey(String supplierId) {
		return supplierMapper.deleteByPrimaryKey(supplierId);
	}



	@Override
	public int insertSelective(SupplierBean record) {
		record.setSupplierId(GenerateUUID.getUUID());
		/*---中间表添加供应商的资质  ---*/
		//判断数组不为空
		if (record.getQualificationIds()!=null && record.getQualificationIds().length>0 ){
		String[] qualificationIds= record.getQualificationIds();
		List<SupplierQualificationBean> qualificationlist = new ArrayList<>();
		for (int i = 0; i < qualificationIds.length; i++) {
			String qualificationId = qualificationIds[i];
			if (YycStringUtils.isNotBlank(qualificationId)) {
				SupplierQualificationBean	supplierQualificationBean = new SupplierQualificationBean();
				supplierQualificationBean.setId(GenerateUUID.getUUID());;//随机生成
				supplierQualificationBean.setSupplierId(record.getSupplierId()); //供应商id
				supplierQualificationBean.setQualificationId(qualificationId); //资质id
				supplierQualificationBean.setDel("0");//状态
				qualificationlist.add(supplierQualificationBean);
			}
		}
		supplierQualificationService.addQualificationList(qualificationlist);
	}
		
		/*---中间表添加供应商的税率  ---*/
		//判断数组不为空
		if (record.getTax()!=null && record.getTaxes().length>0 ){
		String[] taxs= record.getTaxes();
		List<SupplierTaxBean> taxlist = new ArrayList<>();
		for (int i = 0; i < taxs.length; i++) {
			String tax = taxs[i];
			if (YycStringUtils.isNotBlank(tax)) {
				SupplierTaxBean	supplierTaxBean = new SupplierTaxBean();
				supplierTaxBean.setId(GenerateUUID.getUUID());//随机生成
				supplierTaxBean.setSupplierId(record.getSupplierId()); //供应商id
				supplierTaxBean.setTaxType(record.getTaxType());//资质id
				supplierTaxBean.setTax(tax);//资质id
				supplierTaxBean.setDel("0");//状态
				taxlist.add(supplierTaxBean);
			}
		}
		supplierTaxService.addTaxList(taxlist);
	}
		
		/*---中间表添加供应商的服务地域  ---*/
		//判断数组不为空
		if (record.getRegionIds()!=null && record.getRegionIds().length>0 ){
		String[] regionIds= record.getRegionIds();
		List<SupplierRegionBean> regionlist = new ArrayList<>();
		for (int i = 0; i < regionIds.length; i++) {
			String regionId = regionIds[i];
			if (YycStringUtils.isNotBlank(regionId)) {
				SupplierRegionBean	supplierRegionBean = new SupplierRegionBean();
				supplierRegionBean.setId(GenerateUUID.getUUID());//随机生成
				supplierRegionBean.setSupplierId(record.getSupplierId()); //供应商id
				supplierRegionBean.setRegionId(regionId);//资质id
				supplierRegionBean.setDel("0");//状态
				regionlist.add(supplierRegionBean);
			}
		}
		supplierRegionService.addRegionList(regionlist);
	}
		
		/*---中间表添加供应商的合作范围  ---*/
		//判断数组不为空
		if (record.getColatitudeIds()!=null && record.getColatitudeIds().length>0 ){
		String[] colatitudeIds= record.getColatitudeIds();
		List<SupplierColatitudeBean> colatitudelist = new ArrayList<>();
		for (int i = 0; i < colatitudeIds.length; i++) {
			String colatitudeId = colatitudeIds[i];
			if (YycStringUtils.isNotBlank(colatitudeId)) {
				SupplierColatitudeBean	supplierColatitudeBean = new SupplierColatitudeBean();
				supplierColatitudeBean.setId(GenerateUUID.getUUID());//随机生成
				supplierColatitudeBean.setSupplierId(record.getSupplierId()); //供应商id
				supplierColatitudeBean.setColatitudeId(colatitudeId);//资质id
				supplierColatitudeBean.setDel("0");//状态
				colatitudelist.add(supplierColatitudeBean);
			}
		}
		supplierColatitudeService.addColatitudeList(colatitudelist);
	}

		

		return  supplierMapper.insertSelective(record);
	}



	@Override
	public PageInfo<SupplierBean> selectByPrimaryKey(SupplierBean record) {
		
		List<SupplierBean> mmNonbidExpertList = supplierMapper.selectByPrimaryKey(record);
		for(SupplierBean supplierBean:mmNonbidExpertList){
			if(supplierBean.getColatitudeId()!=null){
				supplierBean.setColatitudeIds(supplierBean.getColatitudeId().split(","));
			}
			if(supplierBean.getQualificationId()!=null){
				supplierBean.setQualificationIds(supplierBean.getQualificationId().split(","));
				
			}
			if(supplierBean.getColatitudeName()!=null){
				supplierBean.setColatitudeNames(supplierBean.getColatitudeName().split(","));
			}
			if(supplierBean.getQualificationName()!=null){
				supplierBean.setQualificationNames(supplierBean.getQualificationName().split(","));
			}
			if(supplierBean.getRegionId()!=null){
				supplierBean.setRegionIds(supplierBean.getRegionId().split(","));
			}
			if(supplierBean.getTax()!=null){
				supplierBean.setTaxes(supplierBean.getTax().split(","));
			}	
			
		}
		PageInfo<SupplierBean> result = new PageInfo<SupplierBean>(mmNonbidExpertList);
		return result;
	}



	@Override
	public int updateByPrimaryKeySelective(SupplierBean record) {
		/*---中间表添加供应商的资质  ---*/
		//判断数组不为空
		if (record.getQualificationIds()!=null && record.getQualificationIds().length>0 ){
		String[] qualificationIds= record.getQualificationIds();
		List<SupplierQualificationBean> qualificationlist = new ArrayList<>();
		for (int i = 0; i < qualificationIds.length; i++) {
			String qualificationId = qualificationIds[i];
			if (YycStringUtils.isNotBlank(qualificationId)) {
				SupplierQualificationBean	supplierQualificationBean = new SupplierQualificationBean();
				supplierQualificationBean.setId(GenerateUUID.getUUID());;//随机生成
				supplierQualificationBean.setSupplierId(record.getSupplierId()); //供应商id
				supplierQualificationBean.setQualificationId(qualificationId); //资质id
				supplierQualificationBean.setDel("0");//状态
				qualificationlist.add(supplierQualificationBean);
			}
		}
		supplierQualificationService.addQualificationList(qualificationlist);
	}
		
		/*---中间表添加供应商的税率  ---*/
		//判断数组不为空
		if (record.getTaxes()!=null && record.getTaxes().length>0 ){
		String[] taxs= record.getTaxes();
		List<SupplierTaxBean> taxlist = new ArrayList<>();
		for (int i = 0; i < taxs.length; i++) {
			String tax = taxs[i];
			if (YycStringUtils.isNotBlank(tax)) {
				SupplierTaxBean	supplierTaxBean = new SupplierTaxBean();
				supplierTaxBean.setId(GenerateUUID.getUUID());//随机生成
				supplierTaxBean.setSupplierId(record.getSupplierId()); //供应商id
				supplierTaxBean.setTaxType(record.getTaxType());//资质id
				supplierTaxBean.setTax(tax);//资质id
				supplierTaxBean.setDel("0");//状态
				taxlist.add(supplierTaxBean);
			}
		}
		supplierTaxService.addTaxList(taxlist);
	}
		
		/*---中间表添加供应商的服务地域  ---*/
		//判断数组不为空
		if (record.getRegionIds()!=null && record.getRegionIds().length>0 ){
		String[] regionIds= record.getRegionIds();
		List<SupplierRegionBean> regionlist = new ArrayList<>();
		for (int i = 0; i < regionIds.length; i++) {
			String regionId = regionIds[i];
			if (YycStringUtils.isNotBlank(regionId)) {
				SupplierRegionBean	supplierRegionBean = new SupplierRegionBean();
				supplierRegionBean.setId(GenerateUUID.getUUID());//随机生成
				supplierRegionBean.setSupplierId(record.getSupplierId()); //供应商id
				supplierRegionBean.setRegionId(regionId);//资质id
				supplierRegionBean.setDel("0");//状态
				regionlist.add(supplierRegionBean);
			}
		}
		supplierRegionService.addRegionList(regionlist);
	}
		
		/*---中间表添加供应商的合作范围  ---*/
		//判断数组不为空
		if (record.getColatitudeIds()!=null && record.getColatitudeIds().length>0 ){
		String[] colatitudeIds= record.getColatitudeIds();
		List<SupplierColatitudeBean> colatitudelist = new ArrayList<>();
		for (int i = 0; i < colatitudeIds.length; i++) {
			String colatitudeId = colatitudeIds[i];
			if (YycStringUtils.isNotBlank(colatitudeId)) {
				SupplierColatitudeBean	supplierColatitudeBean = new SupplierColatitudeBean();
				supplierColatitudeBean.setId(GenerateUUID.getUUID());//随机生成
				supplierColatitudeBean.setSupplierId(record.getSupplierId()); //供应商id
				supplierColatitudeBean.setColatitudeId(colatitudeId);//资质id
				supplierColatitudeBean.setDel("0");//状态
				colatitudelist.add(supplierColatitudeBean);
			}
		}
		supplierColatitudeService.addColatitudeList(colatitudelist);
	}
		supplierMapper.updateByPrimaryKeySelective(record);
		return 1;
	}



	@Override
	public int updateByPrimaryKey(String[] supplierIds) {
		int result = 0;
		SupplierBean record = new SupplierBean();
		for (int i = 0; i < supplierIds.length; i++) {
			if (YycStringUtils.isNotBlank(supplierIds[i])) {
				record.setSupplierId(supplierIds[i]);
				record.setDel("1");
				result = result + supplierMapper.updateByPrimaryKeySelective(record);
			}
		}
		return result == 0 ? -1 : result;
	}



	@Override
	public ResultMessage insertSupplier(List<Map<Integer, Object>> readExcel) {
		ResultMessage resultMessage = new ResultMessage();
		List<SupplierBean> supplierList = new ArrayList<>();

		for (Map<Integer, Object> map : readExcel) {
			// 当前行 对象。
			SupplierBean supplierBean = new SupplierBean();
			for (Entry<Integer, Object> entry : map.entrySet()) {
				// 遍历枚举 ， 根据对应列 下标 获取对应的set方法名称
				for (SupplierImportEnum supplierBeanEnum : SupplierImportEnum.values()) {
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
			//导入生成自定义编码
			if(supplierBean.getSupplierName()!=null&&supplierBean.getSupplierName()!=""){
				supplierBean.setSupplierId(GenerateUUID.getUUID());
				supplierBean.setDel("0");
				supplierBean.setState("0");
			}
		
			supplierList.add(supplierBean);
		}
		List<SupplierBean> suppliernewList = new ArrayList<>();
		for(SupplierBean supplierBean:supplierList){
			if(supplierBean.getSupplierId()!=""&&supplierBean.getSupplierId()!=null){
				suppliernewList.add(supplierBean);
			}
		}
		List<String> suppliernameList = new ArrayList<>();
		for(SupplierBean supplierBean:suppliernewList){
			suppliernameList.add(supplierBean.getSupplierName());
		}
		List<String> suppliernamesList = supplierMapper.selectSupplierBySupplierNameList(suppliernameList);
		if(suppliernamesList!=null&&suppliernamesList.size()>0){
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("您提供的供应商:" + JSONArray.toJSONString(suppliernamesList) + "已存在，请核对后再进行抽取！");
			return resultMessage;
		}
		int num = supplierMapper.insertImportSupplierList(suppliernewList);
		resultMessage.setMsg("成功导入"+num+"条供应商");
		return resultMessage;

	}
	
	/**
	 * 导出
	 * @param set
	 * @param request
	 * @param response
	 */
	public void exportSupplier(Set<String> set, HttpServletRequest request, HttpServletResponse response) {
		//获取模板
		InputStream ins = YycFileUtil.class.getResourceAsStream("/templates/supplierTemplate.xlsx");
		if(ins == null){
			throw new RuntimeException("要下载的模板不存在！");
		}
		XSSFWorkbook xssfWorkbook = null;
		try {
			xssfWorkbook = new XSSFWorkbook(ins);//获取Excel
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);//获取标签页
		if(sheetAt == null){
			sheetAt = xssfWorkbook.createSheet();//创建
		}
		//记录起始位置,前两行为表头
		int index = 2;
		//根据批次和包号循环查询,进行合并单元格
		for (String str : set) {
			List<Map<String, Object>> supplierlist = supplierMapper.getSupplierList(str);
			if(xssfWorkbook != null){
				//循环数据
				for (int i = 0; i < supplierlist.size(); i++) {
					XSSFRow row = sheetAt.getRow(index + i);//获取行
					if(row == null){
						row = sheetAt.createRow(index + i);
					}
					Map<String,Object> map = supplierlist.get(i);			
					getcell(row,0).setCellValue(YycStringUtils.convertObjToStr(map.get("supplier_name")));//供应商名称
					getcell(row,1).setCellValue(YycStringUtils.convertObjToStr(map.get("account_group")));//账户组
					getcell(row,2).setCellValue(YycStringUtils.convertObjToStr(map.get("colatitude_name")));//合作范围名称
					getcell(row,3).setCellValue(YycStringUtils.convertObjToStr(map.get("qualification_name")));//资质名称
					getcell(row,4).setCellValue(YycStringUtils.convertObjToStr(map.get("business_scope")));//经营范围
					getcell(row,5).setCellValue(YycStringUtils.convertObjToStr(map.get("province_name")));//服务地域省行政区
					getcell(row,6).setCellValue(YycStringUtils.convertObjToStr(map.get("city_name")));//服务地域市行政区
					getcell(row,7).setCellValue(YycStringUtils.convertObjToStr(map.get("tax_type")));//税率类型
					getcell(row,8).setCellValue(YycStringUtils.convertObjToStr(map.get("tax")));//税率
					getcell(row,9).setCellValue(YycStringUtils.convertObjToStr(map.get("recommend_plant")));//推荐单位
					getcell(row,10).setCellValue(YycStringUtils.convertObjToStr(map.get("evaluate_statistics_date_ndpj")));//物料组编码
					getcell(row,11).setCellValue(YycStringUtils.convertObjToStr(map.get("years_ndpj")));//物料组描述
					getcell(row,12).setCellValue(YycStringUtils.convertObjToStr(map.get("contact")));//联系人
					getcell(row,13).setCellValue(YycStringUtils.convertObjToStr(map.get("contact_mail")));//电子邮箱
					getcell(row,14).setCellValue(YycStringUtils.convertObjToStr(map.get("contact_tel_num")));//联系方式
					getcell(row,15).setCellValue(YycStringUtils.convertObjToStr(map.get("contact_mb_num")));//固定电话
					getcell(row,16).setCellValue(YycStringUtils.convertObjToStr(map.get("stock_holder")));//股东
					getcell(row,17).setCellValue(YycStringUtils.convertObjToStr(map.get("legal_representative")));//法定代表人
					getcell(row,18).setCellValue(YycStringUtils.convertObjToStr(map.get("legal_risk_num")));//法律风险数量
					getcell(row,19).setCellValue(YycStringUtils.convertObjToStr(map.get("registered_capital")));//注册资金
					getcell(row,20).setCellValue(YycStringUtils.convertObjToStr(map.get("remark")));//备注
			
					}
				index += supplierlist.size();
			}
		}
	    try {
	    	String fileName = "";
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	String str = format.format(new Date());
	    	fileName = str + "供应商数据导出.xlsx";
	    	//下载操作
	    	response.setCharacterEncoding("utf-8");
	    	response.setContentType("multipart/form-data");
	    	response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
	        //激活下载操作
	        OutputStream out = response.getOutputStream();
	        xssfWorkbook.write(out);
	        out.flush();
	    } catch (Exception e){
	       e.printStackTrace();
	    }
		
		
	}
	
	
	public static XSSFCell getcell(XSSFRow row, int index){
		XSSFCell cell = row.getCell(index);
		if(cell == null)
			cell = row.createCell(index);
		return cell;
	}



	@Override
	public List<Map<String, Object>> getSupplierList(String str) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int insertSupplierList(List<ErpSupplierBean> list) {
       //给list的每一列数据生成uuid
		for(int i=0;i<list.size();i++){
			list.get(i).setId(GenerateUUID.getUUID());
		}
		//erp供应商和工厂添加关联关系
		supplierMapper.insertRelation(list);
		//将erp的供应商添加到本地供应商库
		int state= supplierMapper.insertSupplierList(list);
		return state;
		
	}



	@Override
	public List<SupplierBean> selectSupplierInfo(SupplierBean record) {
		
		List<SupplierBean> supplierList = supplierMapper.selectSupplierInfo(record);
		
		return supplierList;
	}



	@Override
	public int updateStateByPrimaryKey(String[] supplierIds) {
		int result = 0;
		SupplierBean record = new SupplierBean();
		for (int i = 0; i < supplierIds.length; i++) {
			if (YycStringUtils.isNotBlank(supplierIds[i])) {
				record.setSupplierId(supplierIds[i]);
				record.setState("1");
				result = result + supplierMapper.updateByPrimaryKeySelective(record);
			}
		}
		return result == 0 ? -1 : result;
	}
	
}
