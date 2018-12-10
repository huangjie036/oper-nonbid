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
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.yyc.brace.base.principal.CurrentPrincipalHolder;
import com.yyc.brace.office.util.ExcelUtil;
import com.yyc.oper.nobid.enumbean.ImportExpertExtractBeanEnum;
import com.yyc.oper.nobid.expert.ExpertBean;
import com.yyc.oper.nobid.expert.ExpertExtractRequest;
import com.yyc.oper.nobid.expert.ExpertExtractResultBean;
import com.yyc.oper.nobid.expert.ExpertExtractResultDetailBean;
import com.yyc.oper.nobid.major.MajorBean;
import com.yyc.oper.nobid.mapper.ExpertExtractResultDetailMapper;
import com.yyc.oper.nobid.mapper.ExpertExtractResultMapper;
import com.yyc.oper.nobid.mapper.ExpertMapper;
import com.yyc.oper.nobid.mapper.MajorMapper;
import com.yyc.oper.nobid.mapper.MergeRecordMapper;
import com.yyc.oper.nobid.merge.MergeRecordBean;
import com.yyc.oper.nobid.service.expert.IExpertExtractResultService;
import com.yyc.oper.nobid.supplier.ErpSupplierBean;
import com.yyc.oper.nobid.util.GenerateUUID;
import com.yyc.oper.nobid.util.YycFileUtil;
import com.yyc.oper.nobid.util.YycStringUtils;

@Service
public class ExpertExtractResultServiceImpl implements IExpertExtractResultService{
	
	@Autowired
	private ExpertExtractResultMapper expertExtractResultMapper;
	
	@Autowired
	private ExpertExtractResultDetailMapper expertExtractResultDetailMapper;
	
	@Autowired
	private ExpertMapper expertMapper;
	
	@Autowired
	private MajorMapper majorMapper;
	
	@Autowired
	private MergeRecordMapper mergeRecordMapper;
	
	@Override
	public int deleteByPrimaryKey(String extractId) {
		return expertExtractResultMapper.deleteByPrimaryKey(extractId);
	}
	
