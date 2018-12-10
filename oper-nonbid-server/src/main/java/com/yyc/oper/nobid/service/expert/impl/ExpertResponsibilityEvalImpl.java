package com.yyc.oper.nobid.service.expert.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyc.brace.base.principal.CurrentPrincipalHolder;
import com.yyc.oper.nobid.expert.ExpertResponsibilityEnum;
import com.yyc.oper.nobid.expert.ExpertResponsibilityEvalBean;
import com.yyc.oper.nobid.mapper.ExpertResponsibilityEvalMapper;
import com.yyc.oper.nobid.service.expert.IExpertResponsibilityEvalService;
import com.yyc.oper.nobid.util.GenerateUUID;
import com.yyc.oper.nobid.util.YycFileUtil;
import com.yyc.oper.nobid.util.YycStringUtils;

/**
 * Description:履责评价   
 * @author hlg
 * @date 2018年9月13日
 */
@Service
@Transactional
public class ExpertResponsibilityEvalImpl implements IExpertResponsibilityEvalService {

	@Autowired
	private ExpertResponsibilityEvalMapper posonMaper;
	
	@Override
	public PageInfo<ExpertResponsibilityEvalBean> getResponsibilitys(ExpertResponsibilityEvalBean responsibilityEvalBean) throws Exception {
		
		PageHelper.startPage(responsibilityEvalBean.getPageNum(), responsibilityEvalBean.getPageSize());
		List<ExpertResponsibilityEvalBean> responsibilitys = posonMaper.getResponsibilityCondition(responsibilityEvalBean);
		return new PageInfo<>(responsibilitys);
	}

	@Override
	public void addResponsibility(ExpertResponsibilityEvalBean responsibilityEvalBean) throws Exception {
		String uid = CurrentPrincipalHolder.getUid();
		if(null == uid || "".equals(uid)) {
			throw new RuntimeException("未获取操作用户信息,编辑失败");
		}
		responsibilityEvalBean.setId(GenerateUUID.getUUID());
		responsibilityEvalBean.setCreateBy(uid);
		responsibilityEvalBean.setOpeBy(uid);
		responsibilityEvalBean.setOpeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		posonMaper.insertSelective(responsibilityEvalBean);
		
	}

	@Override
	public void updateResponsibility(ExpertResponsibilityEvalBean responsibility) throws Exception {
		String uid = CurrentPrincipalHolder.getUid();
		if(null == uid || "".equals(uid)) {
			throw new RuntimeException("未获取操作用户信息,编辑失败");
		}
		responsibility.setCreateBy(uid);
		responsibility.setOpeBy(uid);
		responsibility.setOpeTime(new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()));
		posonMaper.updateByPrimaryKeySelective(responsibility);
		
	}

	@Override
	public void deleteResponsibility(List<String> ids) throws Exception {
		for (String id : ids) {
			posonMaper.deleteResponsibility(id);
		}
		
	}

	@Override
	public String addResponsibilityInput(List<Map<Integer, Object>> readExcel) {
		String uid = CurrentPrincipalHolder.getUid();
		if(null == uid || "".equals(uid)) {
			throw new RuntimeException("未获取操作用户信息,编辑失败");
		}
		List<ExpertResponsibilityEvalBean> responsibilityBeans = new ArrayList<>();
		
		for (Map<Integer, Object> map : readExcel) {
			//当前行 对象。
			ExpertResponsibilityEvalBean evalBean = new ExpertResponsibilityEvalBean();
			
			for (Entry<Integer, Object> entry : map.entrySet()) {
				//遍历枚举 ， 根据对应列 下标  获取对应的set方法名称
				for (ExpertResponsibilityEnum evalEnum : ExpertResponsibilityEnum.values()) {
					if (evalEnum.getIndex() == entry.getKey()) {
						Method methods;
						try {
							//根据set方法名称 和实体获取  对应Method。
							methods = evalBean.getClass().getDeclaredMethod(evalEnum.getSetMethod(),
									String.class);
							//根据method 对象和实体反射填充  对象属性值。
							methods.invoke(evalBean, entry.getValue());
						} catch (Exception e) {

							e.printStackTrace();
						}
					}
				}
			}
			
			responsibilityBeans.add(evalBean);
		}
		
		int num = 0 ;
		for (ExpertResponsibilityEvalBean rposonMaperBean : responsibilityBeans) {
			rposonMaperBean.setId(GenerateUUID.getUUID());
			rposonMaperBean.setCreateBy(uid);
			rposonMaperBean.setOpeBy(uid);
			rposonMaperBean.setOpeTime(new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()));
			num += posonMaper.insert(rposonMaperBean);
		}
		
		return "履责评价成功导入" + num + "条！";
	}

	@Override
	public void exportResponsibility(Set<String> set, HttpServletRequest request, HttpServletResponse response) {
		
		//获取模板
		String filePath = YycFileUtil.class.getResource("/templates/responsibilityTemplate.xlsx").getPath();
		if(!new File(filePath).exists()){
			throw new RuntimeException("要下载的模板不存在！");
		}
		InputStream ins = null;
		try {
			ins = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(ins == null){
			return;
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
		for (String id : set) {
			List<Map<String, Object>> responsibilitys = posonMaper.selectResponsibilitys(id);
			if(xssfWorkbook != null){
				//循环数据
				for (int i = 0; i < responsibilitys.size(); i++) {
					XSSFRow row = sheetAt.getRow(index + i);//获取行
					if(row == null){
						row = sheetAt.createRow(index + i);
					}
					Map<String,Object> map = responsibilitys.get(i);			
					getcell(row,0).setCellValue(YycStringUtils.convertObjToStr(map.get("projectNum")));//项目编号
					getcell(row,1).setCellValue(YycStringUtils.convertObjToStr(map.get("projectName")));//项目名称
					getcell(row,2).setCellValue(YycStringUtils.convertObjToStr(map.get("groupId")));//组别
					getcell(row,3).setCellValue(YycStringUtils.convertObjToStr(map.get("role")));//角色
					getcell(row,4).setCellValue(YycStringUtils.convertObjToStr(map.get("name")));//姓名
					getcell(row,5).setCellValue(YycStringUtils.convertObjToStr(map.get("businessAbility")));//业务能力
					getcell(row,6).setCellValue(YycStringUtils.convertObjToStr(map.get("workAttitude")));//工作态度
					getcell(row,7).setCellValue(YycStringUtils.convertObjToStr(map.get("honestDiscipline")));//廉洁纪律
					getcell(row,8).setCellValue(YycStringUtils.convertObjToStr(map.get("evalConclusion")));//评价总结
					getcell(row,9).setCellValue(YycStringUtils.convertObjToStr(map.get("evalCountTime")));//评价统计时间
					getcell(row,10).setCellValue(YycStringUtils.convertObjToStr(map.get("evalCountBy")));//评价统计人
					getcell(row,11).setCellValue(YycStringUtils.convertObjToStr(map.get("remark")));//备注
					getcell(row,12).setCellValue(YycStringUtils.convertObjToStr(map.get("evalStartTime")));//评价开始时间
					getcell(row,13).setCellValue(YycStringUtils.convertObjToStr(map.get("evalEndTime")));//评价结束时间
				}
				index += responsibilitys.size();
			}
		}
	    try {
	    	String fileName = "";
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	String str = format.format(new Date());
	    	fileName = str + "履责评价数据导出.xlsx";
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

}
