package com.yyc.oper.nobid.service.mat.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

import com.github.pagehelper.PageInfo;
import com.yyc.brace.base.principal.CurrentPrincipalHolder;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.oper.nobid.base.ArrayMatplan;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.batch.BatchmanageBean;
import com.yyc.oper.nobid.mapper.BatchmanageMapper;
import com.yyc.oper.nobid.mapper.InvitationSupplierMapper;
import com.yyc.oper.nobid.mapper.MatMapper;
import com.yyc.oper.nobid.mapper.MatplanMapper;
import com.yyc.oper.nobid.mapper.MatplanMatMapper;
import com.yyc.oper.nobid.mapper.MergeRecordMapper;
import com.yyc.oper.nobid.mapper.SupplierMapper;
import com.yyc.oper.nobid.mat.MatBean;
import com.yyc.oper.nobid.mat.MatplanAndMatResponse;
import com.yyc.oper.nobid.mat.MatplanBean;
import com.yyc.oper.nobid.mat.MatplanBeanEnum;
import com.yyc.oper.nobid.mat.MatplanMatBean;
import com.yyc.oper.nobid.mat.MatplanRequest;
import com.yyc.oper.nobid.mat.MergeRecorRequest;
import com.yyc.oper.nobid.merge.MergeRecordBean;
import com.yyc.oper.nobid.service.mat.IMatplanService;
import com.yyc.oper.nobid.supplier.InvitationSupplierBean;
import com.yyc.oper.nobid.supplier.InvitationSupplierImportExcel;
import com.yyc.oper.nobid.supplier.SupplierBean;
import com.yyc.oper.nobid.util.GenerateUUID;
import com.yyc.oper.nobid.util.YycFileUtil;
import com.yyc.oper.nobid.util.YycStringUtils;

@Service
public class MatplanServiceImpl implements IMatplanService{
	
	@Autowired
	private MatplanMapper matplanMapper;
	
	@Autowired
	private BatchmanageMapper batchmanageMapper;
	
	@Autowired
	private MergeRecordMapper mergeRecordMapper;
	
	@Autowired
	private MatplanMatMapper matplanMatMapper;
	
	@Autowired
	private InvitationSupplierMapper invitationSupplierMapper;
	
	@Autowired
	private SupplierMapper supplierMapper;
	
	@Autowired
	private MatMapper matMapper;
	
	@Override
	public int deleteByPrimaryKey(String matplanId) {
		return matplanMapper.deleteByPrimaryKey(matplanId);
	}
	
	@Override
	public int insertSelective(MatplanRequest matplanRequest) {
		int result = 0;
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		//采购方式单一来源时，推荐供应商小余等于1个
		if("03".equals(record.getPurchaseWay())) {
			if(null != matplanRequest.getListInvitationSupplierBean() && matplanRequest.getListInvitationSupplierBean().size()>1) {
				return -3;
			}
		}
		String uuid = GenerateUUID.getUUID();
		record.setMatplanId(uuid);
		record.setState("1");//1待提交
		record.setMatplanSource("01");//01手工录入 02Excel导入
		record.setCreateBy((String)CurrentPrincipalHolder.getAttribute("name"));
		Date currentDate = new Date();
		record.setCreateTime(currentDate);
		record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
		record.setOpeTime(currentDate);
		result += matplanMapper.insertSelective(record);
		//新增物料
		List<MatplanMatBean> listMatBean = record.getListMatplanMatBean();
		if(null != listMatBean && listMatBean.size()>0) {
			for(int i=0;i<listMatBean.size();i++) {
				listMatBean.get(i).setMatplanId(uuid);
				listMatBean.get(i).setId(GenerateUUID.getUUID());
			}
			result += matplanMatMapper.insert(listMatBean);
		}
		//新增邀请供应商
		List<InvitationSupplierBean> listInvitationSupplierBean = matplanRequest.getListInvitationSupplierBean();
		if(null != listInvitationSupplierBean && listInvitationSupplierBean.size()>0) {
			for(int i=0;i<listInvitationSupplierBean.size();i++) {
				//校验供应商手机号码
				/**String supplierPhone = listInvitationSupplierBean.get(i).getSupplierPhone();
				if(null != supplierPhone && !"".equals(supplierPhone) && supplierPhone.length()==11) {
					if(!ValidatorPhoneUtil.isPhone(supplierPhone)) {
						return -2;
					}
				}**/
				if("".equals(listInvitationSupplierBean.get(i).getSupplierId()) || "".equals(listInvitationSupplierBean.get(i).getSupplierPhone())) {
					continue;
				}
				listInvitationSupplierBean.get(i).setMatplanId(uuid);
				listInvitationSupplierBean.get(i).setInvitationId(GenerateUUID.getUUID());
				listInvitationSupplierBean.get(i).setIsMat("0");//0物资，1非物资
			}
			invitationSupplierMapper.insert(listInvitationSupplierBean);
		}
		return result == 0 ? -1 : result;
	}
	