	@Override
	public int insertSelective(ExpertExtractRequest request, MultipartFile importfile) {
		int result = 0;
		//验证是否已抽取
		MergeRecordBean temp = new MergeRecordBean();
		temp.setMergeId(request.getMergeId());
		List<MergeRecordBean> listTemp = mergeRecordMapper.selectByPrimaryKey(temp);
		if(null != listTemp && listTemp.size() > 0) {
			if("1".equals(listTemp.get(0).getExpertExtractState())) {
				return -2;//记录已抽取，不能重复抽取
			}
		}else {
			return -10;//未找到采购方案
		}
		String extractId = GenerateUUID.getUUID();
		ExpertExtractResultBean record = new ExpertExtractResultBean();
		BeanUtils.copyProperties(request, record);
		record.setExtractId(extractId);
		//验证商务专家数量和技术专家数量
		if(null==record.getBusinessExpertNumber() && null==record.getTechnologyExpertNumber()) {
			return -1;//商务专家数量和技术专家数量不能为为空
		}else if(record.getBusinessExpertNumber()==0 && record.getTechnologyExpertNumber()==0) {
			return -3;//商务专家数量和技术专家数量不能都为0
		}
		//导入抽取 获取导入供应商数据
		if("1".equals(record.getOpeWay())) {
			int startRowIndex = 2;
			List<Map<Integer, Object>> readExcel = ExcelUtil.getExcelData(importfile,startRowIndex);
			List<String> expertCodeList = importExpertExtract(readExcel);
			record.setExpertCodeList(toArrayByObject(expertCodeList.toArray()));
		}
		//验证手动|导入抽取时，选中专家数量和商务专家、技术专家数量是否相等
		List<ExpertBean> listBusinessExpert1 = new ArrayList<>();//导入抽取 商务专家集合
		List<ExpertBean> listTechnologyExpert1 = new ArrayList<>();//导入抽取 技术专家集合
		if(null!=record.getBusinessExpertNumber() && null!=record.getTechnologyExpertNumber()) {//手动抽取|导入抽取
			String[] expertCode = record.getExpertCodeList();
			if("3".equals(record.getOpeWay()) ) {
				if(expertCode.length != (record.getBusinessExpertNumber() + record.getTechnologyExpertNumber())) {
					return -5;//选取专家数量与商务专家和技术专家数量不相等
				}else {
					List<ExpertBean> listBusinessExpert = new ArrayList<>();//商务专家集合
					List<ExpertBean> listTechnologyExpert = new ArrayList<>();//技术专家集合
					List<ExpertBean> listTechnologyExpertAndBusinessExpert = new ArrayList<>();//既是技术专家也是商务专家集合
					List<String> businessExpert = new ArrayList<>();//商务专家分类集合
					List<String> technologyExpert = new ArrayList<>();//技术专家分类集合
					for(int i=0;i<expertCode.length;i++) {
						ExpertBean temp00 = new ExpertBean();
						temp00.setExpertCode(expertCode[i]);
						List<ExpertBean> listExpertBean = expertMapper.selectByPrimaryKey(temp00);
						if(listExpertBean.size()>0) {
							String[] majorId = listExpertBean.get(0).getListMajorBean().get(0).getMajorId().split(",");
							if(majorId.length > 1) {
								listTechnologyExpertAndBusinessExpert.add(listExpertBean.get(0));
							}else {
								int num1 = technologyExpert.size();
								int num2 = businessExpert.size();
								getListExpert(technologyExpert, businessExpert, majorId);
								if(technologyExpert.size() > num1) {
									listTechnologyExpert.add(listExpertBean.get(0));
								}else if(businessExpert.size() > num2) {
									listBusinessExpert.add(listExpertBean.get(0));
								}
							}
						}
					}
					if(listBusinessExpert.size() > record.getBusinessExpertNumber()) {
						return -8;//选取商务专家数量大于商务专家数量
					}else if(listTechnologyExpert.size() > record.getTechnologyExpertNumber()) {
						return -9;//选取技术专家数量大于商务技术数量
					}else if(listBusinessExpert.size() < record.getBusinessExpertNumber()) {
						listBusinessExpert.addAll(listTechnologyExpertAndBusinessExpert);
					}else if(listTechnologyExpert.size() < record.getTechnologyExpertNumber()) {
						listTechnologyExpert.addAll(listTechnologyExpertAndBusinessExpert);
					}
				}
			}else if("1".equals(record.getOpeWay())) {
				List<ExpertBean> listTechnologyExpertAndBusinessExpert = new ArrayList<>();//既是技术专家也是商务专家集合
				List<String> businessExpert = new ArrayList<>();//商务专家分类集合
				List<String> technologyExpert = new ArrayList<>();//技术专家分类集合
				for(int i=0;i<expertCode.length;i++) {
					ExpertBean temp00 = new ExpertBean();
					temp00.setExpertCode(expertCode[i]);
					List<ExpertBean> listExpertBean = expertMapper.selectByPrimaryKey(temp00);
					if(listExpertBean.size() > 0 && null != listExpertBean.get(0)) {
						String[] majorId = listExpertBean.get(0).getListMajorBean().get(0).getMajorId().split(",");
						if(majorId.length > 1) {
							listTechnologyExpert1.add(listExpertBean.get(0));//既是商务又是技术时，添加到技术专家集合
						}else {
							int num1 = technologyExpert.size();
							int num2 = businessExpert.size();
							getListExpert(technologyExpert, businessExpert, majorId);
							if(technologyExpert.size() > num1) {
								listTechnologyExpert1.add(listExpertBean.get(0));
							}else if(businessExpert.size() > num2) {
								listBusinessExpert1.add(listExpertBean.get(0));
							}
						}
					}else {
						return -11;//专家编码不在库中或专家为冻结状态
					}
				}
			}
		}
		record.setCreateBy((String)CurrentPrincipalHolder.getAttribute("name"));
		record.setCreateDate(new Date());
		record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
		record.setOpeDate(new Date());
		result += expertExtractResultMapper.insertSelective(record);
		ExpertExtractResultDetailBean expertExtractResultDetailBean = new ExpertExtractResultDetailBean();
		if("1".equals(record.getOpeWay())) {//导入抽取
			String[] technologyExpertTemp = new String[listTechnologyExpert1.size()];
			for(int i=0;i<listTechnologyExpert1.size();i++) {
				technologyExpertTemp[i] = listTechnologyExpert1.get(i).getExpertCode();
			}
			String[] businessExpertTemp = new String[listBusinessExpert1.size()];
			for(int i=0;i<listBusinessExpert1.size();i++) {
				businessExpertTemp[i] = listBusinessExpert1.get(i).getExpertCode();
			}
			String[] technologyExpertArr = getRandomArray(technologyExpertTemp, record.getTechnologyExpertNumber());
			String[] businessExpertArr = getRandomArray(businessExpertTemp, record.getBusinessExpertNumber());
			for(int i=0;i<technologyExpertArr.length;i++) {
				expertExtractResultDetailBean.setExtractId(extractId);
				expertExtractResultDetailBean.setId(GenerateUUID.getUUID());
				expertExtractResultDetailBean.setExpertCode(technologyExpertArr[i]);
				result += expertExtractResultDetailMapper.insertSelective(expertExtractResultDetailBean);
			}
			for(int i=0;i<businessExpertArr.length;i++) {
				expertExtractResultDetailBean.setExtractId(extractId);
				expertExtractResultDetailBean.setId(GenerateUUID.getUUID());
				expertExtractResultDetailBean.setExpertCode(businessExpertArr[i]);
				result += expertExtractResultDetailMapper.insertSelective(expertExtractResultDetailBean);
			}
		}else if("3".equals(record.getOpeWay())) {//手动抽取
			String[] expertCode = record.getExpertCodeList();
			for(int i=0;i<expertCode.length;i++) {
				expertExtractResultDetailBean.setExtractId(extractId);
				expertExtractResultDetailBean.setId(GenerateUUID.getUUID());
				expertExtractResultDetailBean.setExpertCode(expertCode[i]);
				//expertExtractResultDetailBean.setExpertName(expertBean.getExpertName());
				//expertExtractResultDetailBean.setMajorId(expertBean.getMajorQualificationType());
				//expertExtractResultDetailBean.setMajorName(expertBean.getMajorQualificationName());
				result += expertExtractResultDetailMapper.insertSelective(expertExtractResultDetailBean);
			}
		}
		if("2".equals(record.getOpeWay())) {//随机抽取
			List<String> listTechnologyExpert = new ArrayList<>();
			List<String> listBusinessExpert = new ArrayList<>();
			String[] majorQualificationType = request.getMajorQualificationType();
			for(int i=0; i<majorQualificationType.length; i++) {
				MajorBean temp1 = new MajorBean();
				temp1.setMajorId(majorQualificationType[i]);
				List<MajorBean> list1 = majorMapper.selectByPrimaryKey(temp1);
				if(!list1.get(0).getMajorName().equals("商务专家") && !list1.get(0).getMajorName().equals("技术专家")) {
					MajorBean temp2 = new MajorBean();
					temp2.setMajorId(list1.get(0).getParentId());
					List<MajorBean> list2 = majorMapper.selectByPrimaryKey(temp2);
					if(!list2.get(0).getMajorName().equals("商务专家") && !list2.get(0).getMajorName().equals("技术专家")) {
						MajorBean temp3 = new MajorBean();
						temp3.setMajorId(list2.get(0).getParentId());
						List<MajorBean> list3 = majorMapper.selectByPrimaryKey(temp3);
						if(list3.get(0).getMajorName().equals("商务专家")){
							listBusinessExpert.add(majorQualificationType[i]);
						}else {
							listTechnologyExpert.add(majorQualificationType[i]);
						}
					}else if(list2.get(0).getMajorName().equals("商务专家")){
						listBusinessExpert.add(majorQualificationType[i]);
					}else {
						listTechnologyExpert.add(majorQualificationType[i]);
					}
				}else if(list1.get(0).getMajorName().equals("商务专家")){
					listBusinessExpert.add(majorQualificationType[i]);
				}else {
					listTechnologyExpert.add(majorQualificationType[i]);
				}
			}
			ExpertBean record1 = new ExpertBean();
			if(null != record.getTechnologyExpertNumber() && 0 < record.getTechnologyExpertNumber()) {//技术专家数量
				record1.setMajorIds(listTechnologyExpert);
				//record1.setListMajorBean(listTechnologyExpert);
				//record1.setMajorQualificationType("1");
				//record1.setNumber(record.getTechnologyExpertNumber()); 
				record1.setPageSize(record.getTechnologyExpertNumber());
				//PageHelper.startPage(record.getPageNum(), record.getTechnologyExpertNumber());
				List<ExpertBean> listExpertBean = expertMapper.findExpertByRand(record1);
				for(int i=0;i<listExpertBean.size();i++) {
					ExpertBean expertBean = listExpertBean.get(i);
					expertExtractResultDetailBean.setExtractId(extractId);
					expertExtractResultDetailBean.setId(GenerateUUID.getUUID());
					expertExtractResultDetailBean.setExpertCode(expertBean.getExpertCode());
					//expertExtractResultDetailBean.setExpertName(expertBean.getExpertName());
					expertExtractResultDetailBean.setMajorId(expertBean.getMajorQualificationType());
					expertExtractResultDetailBean.setMajorName(expertBean.getMajorQualificationName());
					result += expertExtractResultDetailMapper.insertSelective(expertExtractResultDetailBean);
				}
			}
			if(null != record.getBusinessExpertNumber() && 0 < record.getBusinessExpertNumber()) {//商务专家数量
				record1.setMajorIds(listBusinessExpert);
				//record1.setListMajorBean(listBusinessExpert);
				//record1.setMajorQualificationType("2");
				//record1.setNumber(record.getBusinessExpertNumber());
				record1.setPageSize(record.getBusinessExpertNumber());
				List<ExpertBean> listExpertBean = expertMapper.findExpertByRand(record1);
				for(int i=0;i<listExpertBean.size();i++) {
					ExpertBean expertBean = listExpertBean.get(i);
					expertExtractResultDetailBean.setExtractId(extractId);
					expertExtractResultDetailBean.setId(GenerateUUID.getUUID());
					expertExtractResultDetailBean.setExpertCode(expertBean.getExpertCode());
					//expertExtractResultDetailBean.setExpertName(expertBean.getExpertName());
					expertExtractResultDetailBean.setMajorId(expertBean.getMajorQualificationType());
					expertExtractResultDetailBean.setMajorName(expertBean.getMajorQualificationName());
					result += expertExtractResultDetailMapper.insertSelective(expertExtractResultDetailBean);
				}
			}
		}
		MergeRecordBean mergeRecordBean = new MergeRecordBean();
		mergeRecordBean.setExpertExtractState("1");
		mergeRecordBean.setMergeId(request.getMergeId());
		mergeRecordBean.setDel("");
		result += mergeRecordMapper.updateByPrimaryKeySelective(mergeRecordBean);
		return result == 0 ? -1 : result;
	}
	
