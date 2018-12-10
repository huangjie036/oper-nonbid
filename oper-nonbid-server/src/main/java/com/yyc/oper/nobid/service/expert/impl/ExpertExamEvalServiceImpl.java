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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyc.brace.base.principal.CurrentPrincipalHolder;
import com.yyc.oper.nobid.expert.ExpertExamEvalBean;
import com.yyc.oper.nobid.expert.ExpertExamEvalEnum;
import com.yyc.oper.nobid.mapper.ExpertExamEvalMapper;
import com.yyc.oper.nobid.service.expert.IExpertExamEvalService;
import com.yyc.oper.nobid.util.GenerateUUID;
import com.yyc.oper.nobid.util.YycFileUtil;
import com.yyc.oper.nobid.util.YycStringUtils;
/**
 * Description:考试评价实现   
 * @author hlg
 * @date 2018年9月12日
 */
@Service
@Transactional
public class ExpertExamEvalServiceImpl implements IExpertExamEvalService {

	
	private static final Logger log = LoggerFactory.getLogger(ExpertExamEvalServiceImpl.class);

	
	@Autowired
	private ExpertExamEvalMapper examEvalMapper; 
	
	//分页查询
	@Override
	public PageInfo<ExpertExamEvalBean> getExamEvals(ExpertExamEvalBean examEvalBean) throws Exception {
		PageHelper.startPage(examEvalBean.getPageNum(), examEvalBean.getPageSize());
		List<ExpertExamEvalBean> evalBeans = examEvalMapper.getEvalsByCondition(examEvalBean);
		return new PageInfo<>(evalBeans);
	}

	//新增
	@Override
	public void addexamEval(ExpertExamEvalBean examEvalBean) throws Exception {
		String uid = CurrentPrincipalHolder.getUid();
		if(null == uid || "".equals(uid)) {
			throw new RuntimeException("未获取操作用户信息,新增失败");
		}
		examEvalBean.setId(GenerateUUID.getUUID());
		examEvalBean.setCreateBy(uid);
		examEvalBean.setOpeBy(uid);
		examEvalBean.setOpeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		examEvalMapper.insertSelective(examEvalBean);
	}

	@Override
	public void updateExamEval(ExpertExamEvalBean examEvalBean) {
		String uid = CurrentPrincipalHolder.getUid();
		if(null == uid || "".equals(uid)) {
			throw new RuntimeException("未获取操作用户信息,编辑失败");
		}
		examEvalBean.setOpeBy(uid);
		examEvalBean.setOpeTime(new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()));
		examEvalMapper.updateByPrimaryKey(examEvalBean);
	}

	//删除
	@Override
	public void deleteExamEval(List<String> ids) {
		for (String id : ids) {
			examEvalMapper.deleteExpertExamEval(id);
		}
	}

	@Override
	public String addEvalsInput(List<Map<Integer, Object>> readExcel) {
		String uid = CurrentPrincipalHolder.getUid();
		if(null == uid || "".equals(uid)) {
			throw new RuntimeException("未获取操作用户信息,新增失败");
		}
		List<ExpertExamEvalBean> evalBeans = new ArrayList<>();
		
		for (Map<Integer, Object> map : readExcel) {
			//当前行 对象。
			ExpertExamEvalBean evalBean = new ExpertExamEvalBean();
			
			for (Entry<Integer, Object> entry : map.entrySet()) {
				//遍历枚举 ， 根据对应列 下标  获取对应的set方法名称
				for (ExpertExamEvalEnum evalEnum : ExpertExamEvalEnum.values()) {
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
			
			evalBeans.add(evalBean);
		}
		
		int num = 0 ;
		for (ExpertExamEvalBean expertExamEvalBean : evalBeans) {
			expertExamEvalBean.setId(GenerateUUID.getUUID());
			expertExamEvalBean.setCreateBy(uid);
			expertExamEvalBean.setOpeBy(uid);
			expertExamEvalBean.setOpeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			num += examEvalMapper.insert(expertExamEvalBean);
		}
		
		return "考试评价成功导入" + num + "条！";
	}

	@Override
	public void exportExamEval(Set<String> set, HttpServletRequest request, HttpServletResponse response) {
		
		//获取模板
		String filePath = YycFileUtil.class.getResource("/templates/examEvalTemplate.xlsx").getPath();
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
			List<Map<String, Object>> evamEvals = examEvalMapper.selectExamEvals(id);
			if(xssfWorkbook != null){
				//循环数据
				for (int i = 0; i < evamEvals.size(); i++) {
					XSSFRow row = sheetAt.getRow(index + i);//获取行
					if(row == null){
						row = sheetAt.createRow(index + i);
					}
					Map<String,Object> map = evamEvals.get(i);			
					getcell(row,0).setCellValue(YycStringUtils.convertObjToStr(map.get("workDepartment")));//工作部门
					getcell(row,1).setCellValue(YycStringUtils.convertObjToStr(map.get("idNum")));//身份证号码
					getcell(row,2).setCellValue(YycStringUtils.convertObjToStr(map.get("expertName")));//专家姓名
					getcell(row,3).setCellValue(YycStringUtils.convertObjToStr(map.get("majorExamResults")));//专业考试成绩
					getcell(row,4).setCellValue(YycStringUtils.convertObjToStr(map.get("comprehensiveExamResults")));//综合考试成绩 
					getcell(row,5).setCellValue(YycStringUtils.convertObjToStr(map.get("evalOpinion")));//评价意见
					getcell(row,6).setCellValue(YycStringUtils.convertObjToStr(map.get("reportDepartment")));//提报部门
					getcell(row,7).setCellValue(YycStringUtils.convertObjToStr(map.get("examDate")));//考试时间
					getcell(row,8).setCellValue(YycStringUtils.convertObjToStr(map.get("examPlace")));//考试地点
					getcell(row,9).setCellValue(YycStringUtils.convertObjToStr(map.get("evalCountTime")));//评价统计时间
					getcell(row,10).setCellValue(YycStringUtils.convertObjToStr(map.get("evalCountBy")));//评价统计人
					getcell(row,11).setCellValue(YycStringUtils.convertObjToStr(map.get("remark")));//备注
				}
				index += evamEvals.size();
			}
		}
	    try {
	    	String fileName = "";
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	String str = format.format(new Date());
	    	fileName = str + "考试评价数据导出.xlsx";
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