	@Override
	public PageInfo<MatplanBean> selectByPrimaryKey(MatplanBean record) {
		
        List<MatplanBean> mmNonbidExpertList = matplanMapper.selectByPrimaryKey(record);
        return new PageInfo<>(mmNonbidExpertList);
	}

	@Override
	public PageInfo<MatplanBean> selectMatplanAndMat(MatplanBean record) {
		
        List<MatplanBean> listMatplanBean = matplanMapper.selectMatplanAndMat(record);
        for(int i=0;i<listMatplanBean.size();i++) {
        	InvitationSupplierBean supplier = new InvitationSupplierBean();
            supplier.setMatplanId(listMatplanBean.get(i).getMatplanId());
            supplier.setIsMat("0");//0物资，1非物资
            List<InvitationSupplierBean> listInvitationSupplierBean = invitationSupplierMapper.selectByPrimaryKey(supplier);
            listMatplanBean.get(i).setListInvitationSupplierBean(listInvitationSupplierBean);
        }
        return new PageInfo<>(listMatplanBean);
	}
	
	@Override
	public int updateByPrimaryKeySelective(MatplanRequest matplanRequest) {
		int result = 0;
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		//修改物料
		List<MatplanMatBean> listMatBean = matplanRequest.getListMatplanMatBean();
		if(null != listMatBean && listMatBean.size()>0) {
			result += matplanMatMapper.deleteByMatplanId(record.getMatplanId());
			for(int i=0;i<listMatBean.size();i++) {
				listMatBean.get(i).setMatplanId(record.getMatplanId());
				listMatBean.get(i).setId(GenerateUUID.getUUID());
			}
			result += matplanMatMapper.insert(listMatBean);
		}
		//修改邀请供应商
		List<InvitationSupplierBean> listInvitationSupplierBean = matplanRequest.getListInvitationSupplierBean();
		if(null != listInvitationSupplierBean && listInvitationSupplierBean.size()>0) {
			result += invitationSupplierMapper.deleteByMatplanId(record.getMatplanId());
			for(int i=0;i<listInvitationSupplierBean.size();i++) {
				if("".equals(listInvitationSupplierBean.get(i).getSupplierId()) || "".equals(listInvitationSupplierBean.get(i).getSupplierPhone())) {
					continue;
				}
				listInvitationSupplierBean.get(i).setMatplanId(record.getMatplanId());
				listInvitationSupplierBean.get(i).setInvitationId(GenerateUUID.getUUID());
				listInvitationSupplierBean.get(i).setIsMat("0");//0物资，1非物资
			}
			invitationSupplierMapper.insert(listInvitationSupplierBean);
		}
		record.setState("1");//1待提交
		record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
		record.setOpeTime(new Date());
		result += matplanMapper.updateByPrimaryKeySelective(record);
		return result == 0 ? -1 : result;
	}

	@Override
	public int updateByPrimaryKey(String[] matplanIds, String mergeId, String bagNum, String qualification, String del) {
		int result = 0;
		MergeRecordBean record1 = new MergeRecordBean();
		record1.setBagNum(bagNum);
		record1.setQualification(qualification);
		record1.setDel(del);
		MatplanBean record2 = new MatplanBean();
		if(StringUtils.isNotBlank(mergeId)) {
			record1.setMergeId(mergeId);
			record2.setMergeId(mergeId);
			record1.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record1.setOpeTime(new Date());
			result = result + mergeRecordMapper.updateByPrimaryKeySelective(record1);//0/1
		}else {
			String uuId = GenerateUUID.getUUID();
			record1.setMergeId(uuId);
			record2.setMergeId(uuId);
			record1.setCreateBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record1.setCreateTime(new Date());
			record1.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record1.setOpeTime(new Date());
			result = result + mergeRecordMapper.insertSelective(record1);//-1/1
		}
		
		for(int i=0;i<matplanIds.length;i++) {
			record2.setMatplanId(matplanIds[i]);
			result = result + matplanMapper.updateByPrimaryKeySelective(record2);//0/1
		}
		return result==matplanIds.length+1 ? result : -1 ;
	}