	public void getListExpert(List<String> listTechnologyExpert, List<String> listBusinessExpert, String[] majorQualificationType) {
		//List<String> listTechnologyExpert = new ArrayList<>();
		//List<String> listBusinessExpert = new ArrayList<>();
		//String[] majorQualificationType = request.getMajorQualificationType();
		for(int i=0; i<majorQualificationType.length; i++) {
			MajorBean temp1 = new MajorBean();
			temp1.setMajorId(majorQualificationType[i]);
			List<MajorBean> list1 = majorMapper.selectByPrimaryKey(temp1);
			if(!list1.get(0).getMajorName().equals("商务专家") && !list1.get(0).getMajorName().equals("技术专家")) {
				MajorBean temp2 = new MajorBean();
				temp2.setMajorId(list1.get(0).getParentId());
				List<MajorBean> list2 = majorMapper.selectByPrimaryKey(temp2);
				if(!list2.get(0).getMajorName().equals("商务专家") && !list2.get(0).getMajorName().equals("技术专家")) {
					MajorBean temp3 = new MajorBean();
					temp3.setMajorId(list2.get(0).getParentId());
					List<MajorBean> list3 = majorMapper.selectByPrimaryKey(temp3);
					if(list3.get(0).getMajorName().equals("商务专家")){
						listBusinessExpert.add(majorQualificationType[i]);
					}else {
						listTechnologyExpert.add(majorQualificationType[i]);
					}
				}else if(list2.get(0).getMajorName().equals("商务专家")){
					listBusinessExpert.add(majorQualificationType[i]);
				}else {
					listTechnologyExpert.add(majorQualificationType[i]);
				}
			}else if(list1.get(0).getMajorName().equals("商务专家")){
				listBusinessExpert.add(majorQualificationType[i]);
			}else {
				listTechnologyExpert.add(majorQualificationType[i]);
			}
		}
	}
	