	@Override
	public ResultMessage importExcel(List<Map<Integer, Object>> readExcel) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"");
		String result = "";
		Set<MatplanBean> matplanBeanHashSet = new HashSet<>();
		Set<MatplanMatBean> matplanMatBeanHashSet = new HashSet<>();
		Set<InvitationSupplierImportExcel> invitationSupplierImportExcelHashSet = new HashSet<>();
		boolean bool = true;
		for (int i=0; i<readExcel.size(); i++) {
			Map<Integer, Object> map = readExcel.get(i);
			MatplanBean matplanBean = new MatplanBean();
			MatplanMatBean matplanMatBean = new MatplanMatBean();
			InvitationSupplierImportExcel invitationSupplierImportExcel = new InvitationSupplierImportExcel();
			// 当前行 对象。
			for (Entry<Integer, Object> entry : map.entrySet()) {
				// 遍历枚举 ， 根据对应列 下标 获取对应的set方法名称
				for (MatplanBeanEnum beanEnum : MatplanBeanEnum.values()) {
					if (beanEnum.getIndex() == entry.getKey()) {
						try {
							if(12 >= beanEnum.getIndex() && 1 <= beanEnum.getIndex()) {
								// 根据set方法名称 和实体获取 对应Method。
								Method methods = matplanBean.getClass().getDeclaredMethod(beanEnum.getSetMethod(), String.class);
								// 根据method 对象和实体反射填充 对象属性值。
								methods.invoke(matplanBean, entry.getValue());
							}else if(18 >= beanEnum.getIndex() && 13 <= beanEnum.getIndex()) {
								Method methods = invitationSupplierImportExcel.getClass().getDeclaredMethod(beanEnum.getSetMethod(), String.class);
								methods.invoke(invitationSupplierImportExcel, entry.getValue());
							}else if(19 <= beanEnum.getIndex()) {
								if(23 == beanEnum.getIndex()) {
									SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");  
									String d = (String)entry.getValue();
									if(d.length()==10 && d.split("-").length==3) {
										Date date = format.parse(d);
										matplanMatBean.setDeliveryTime(date);
									}else {
										result += "第"+ (i+1) +"行 交货时间，数据格式不对！";
									}
								}else {
									Method methods = matplanMatBean.getClass().getDeclaredMethod(beanEnum.getSetMethod(), String.class);
									methods.invoke(matplanMatBean, entry.getValue());
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			//采购方式
			if(!"01".equals(matplanBean.getPurchaseWay()) && !"02".equals(matplanBean.getPurchaseWay()) && !"03".equals(matplanBean.getPurchaseWay())) {
				result += "第"+ (i+1) +"行 采购方式，数据格式不对！";
				bool = false;
			}
			//批次
			if(StringUtils.isNotBlank(matplanBean.getBatchNum())) {
				BatchmanageBean temp = new BatchmanageBean();
				temp.setBatchNum(matplanBean.getBatchNum());
				List<BatchmanageBean> list = batchmanageMapper.selectByPrimaryKey(temp);
				if(null==list || list.size()==0 || null==list.get(0)) {
					result += "第"+ (i+1) +"行 批次，不在库中！";
					bool = false;
				}
			}
			//推荐供应商
			if(StringUtils.isNotBlank(invitationSupplierImportExcel.getSupplierId1())) {
				SupplierBean temp = new SupplierBean();
				temp.setSupplierName(invitationSupplierImportExcel.getSupplierId1());
				List<SupplierBean> list = supplierMapper.selectBySupplierBean(temp);
				if(null==list || list.size()==0 || null==list.get(0)) {
					result += "第"+ (i+1) +"行 推荐供应商1，不在库中！";
					bool = false;
				}
			}
			if(StringUtils.isNotBlank(invitationSupplierImportExcel.getSupplierId2())) {
				SupplierBean temp = new SupplierBean();
				temp.setSupplierName(invitationSupplierImportExcel.getSupplierId2());
				List<SupplierBean> list = supplierMapper.selectByPrimaryKey(temp);
				if(null==list || list.size()==0 || null==list.get(0)) {
					result += "第"+ (i+1) +"行 推荐供应商2，不在库中！";
					bool = false;
				}
			}
			if(StringUtils.isNotBlank(invitationSupplierImportExcel.getSupplierId3())) {
				SupplierBean temp = new SupplierBean();
				temp.setSupplierName(invitationSupplierImportExcel.getSupplierId3());
				List<SupplierBean> list = supplierMapper.selectByPrimaryKey(temp);
				if(null==list || list.size()==0 || null==list.get(0)) {
					result += "第"+ (i+1) +"行 推荐供应商3，不在库中！";
					bool = false;
				}
			}
			//物料编码
			if(StringUtils.isNotBlank(matplanMatBean.getMatId())) {
				MatBean temp = new MatBean();
				temp.setMatId(matplanMatBean.getMatId());
				List<MatBean> list = matMapper.selectByPrimaryKey(temp);
				if(null==list || list.size()==0 || null==list.get(0)) {
					result += "第"+ (i+1) +"行 物料编码，不在库中！";
					bool = false;
				}else {
					matplanMatBean.setSmallClassId(list.get(0).getSmallCatagory());
					matplanMatBean.setMiddleClassId(list.get(0).getMediumCatagory());
					matplanMatBean.setLargeClassId(list.get(0).getBigCatagory());
					matplanMatBean.setMatUnit(list.get(0).getUnitName());
				}
			}
			//物料数量和单价
			if(StringUtils.isNotBlank(matplanMatBean.getMatNum()) && StringUtils.isNotBlank(matplanMatBean.getEstimateUnitPrice())) {
				double num = Double.parseDouble(matplanMatBean.getMatNum()) * Double.parseDouble(matplanMatBean.getEstimateUnitPrice());
				matplanMatBean.setEstimateTotalPrice(Double.toString(num));
			}
			
			//根据序号、包号、项目编号、批次号 在库中找到记录，则不导入
			MatplanBean temp00 = new MatplanBean();
			temp00.setSerialNum(matplanBean.getSerialNum());
			temp00.setPackageNum(matplanBean.getPackageNum());
			temp00.setProjectNum(matplanBean.getProjectNum());
			temp00.setBatchNum(matplanBean.getBatchNum());
			List<MatplanBean> list00 = matplanMapper.selectByPrimaryKey(temp00);
			if(list00 != null && list00.size()>0) {
				result += "第"+ (i+1) +"行 采购计划，在库中已存在！";
				bool = false;
				continue;
			}
			//Excel中序号、包号、项目编号、批次号  相同就是一个采购计划
			String matplanId = null;
			for(MatplanBean temp : matplanBeanHashSet) {
				if(temp.getSerialNum().equals(matplanBean.getSerialNum()) && temp.getPackageNum().equals(matplanBean.getPackageNum())
					&& temp.getProjectNum().equals(matplanBean.getProjectNum()) && temp.getBatchNum().equals(matplanBean.getBatchNum())) {
					matplanId = temp.getMatplanId();
					break;
				}
			}
			if(matplanId == null) {
				String uuId = GenerateUUID.getUUID();
				matplanBean.setMatplanId(uuId);
				matplanBean.setState("1");
				matplanBean.setCreateBy((String)CurrentPrincipalHolder.getAttribute("name"));
				Date currentDate = new Date();
				matplanBean.setCreateTime(currentDate);
				matplanBean.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
				matplanBean.setOpeTime(currentDate);
				matplanMatBean.setId(GenerateUUID.getUUID());
				matplanMatBean.setMatplanId(uuId);
				invitationSupplierImportExcel.setInvitationId(GenerateUUID.getUUID());
				invitationSupplierImportExcel.setMatplanId(uuId);
				matplanBeanHashSet.add(matplanBean);
				matplanMatBeanHashSet.add(matplanMatBean);
				invitationSupplierImportExcelHashSet.add(invitationSupplierImportExcel);
			}else {
				matplanMatBean.setId(GenerateUUID.getUUID());
				matplanMatBean.setMatplanId(matplanId);
				matplanMatBeanHashSet.add(matplanMatBean);
			}
		}
		
		if(bool) {
			int num = 0;
			if(matplanBeanHashSet.size() > 0) {
				List<MatplanBean> list = new ArrayList<MatplanBean>(matplanBeanHashSet);
				num += matplanMapper.insert(list);
			}
			if(matplanMatBeanHashSet.size() > 0) {
				List<MatplanMatBean> list = new ArrayList<MatplanMatBean>(matplanMatBeanHashSet);
				matplanMatMapper.insert(list);
			}
			if(invitationSupplierImportExcelHashSet.size() > 0) {
				for(InvitationSupplierImportExcel bean : invitationSupplierImportExcelHashSet) {
					List<InvitationSupplierBean> list = new ArrayList<>();
					if(StringUtils.isNotEmpty(bean.getSupplierId1())){
						InvitationSupplierBean invitationSupplierBean = new InvitationSupplierBean();
						invitationSupplierBean.setInvitationId(GenerateUUID.getUUID());
						invitationSupplierBean.setMatplanId(bean.getMatplanId());
						invitationSupplierBean.setSupplierId(bean.getSupplierId1());
						invitationSupplierBean.setSupplierPhone(bean.getSupplierPhone1());
						list.add(invitationSupplierBean);
					}
					if(StringUtils.isNotEmpty(bean.getSupplierId2())){
						InvitationSupplierBean invitationSupplierBean = new InvitationSupplierBean();
						invitationSupplierBean.setInvitationId(GenerateUUID.getUUID());
						invitationSupplierBean.setMatplanId(bean.getMatplanId());
						invitationSupplierBean.setSupplierId(bean.getSupplierId2());
						invitationSupplierBean.setSupplierPhone(bean.getSupplierPhone2());
						list.add(invitationSupplierBean);
					}
					if(StringUtils.isNotEmpty(bean.getSupplierId3())){
						InvitationSupplierBean invitationSupplierBean = new InvitationSupplierBean();
						invitationSupplierBean.setInvitationId(GenerateUUID.getUUID());
						invitationSupplierBean.setMatplanId(bean.getMatplanId());
						invitationSupplierBean.setSupplierId(bean.getSupplierId3());
						invitationSupplierBean.setSupplierPhone(bean.getSupplierPhone3());
						list.add(invitationSupplierBean);
					}
					invitationSupplierMapper.insert(list);
				}
			}
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setMsg("成功：导入成功" + num + "条！");
			return resultMessage;
		}else {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("失败：" + result);
			return resultMessage;
		}
		
	}

	@Override
	public String deleteByMatplanIds(ArrayMatplan matplanIds) {
		int result = 0;
		MatplanBean record = new MatplanBean();
		record.setDel("1");
		String[] array = matplanIds.getMatplanIds();
		for(int i=0; i<array.length; i++) {
			record.setMatplanId(array[i]);
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			result += matplanMapper.updateByPrimaryKeySelective(record);
		}
		return "删除成功" + result + "条！";
	}

	@Override
	public String matplanSubmitByMatplanIds(ArrayMatplan matplanIds) {
		int result = 0;
		MatplanBean record = new MatplanBean();
		record.setState("2");//2待审核
		String[] array = matplanIds.getMatplanIds();
		for(int i=0; i<array.length; i++) {
			record.setMatplanId(array[i]);
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			result += matplanMapper.updateByPrimaryKeySelective(record);
		}
		return "提交成功" + result + "条！";
	}

	@Override
	public String matplanExamineByMatplanIds(ArrayMatplan matplanIds) {
		int result = 0;
		MatplanBean record = new MatplanBean();
		record.setState("3");//3待确认
		String[] array = matplanIds.getMatplanIds();
		for(int i=0; i<array.length; i++) {
			record.setMatplanId(array[i]);
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			result += matplanMapper.updateByPrimaryKeySelective(record);
		}
		return "审核成功" + result + "条！";
	}
	
	@Override
	public String matplanconfirmByMatplanIds(ArrayMatplan matplanIds) {
		int result = 0;
		MatplanBean record = new MatplanBean();
		record.setState("4");//4已确认
		String[] array = matplanIds.getMatplanIds();
		for(int i=0; i<array.length; i++) {
			record.setMatplanId(array[i]);
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			result += matplanMapper.updateByPrimaryKeySelective(record);
		}
		return "确认成功" + result + "条！";
	}

	@Override
	public String matplanRejectByMatplanIds(ArrayMatplan matplanIds) {
		int result = 0;
		MatplanBean record = new MatplanBean();
		record.setState("5");//4已驳回
		String[] array = matplanIds.getMatplanIds();
		for(int i=0; i<array.length; i++) {
			record.setMatplanId(array[i]);
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			result += matplanMapper.updateByPrimaryKeySelective(record);
		}
		return "驳回成功" + result + "条！";
	}

	@Override
	public int matplanMerge(MergeRecorRequest matplanRequest) {
		int result = 0;
		if("03".equals(matplanRequest.getPurchaseWay()) && Integer.parseInt(matplanRequest.getDemandSupplierNum()) > 1){
			return -3;
		}
		String uuId = GenerateUUID.getUUID();
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		MergeRecordBean mergeRecordBean = new MergeRecordBean();
		mergeRecordBean.setMergeId(uuId);
		mergeRecordBean.setBatchNum(matplanRequest.getBatchNum());
		mergeRecordBean.setPackageNum(matplanRequest.getPackageNum());
		mergeRecordBean.setPurchaseSchmeId(matplanRequest.getPurchaseSchmeId());
		mergeRecordBean.setPurchaseSchmeName(matplanRequest.getPurchaseSchmeName());
		mergeRecordBean.setProjectId(matplanRequest.getProjectNum());
		mergeRecordBean.setProjectName(matplanRequest.getProjectName());
		mergeRecordBean.setPurchaseStrategy(matplanRequest.getPurchaseStrategy());
		mergeRecordBean.setPurchaseWay(matplanRequest.getPurchaseWay());
		mergeRecordBean.setQualification(matplanRequest.getQualification());
		mergeRecordBean.setDemandSupplierNum(matplanRequest.getDemandSupplierNum());
		mergeRecordBean.setState("1");//采购方案列表
		Date currentDate = new Date();
		mergeRecordBean.setCreateBy((String)CurrentPrincipalHolder.getAttribute("name"));
		mergeRecordBean.setCreateTime(currentDate);
		mergeRecordBean.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
		mergeRecordBean.setOpeTime(currentDate);
		result += mergeRecordMapper.insertSelective(mergeRecordBean);
		MatplanBean matplanBean = new MatplanBean();
		matplanBean.setMergeId(uuId);
		matplanBean.setIsMerge("1");//是否合包 1是
		matplanBean.setMergeState("1");//合包状态 1已合包
		String[] matplanIds = record.getMatplanIds();
		for(int i=0; i<matplanIds.length; i++) {
			matplanBean.setMatplanId(matplanIds[i]);
			matplanBean.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			matplanBean.setOpeTime(new Date());
			result += matplanMapper.updateByPrimaryKeySelective(matplanBean);
		}
		return result;
	}

	@Override
	public int matplanUnraveling(ArrayMatplan matplanIds) {
		int result = 0;
		MatplanBean record = new MatplanBean();
		String[] array = matplanIds.getMatplanIds();
		for(int i=0; i<array.length; i++) {
			record.setMatplanId(array[i]);
			List<MatplanBean> list = matplanMapper.selectByPrimaryKey(record);
			MatplanBean temp = list.get(0);
			if(null!=temp && i==0) {
				/**MergeRecordBean mergeRecordBean = new MergeRecordBean();
				mergeRecordBean.setMergeId(temp.getMergeId());
				mergeRecordBean.setDel("1");//1删除
				mergeRecordBean.setPurchaseSchmeId("");
				mergeRecordBean.setPurchaseSchmeName("");
				mergeRecordBean.setQualification("");
				mergeRecordBean.setDemandSupplierNum("");
				result += mergeRecordMapper.updateByPrimaryKeySelective(mergeRecordBean);**/
				mergeRecordMapper.deleteByPrimaryKey(temp.getMergeId());
			}
			record.setMergeId("");
			record.setIsMerge("0");//是否合包 0否
			record.setMergeState("0");//合包状态 0未合包
			record.setPackageNum("");
			record.setPackageName("");
			record.setProjectNum("");
			record.setProjectName("");
			record.setPurchaseStrategy("");
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			result += matplanMapper.updateByPrimaryKeySelective(record);
		}
		return result;
	}

	@Override
	public int matplanNoMerge(MergeRecorRequest matplanRequest) {
		int result = 0;
		String uuId = GenerateUUID.getUUID();
		MatplanBean record = new MatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		MergeRecordBean mergeRecordBean = new MergeRecordBean();
		mergeRecordBean.setMergeId(uuId);
		mergeRecordBean.setBatchNum(matplanRequest.getBatchNum());
		mergeRecordBean.setPackageNum(matplanRequest.getPackageNum());
		mergeRecordBean.setPurchaseSchmeId(matplanRequest.getPurchaseSchmeId());
		mergeRecordBean.setPurchaseSchmeName(matplanRequest.getPurchaseSchmeName());
		mergeRecordBean.setBatchNum(matplanRequest.getBatchNum());
		mergeRecordBean.setProjectId(matplanRequest.getProjectNum());
		mergeRecordBean.setProjectName(matplanRequest.getProjectName());
		mergeRecordBean.setPurchaseStrategy(matplanRequest.getPurchaseStrategy());
		mergeRecordBean.setPurchaseWay(matplanRequest.getPurchaseWay());
		mergeRecordBean.setQualification(matplanRequest.getQualification());
		mergeRecordBean.setDemandSupplierNum(matplanRequest.getDemandSupplierNum());
		mergeRecordBean.setState("1");//采购方案列表
		Date currentDate = new Date();
		mergeRecordBean.setCreateBy((String)CurrentPrincipalHolder.getAttribute("name"));
		mergeRecordBean.setCreateTime(currentDate);
		mergeRecordBean.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
		mergeRecordBean.setOpeTime(currentDate);
		result += mergeRecordMapper.insertSelective(mergeRecordBean);
		MatplanBean matplanBean = new MatplanBean();
		matplanBean.setMergeId(uuId);
		matplanBean.setIsMerge("0");//是否合包 0否
		matplanBean.setMergeState("1");//合包状态 1已合包
		String[] matplanIds = record.getMatplanIds();
		for(int i=0; i<matplanIds.length; i++) {
			matplanBean.setMatplanId(matplanIds[i]);
			matplanBean.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			matplanBean.setOpeTime(new Date());
			result += matplanMapper.updateByPrimaryKeySelective(matplanBean);
		}
		return result;
	}

	@Override
	public int updateMergeStateByMatplanId(ArrayMatplan matplanIds) {
		int result = 0;
		MatplanBean record = new MatplanBean();
		record.setMergeState("4");//4合包已确认
		String[] array = matplanIds.getMatplanIds();
		for(int i=0; i<array.length; i++) {
			record.setMatplanId(array[i]);
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			result += matplanMapper.updateByPrimaryKeySelective(record);
		}
		return result;
	}

	@Override
	public int invitationSupplierByMatplanId(MatplanRequest matplanRequest) {
		List<InvitationSupplierBean> list = matplanRequest.getListInvitationSupplierBean();
		return invitationSupplierMapper.insert(list);
	}

	@Override
	public ResultMessage editMergeByMatplanIds(ArrayMatplan matplanIds) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.OK,"编辑成功");
		MergeRecorRequest mergeRecorRequest = new MergeRecorRequest();
		//判断是否可以合包  1判断批次编号、采购方式是否相同
		MatplanBean record = new MatplanBean();
		record.setMatplanIds(matplanIds.getMatplanIds());
		List<MatplanBean> listMatplanBean = matplanMapper.selectByPrimaryKey(record);
		List<MatplanBean> listTempMatplan = new ArrayList<MatplanBean>();
		boolean bool1 = true;
		continueOut:
		for(int i=0; i<listMatplanBean.size(); i++) {
			MatplanBean temp2 = listMatplanBean.get(i);
			for(int j=0;j<listTempMatplan.size();j++) {
				if(!temp2.getBatchNum().equals(listTempMatplan.get(j).getBatchNum()) || !temp2.getPurchaseWay().equals(listTempMatplan.get(j).getPurchaseWay())) {
					bool1 = false;
					break continueOut;
				}
			}
			if(bool1) {
				listTempMatplan.add(temp2);
			}
		}
		
		if(bool1 && listMatplanBean.size()>0) {
			MatplanBean temp1 = listMatplanBean.get(0);
			BatchmanageBean batchmanage = new BatchmanageBean();
			batchmanage.setBatchNum(temp1.getBatchNum());
			List<BatchmanageBean> listBatchmanageBean = batchmanageMapper.selectByPrimaryKey(batchmanage);
			if(null != listBatchmanageBean && listBatchmanageBean.size()>0) {
				mergeRecorRequest.setBatchName(listBatchmanageBean.get(0).getBatchName());
			}
			mergeRecorRequest.setBatchNum(temp1.getBatchNum());
			mergeRecorRequest.setPurchaseWay(temp1.getPurchaseWay());
			//不合包填值
			if(matplanIds.getMatplanIds().length==1) {
				mergeRecorRequest.setProjectNum(temp1.getProjectNum());
				mergeRecorRequest.setProjectName(temp1.getProjectName());
				mergeRecorRequest.setPurchaseStrategy(temp1.getPurchaseStrategy());
				mergeRecorRequest.setPackageNum(temp1.getPackageNum());
				mergeRecorRequest.setPurchaseNum(temp1.getPurchaseNum());
				mergeRecorRequest.setFundSource(temp1.getFundSource());
				mergeRecorRequest.setApprovalNum(temp1.getApprovalNum());
				mergeRecorRequest.setDemandCompany(temp1.getDemandCompany());
			}
		}else {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("只有批次和采购方式相同的采购计划才能合包");
			return resultMessage;//批次和采购方式不同
		}
		
		//判断是否可以合包  2判断邀请供应商是否小余等于5个
		List<InvitationSupplierBean> list = new ArrayList<InvitationSupplierBean>();
		InvitationSupplierBean invitationSupplierBean = new InvitationSupplierBean();
		invitationSupplierBean.setMatplanIds(matplanIds.getMatplanIds());
		invitationSupplierBean.setIsMat("0");//0物资，1非物资
		List<InvitationSupplierBean> listInvitationSupplierBean = invitationSupplierMapper.selectByPrimaryKey(invitationSupplierBean);
		for(int i=0; i<listInvitationSupplierBean.size(); i++) {
			InvitationSupplierBean temp2 = listInvitationSupplierBean.get(i);
			boolean bool = true;
			for(int j=0;j<list.size();j++) {
				if(temp2.getSupplierId().equals(list.get(j).getSupplierId())) {
					bool = false;
					break;
				}
			}
			if(bool) {
				list.add(temp2);
			}
		}
		//采购方式单一来源：推荐供应商只能是1个
		if((listTempMatplan.size()>0 && !"03".equals(listTempMatplan.get(0).getPurchaseWay()))
			|| (list.size() <= 1 && listTempMatplan.size()>0 && "03".equals(listTempMatplan.get(0).getPurchaseWay()))) {
			mergeRecorRequest.setListInvitationSupplierBean(list);
			MatplanMatBean matplanMatBean = new MatplanMatBean();
			matplanMatBean.setMatplanIds(matplanIds.getMatplanIds());
			List<MatplanMatBean> listMatplanMatBean = matplanMatMapper.selectByPrimaryKey(matplanMatBean);
			mergeRecorRequest.setListMatplanMatBean(listMatplanMatBean);
		}else {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg("采购方式为单一来源时，推荐供应商最多1个；采购方式为其他时，推荐供应商最多5个");
			return resultMessage;//推荐供应商数量和采购方式不匹配
		}
		resultMessage.setData(mergeRecorRequest);
		return resultMessage;
	}

	@Override
	public void exportMatplanAndMat(List<MatplanAndMatResponse> list, HttpServletRequest request,
			HttpServletResponse response) {

		//获取模板
		InputStream ins = YycFileUtil.class.getResourceAsStream("/templates/exportMatplanAndMat.xlsx");
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
		int index = 1;
		//根据批次和包号循环查询,进行合并单元格
		if(xssfWorkbook != null){
			//循环数据
			for (int i = 0; i < list.size(); i++) {
				MatplanAndMatResponse map = list.get(i);
				if(null != map.getListMatplanMatBean() && map.getListMatplanMatBean().size() > 0) {
					for(int j=0; j<map.getListMatplanMatBean().size(); j++) {
						MatplanMatBean matplanMat = map.getListMatplanMatBean().get(j);
						XSSFRow row = sheetAt.getRow(index + j);//获取行
						if(row == null){
							row = sheetAt.createRow(index + i);
						}
						getcell(row,0).setCellValue(YycStringUtils.convertObjToStr(map.getSerialNum()));//序号
						getcell(row,1).setCellValue(YycStringUtils.convertObjToStr(map.getPackageNum()));//包号
						getcell(row,2).setCellValue(YycStringUtils.convertObjToStr(map.getPackageName()));//包名称
						getcell(row,3).setCellValue(YycStringUtils.convertObjToStr(map.getBatchNum()));//批次
						getcell(row,4).setCellValue(YycStringUtils.convertObjToStr(map.getProjectNum()));//项目编号
						getcell(row,5).setCellValue(YycStringUtils.convertObjToStr(map.getProjectName()));//项目名称
						getcell(row,6).setCellValue(YycStringUtils.convertObjToStr(map.getPurchaseWay()));//采购方式
						getcell(row,7).setCellValue(YycStringUtils.convertObjToStr(map.getPurchaseStrategy()));//采购策略
						getcell(row,8).setCellValue(YycStringUtils.convertObjToStr(map.getDemandCompany()));//需求单位
						getcell(row,9).setCellValue(YycStringUtils.convertObjToStr(map.getPurchaseNum()));//采购申请号
						getcell(row,10).setCellValue(YycStringUtils.convertObjToStr(map.getFundSource()));//资金来源（文号）
						getcell(row,11).setCellValue(YycStringUtils.convertObjToStr(map.getApprovalNum()));//批准文号
						getcell(row,12).setCellValue(YycStringUtils.convertObjToStr(map.getInvitationSupplier1()));//推荐供应商1
						getcell(row,13).setCellValue(YycStringUtils.convertObjToStr(map.getInvitationSupplier2()));//推荐供应商2
						getcell(row,14).setCellValue(YycStringUtils.convertObjToStr(map.getInvitationSupplier3()));//推荐供应商3
						getcell(row,15).setCellValue(YycStringUtils.convertObjToStr(map.getInvitationSupplier4()));//推荐供应商4
						getcell(row,16).setCellValue(YycStringUtils.convertObjToStr(map.getInvitationSupplier5()));//推荐供应商5
						getcell(row,17).setCellValue(YycStringUtils.convertObjToStr(matplanMat.getMatId()));//物料编码
						getcell(row,18).setCellValue(YycStringUtils.convertObjToStr(matplanMat.getMatDes()));//物料名称
						getcell(row,19).setCellValue(YycStringUtils.convertObjToStr(matplanMat.getLargeClass()));//大类
						getcell(row,20).setCellValue(YycStringUtils.convertObjToStr(matplanMat.getMiddleClass()));//中类
						getcell(row,21).setCellValue(YycStringUtils.convertObjToStr(matplanMat.getSmallClass()));//小类
						getcell(row,22).setCellValue(YycStringUtils.convertObjToStr(matplanMat.getMatUnit()));//计量单位
						getcell(row,23).setCellValue(YycStringUtils.convertObjToStr(matplanMat.getMatNum()));//数量
						getcell(row,24).setCellValue(YycStringUtils.convertObjToStr(matplanMat.getEstimateUnitPrice()));//预计单价
						getcell(row,25).setCellValue(YycStringUtils.convertObjToStr(matplanMat.getEstimateTotalPrice()));//预计总价
						getcell(row,26).setCellValue(YycStringUtils.convertObjToStr(matplanMat.getDeliveryPlace()));//交货地点
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    	String str = format.format(matplanMat.getDeliveryTime());
						getcell(row,27).setCellValue(YycStringUtils.convertObjToStr(str));//交货时间
					}
					index += map.getListMatplanMatBean().size();
				}
			}
		}
	    try {
	    	String fileName = "";
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	String str = format.format(new Date());
	    	fileName = str + "采购计划汇总导出.xlsx";
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