	@Override
	public PageInfo<ExpertExtractResultBean> selectByPrimaryKey(ExpertExtractResultBean record) {
		
        List<ExpertExtractResultBean> mmNonbidExpertList = expertExtractResultMapper.selectByPrimaryKey(record);
        PageInfo<ExpertExtractResultBean> result = new PageInfo<ExpertExtractResultBean>(mmNonbidExpertList);
        return result;
	}
	
	@Override
	public int updateByPrimaryKeySelective(ExpertExtractResultBean record) {
		return expertExtractResultMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void exportExpertExtractResult(Set<String> set, HttpServletRequest request, HttpServletResponse response) {
		//获取模板
		String filePath = YycFileUtil.class.getResource("/templates/expertExtractResultTemplate.xlsx").getPath();
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
		for (String str : set) {
			List<Map<String, Object>> supplierlist = expertExtractResultMapper.getExpertExtractResultList(str);
			if(xssfWorkbook != null){
				//循环数据
				for (int i = 0; i < supplierlist.size(); i++) {
					XSSFRow row = sheetAt.getRow(index + i);//获取行
					if(row == null){
						row = sheetAt.createRow(index + i);
					}
					Map<String,Object> map = supplierlist.get(i);			
					getcell(row,0).setCellValue(YycStringUtils.convertObjToStr(map.get("extractId")));//抽取记录编码
					String batchNum = YycStringUtils.convertObjToStr(map.get("matplanBatchNum"));
					String batchName = YycStringUtils.convertObjToStr(map.get("matplanBatchName"));
					if(!StringUtils.isNotBlank(batchNum)) {
						batchNum = YycStringUtils.convertObjToStr(map.get("nonMatplanBatchNum"));
						batchName = YycStringUtils.convertObjToStr(map.get("nonMatplanBatchName"));
					}
					getcell(row,1).setCellValue(batchNum);//批次编码
					getcell(row,2).setCellValue(batchName);//批次名称
					getcell(row,3).setCellValue(YycStringUtils.convertObjToStr(map.get("opeDate")));//操作时间
					getcell(row,4).setCellValue(YycStringUtils.convertObjToStr(map.get("opeBy")));//操作人
					getcell(row,5).setCellValue(YycStringUtils.convertObjToStr(map.get("opeWay")));//操作方式
					getcell(row,6).setCellValue(YycStringUtils.convertObjToStr(map.get("expertCode")));//专家编码
					getcell(row,7).setCellValue(YycStringUtils.convertObjToStr(map.get("expertName")));//专家姓名
					getcell(row,8).setCellValue(YycStringUtils.convertObjToStr(map.get("majorQualificationName")));//专家专业名称
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
	public List<String> importExpertExtract(List<Map<Integer, Object>> readExcel) {
		List<String> list = new ArrayList<>();
		for (Map<Integer, Object> map : readExcel) {
			// 当前行 对象。
			ErpSupplierBean bean = new ErpSupplierBean();
			for (Entry<Integer, Object> entry : map.entrySet()) {
				// 遍历枚举 ， 根据对应列 下标 获取对应的set方法名称
				for (ImportExpertExtractBeanEnum beanEnum : ImportExpertExtractBeanEnum.values()) {
					if (beanEnum.getIndex() == entry.getKey()) {
						try {
							// 根据set方法名称 和实体获取 对应Method。
							Method methods = bean.getClass().getDeclaredMethod(beanEnum.getSetMethod(), String.class);
							// 根据method 对象和实体反射填充 对象属性值。
							methods.invoke(bean, entry.getValue());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			if(null != bean.getSupplierId() && !"".equals(bean.getSupplierId())) {
				list.add(bean.getSupplierId());
			}
		}
		return list;
	}
	
	public String[] getRandomArray(String[] paramArray,int count){
        if(paramArray.length<count){
            return paramArray;
        }
        String[] newArray=new String[count];
        Random random= new Random();
        int temp=0;//接收产生的随机数
        List<Integer> list=new ArrayList<Integer>();
        for(int i=1;i<=count;i++){
            temp=random.nextInt(paramArray.length);//将产生的随机数作为被抽数组的索引
            if(!(list.contains(temp))){
                newArray[i-1]=paramArray[temp];
                list.add(temp); 
            }
            else{
                i--;
            }
        }
        return newArray;
    }
	
	public String[] toArrayByObject(Object[] param) {
		String[] array = new String[param.length];
		for(int i=0; i<param.length; i++) {
			array[i] = (String)param[i];
		}
		return array;
	}
}
