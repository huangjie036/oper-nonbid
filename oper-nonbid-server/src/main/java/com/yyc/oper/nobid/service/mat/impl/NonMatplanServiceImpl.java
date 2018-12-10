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
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.batch.BatchmanageBean;
import com.yyc.oper.nobid.mapper.BatchmanageMapper;
import com.yyc.oper.nobid.mapper.InvitationSupplierMapper;
import com.yyc.oper.nobid.mapper.MatplanMatMapper;
import com.yyc.oper.nobid.mapper.MergeRecordMapper;
import com.yyc.oper.nobid.mapper.NonMatplanMapper;
import com.yyc.oper.nobid.mapper.SupplierMapper;
import com.yyc.oper.nobid.mat.MatplanRequest;
import com.yyc.oper.nobid.mat.MergeRecorRequest;
import com.yyc.oper.nobid.mat.NonMatplanBean;
import com.yyc.oper.nobid.mat.NonMatplanBeanEnum;
import com.yyc.oper.nobid.mat.NonMatplanIdRequest;
import com.yyc.oper.nobid.mat.NonMatplanRequest;
import com.yyc.oper.nobid.merge.MergeRecordBean;
import com.yyc.oper.nobid.service.mat.INonMatplanService;
import com.yyc.oper.nobid.supplier.InvitationSupplierBean;
import com.yyc.oper.nobid.supplier.InvitationSupplierImportExcel;
import com.yyc.oper.nobid.supplier.SupplierBean;
import com.yyc.oper.nobid.util.GenerateUUID;
import com.yyc.oper.nobid.util.YycFileUtil;
import com.yyc.oper.nobid.util.YycStringUtils;

@Service
public class NonMatplanServiceImpl implements INonMatplanService{
	
	@Autowired
	private NonMatplanMapper nonMatplanMapper;
	
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
	
	@Override
	public int deleteByPrimaryKey(String matplanId) {
		return nonMatplanMapper.deleteByPrimaryKey(matplanId);
	}
	
	@Override
	public int insertSelective(NonMatplanRequest matplanRequest) {
		int result = 0;
		NonMatplanBean record = new NonMatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		String uuid = GenerateUUID.getUUID();
		record.setNonMatplanId(uuid);
		record.setState("1");//1待提交
		record.setNonMatplanSource("01");//01手工录入 02Excel导入
		record.setCreateBy((String)CurrentPrincipalHolder.getAttribute("name"));
		record.setCreateTime(new Date());
		record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
		record.setOpeTime(new Date());
		result += nonMatplanMapper.insertSelective(record);
		//新增邀请供应商
		List<InvitationSupplierBean> listInvitationSupplierBean = matplanRequest.getListInvitationSupplierBean();
		if(null != listInvitationSupplierBean && listInvitationSupplierBean.size()>0) {
			for(int i=0;i<listInvitationSupplierBean.size();i++) {
				if("".equals(listInvitationSupplierBean.get(i).getSupplierId()) || "".equals(listInvitationSupplierBean.get(i).getSupplierPhone())) {
					continue;
				}
				listInvitationSupplierBean.get(i).setMatplanId(uuid);
				listInvitationSupplierBean.get(i).setInvitationId(GenerateUUID.getUUID());
				listInvitationSupplierBean.get(i).setIsMat("1");//0物资，1非物资
			}
			invitationSupplierMapper.insert(listInvitationSupplierBean);
		}
		return result == 0 ? -1 : result;
	}
	
	@Override
	public PageInfo<NonMatplanBean> selectByPrimaryKey(NonMatplanBean record) {
        List<NonMatplanBean> mmNonbidExpertList = nonMatplanMapper.selectByPrimaryKey(record);
        return new PageInfo<>(mmNonbidExpertList);
	}

	@Override
	public PageInfo<NonMatplanBean> getNonMatplanRequest(NonMatplanBean record) {
        List<NonMatplanBean> listMatplanBean = nonMatplanMapper.selectByPrimaryKey(record);
        for(int i=0;i<listMatplanBean.size();i++) {
        	//获取推荐供应商
        	InvitationSupplierBean supplier = new InvitationSupplierBean();
            supplier.setMatplanId(listMatplanBean.get(i).getNonMatplanId());
            List<InvitationSupplierBean> listInvitationSupplierBean = invitationSupplierMapper.selectByPrimaryKey(supplier);
            listMatplanBean.get(i).setListInvitationSupplierBean(listInvitationSupplierBean);
        }
        return new PageInfo<>(listMatplanBean);
	}
	
	@Override
	public int updateByPrimaryKeySelective(NonMatplanRequest matplanRequest) {
		int result = 0;
		NonMatplanBean record = new NonMatplanBean();
		BeanUtils.copyProperties(matplanRequest, record);
		//修改邀请供应商
		List<InvitationSupplierBean> listInvitationSupplierBean = matplanRequest.getListInvitationSupplierBean();
		if(null != listInvitationSupplierBean && listInvitationSupplierBean.size()>0) {
			result += invitationSupplierMapper.deleteByMatplanId(record.getNonMatplanId());
			for(int i=0;i<listInvitationSupplierBean.size();i++) {
				if("".equals(listInvitationSupplierBean.get(i).getSupplierId()) || "".equals(listInvitationSupplierBean.get(i).getSupplierPhone())) {
					continue;
				}
				listInvitationSupplierBean.get(i).setMatplanId(record.getNonMatplanId());
				listInvitationSupplierBean.get(i).setInvitationId(GenerateUUID.getUUID());
				listInvitationSupplierBean.get(i).setIsMat("1");//0物资，1非物资
			}
			invitationSupplierMapper.insert(listInvitationSupplierBean);
		}
		record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
		record.setOpeTime(new Date());
		result += nonMatplanMapper.updateByPrimaryKeySelective(record);
		return result == 0 ? -1 : result;
	}


	@Override
	public String deleteByMatplanIds(NonMatplanIdRequest matplanIds) {
		int result = 0;
		NonMatplanBean record = new NonMatplanBean();
		record.setDel("1");
		String[] array = matplanIds.getNonMatplanIds();
		for(int i=0; i<array.length; i++) {
			record.setNonMatplanId(array[i]);
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			result += nonMatplanMapper.updateByPrimaryKeySelective(record);
		}
		return "删除成功" + result + "条！";
	}

	@Override
	public String matplanSubmitByMatplanIds(NonMatplanIdRequest matplanIds) {
		int result = 0;
		NonMatplanBean record = new NonMatplanBean();
		record.setState("2");//2待审核
		String[] array = matplanIds.getNonMatplanIds();
		for(int i=0; i<array.length; i++) {
			record.setNonMatplanId(array[i]);
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			result += nonMatplanMapper.updateByPrimaryKeySelective(record);
		}
		return "提交成功" + result + "条！";
	}

	@Override
	public String matplanExamineByMatplanIds(NonMatplanIdRequest matplanIds) {
		int result = 0;
		NonMatplanBean record = new NonMatplanBean();
		record.setState("3");//3待确认
		String[] array = matplanIds.getNonMatplanIds();
		for(int i=0; i<array.length; i++) {
			record.setNonMatplanId(array[i]);
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			result += nonMatplanMapper.updateByPrimaryKeySelective(record);
		}
		return "审核成功" + result + "条！";
	}
	
	@Override
	public String matplanconfirmByMatplanIds(NonMatplanIdRequest matplanIds) {
		int result = 0;
		NonMatplanBean record = new NonMatplanBean();
		record.setState("4");//4已确认
		String[] array = matplanIds.getNonMatplanIds();
		for(int i=0; i<array.length; i++) {
			record.setNonMatplanId(array[i]);
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			result += nonMatplanMapper.updateByPrimaryKeySelective(record);
		}
		return "确认成功" + result + "条！";
	}

	@Override
	public String matplanRejectByMatplanIds(NonMatplanIdRequest matplanIds) {
		int result = 0;
		NonMatplanBean record = new NonMatplanBean();
		record.setState("5");//4已驳回
		String[] array = matplanIds.getNonMatplanIds();
		for(int i=0; i<array.length; i++) {
			record.setNonMatplanId(array[i]);
			record.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			record.setOpeTime(new Date());
			result += nonMatplanMapper.updateByPrimaryKeySelective(record);
		}
		return "驳回成功" + result + "条！";
	}

	@Override
	public int matplanMerge(MergeRecorRequest matplanRequest) {
		int result = 0;
		String uuId = GenerateUUID.getUUID();
		NonMatplanBean record = new NonMatplanBean();
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
		mergeRecordBean.setIsMat("1");//0物资，1非物资
		mergeRecordBean.setCreateBy((String)CurrentPrincipalHolder.getAttribute("name"));
		mergeRecordBean.setCreateTime(new Date());
		mergeRecordBean.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
		mergeRecordBean.setOpeTime(new Date());
		result += mergeRecordMapper.insertSelective(mergeRecordBean);
		NonMatplanBean matplanBean = new NonMatplanBean();
		matplanBean.setMergeId(uuId);
		matplanBean.setIsMerge("1");//是否合包 1是
		matplanBean.setMergeState("1");//合包状态 1已合包
		String[] matplanIds = record.getNonMatplanIds();
		for(int i=0; i<matplanIds.length; i++) {
			matplanBean.setNonMatplanId(matplanIds[i]);
			matplanBean.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			matplanBean.setOpeTime(new Date());
			result += nonMatplanMapper.updateByPrimaryKeySelective(matplanBean);
		}
		return result;
	}


	@Override
	public int matplanNoMerge(MergeRecorRequest matplanRequest) {
		int result = 0;
		String uuId = GenerateUUID.getUUID();
		NonMatplanBean record = new NonMatplanBean();
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
		mergeRecordBean.setIsMat("1");//0物资，1非物资
		mergeRecordBean.setCreateBy((String)CurrentPrincipalHolder.getAttribute("name"));
		mergeRecordBean.setCreateTime(new Date());
		mergeRecordBean.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
		mergeRecordBean.setOpeTime(new Date());
		result += mergeRecordMapper.insertSelective(mergeRecordBean);
		NonMatplanBean matplanBean = new NonMatplanBean();
		matplanBean.setMergeId(uuId);
		matplanBean.setIsMerge("0");//是否合包 0否
		matplanBean.setMergeState("1");//合包状态 1已合包
		String[] matplanIds = record.getNonMatplanIds();
		for(int i=0; i<matplanIds.length; i++) {
			matplanBean.setNonMatplanId(matplanIds[i]);
			matplanBean.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			matplanBean.setOpeTime(new Date());
			result += nonMatplanMapper.updateByPrimaryKeySelective(matplanBean);
		}
		return result;
	}

	@Override
	public int invitationSupplierByMatplanId(MatplanRequest matplanRequest) {
		List<InvitationSupplierBean> list = matplanRequest.getListInvitationSupplierBean();
		return invitationSupplierMapper.insert(list);
	}

	@Override
	public MergeRecorRequest editMergeByMatplanIds(NonMatplanIdRequest matplanIds) {
		MergeRecorRequest mergeRecorRequest = new MergeRecorRequest();
		//判断是否可以合包  1判断批次编号、采购方式是否相同
		NonMatplanBean record = new NonMatplanBean();
		record.setNonMatplanIds(matplanIds.getNonMatplanIds());
		List<NonMatplanBean> listMatplanBean = nonMatplanMapper.selectByPrimaryKey(record);
		List<NonMatplanBean> listTempMatplan = new ArrayList<NonMatplanBean>();
		boolean bool1 = true;
		continueOut:
		for(int i=0; i<listMatplanBean.size(); i++) {
			NonMatplanBean temp2 = listMatplanBean.get(i);
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
			NonMatplanBean temp1 = listMatplanBean.get(0);
			BatchmanageBean batchmanage = new BatchmanageBean();
			batchmanage.setBatchNum(temp1.getBatchNum());
			List<BatchmanageBean> listBatchmanageBean = batchmanageMapper.selectByPrimaryKey(batchmanage);
			if(null != listBatchmanageBean && listBatchmanageBean.size()>0) {
				mergeRecorRequest.setBatchName(listBatchmanageBean.get(0).getBatchName());
			}
			mergeRecorRequest.setBatchNum(temp1.getBatchNum());
			mergeRecorRequest.setPurchaseWay(temp1.getPurchaseWay());
			//不合包填值
			if(matplanIds.getNonMatplanIds().length==1) {
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
			return null;
		}
		
		//判断是否可以合包  2判断邀请供应商是否小余等于5个
		List<InvitationSupplierBean> list = new ArrayList<InvitationSupplierBean>();
		InvitationSupplierBean invitationSupplierBean = new InvitationSupplierBean();
		invitationSupplierBean.setMatplanIds(matplanIds.getNonMatplanIds());
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
		mergeRecorRequest.setListInvitationSupplierBean(list);
		return mergeRecorRequest;
	}

	@Override
	public ResultMessage importExcel(List<Map<Integer, Object>> readExcel) {
		ResultMessage resultMessage = new ResultMessage(StateDictionary.ERROR,"");
		String result = "";
		Set<NonMatplanBean> matplanBeanHashSet = new HashSet<>();
		Set<InvitationSupplierImportExcel> invitationSupplierImportExcelHashSet = new HashSet<>();
		boolean bool = true; 
		for (int i=0; i<readExcel.size(); i++) {
			Map<Integer, Object> map = readExcel.get(i);
			NonMatplanBean matplanBean = new NonMatplanBean();
			InvitationSupplierImportExcel invitationSupplierImportExcel = new InvitationSupplierImportExcel();
			// 当前行 对象。
			for (Entry<Integer, Object> entry : map.entrySet()) {
				// 遍历枚举 ， 根据对应列 下标 获取对应的set方法名称
				for (NonMatplanBeanEnum beanEnum : NonMatplanBeanEnum.values()) {
					if (beanEnum.getIndex() == entry.getKey()) {
						try {
							if(29 >= beanEnum.getIndex()) {
								// 根据set方法名称 和实体获取 对应Method。
								Method methods = matplanBean.getClass().getDeclaredMethod(beanEnum.getSetMethod(), String.class);
								// 根据method 对象和实体反射填充 对象属性值。
								methods.invoke(matplanBean, entry.getValue());
							}else {
								Method methods = invitationSupplierImportExcel.getClass().getDeclaredMethod(beanEnum.getSetMethod(), String.class);
								methods.invoke(invitationSupplierImportExcel, entry.getValue());
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
				}else {
					invitationSupplierImportExcel.setSupplierId1(list.get(0).getSupplierId());
				}
			}
			if(StringUtils.isNotBlank(invitationSupplierImportExcel.getSupplierId2())) {
				SupplierBean temp = new SupplierBean();
				temp.setSupplierName(invitationSupplierImportExcel.getSupplierId2());
				List<SupplierBean> list = supplierMapper.selectByPrimaryKey(temp);
				if(null==list || list.size()==0 || null==list.get(0)) {
					result += "第"+ (i+1) +"行 推荐供应商2，不在库中！";
					bool = false;
				}else {
					invitationSupplierImportExcel.setSupplierId2(list.get(0).getSupplierId());
				}
			}
			if(StringUtils.isNotBlank(invitationSupplierImportExcel.getSupplierId3())) {
				SupplierBean temp = new SupplierBean();
				temp.setSupplierName(invitationSupplierImportExcel.getSupplierId3());
				List<SupplierBean> list = supplierMapper.selectByPrimaryKey(temp);
				if(null==list || list.size()==0 || null==list.get(0)) {
					result += "第"+ (i+1) +"行 推荐供应商3，不在库中！";
					bool = false;
				}else {
					invitationSupplierImportExcel.setSupplierId3(list.get(0).getSupplierId());
				}
			}
			
			//根据序号、包号、项目编号、批次号 在库中找到记录，则不导入
			NonMatplanBean temp00 = new NonMatplanBean();
			temp00.setSerialNum(matplanBean.getSerialNum());
			temp00.setPackageNum(matplanBean.getPackageNum());
			temp00.setProjectNum(matplanBean.getProjectNum());
			temp00.setBatchNum(matplanBean.getBatchNum());
			List<NonMatplanBean> list00 = nonMatplanMapper.selectByPrimaryKey(temp00);
			if(list00 != null && list00.size()>0) {
				result += "第"+ (i+1) +"行 采购计划，在库中已存在！";
				bool = false;
				continue;
			}
			String uuId = GenerateUUID.getUUID();
			matplanBean.setNonMatplanId(uuId);
			matplanBean.setState("1");
			matplanBean.setNonMatplanSource("02");//01 手动，02 Excel
			matplanBean.setCreateBy((String)CurrentPrincipalHolder.getAttribute("name"));
			Date currentDate = new Date();
			matplanBean.setCreateTime(currentDate);
			matplanBean.setOpeBy((String)CurrentPrincipalHolder.getAttribute("name"));
			matplanBean.setOpeTime(currentDate);
			invitationSupplierImportExcel.setInvitationId(GenerateUUID.getUUID());
			invitationSupplierImportExcel.setMatplanId(uuId);
			matplanBeanHashSet.add(matplanBean);
			invitationSupplierImportExcelHashSet.add(invitationSupplierImportExcel);
		}
		
		if(bool) {
			int num = 0;
			if(matplanBeanHashSet.size() > 0) {
				List<NonMatplanBean> list = new ArrayList<NonMatplanBean>(matplanBeanHashSet);
				num += nonMatplanMapper.insert(list);
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
						invitationSupplierBean.setIsMat("1");//0物资，1非物资
						list.add(invitationSupplierBean);
					}
					if(StringUtils.isNotEmpty(bean.getSupplierId2())){
						InvitationSupplierBean invitationSupplierBean = new InvitationSupplierBean();
						invitationSupplierBean.setInvitationId(GenerateUUID.getUUID());
						invitationSupplierBean.setMatplanId(bean.getMatplanId());
						invitationSupplierBean.setSupplierId(bean.getSupplierId2());
						invitationSupplierBean.setSupplierPhone(bean.getSupplierPhone2());
						invitationSupplierBean.setIsMat("1");//0物资，1非物资
						list.add(invitationSupplierBean);
					}
					if(StringUtils.isNotEmpty(bean.getSupplierId3())){
						InvitationSupplierBean invitationSupplierBean = new InvitationSupplierBean();
						invitationSupplierBean.setInvitationId(GenerateUUID.getUUID());
						invitationSupplierBean.setMatplanId(bean.getMatplanId());
						invitationSupplierBean.setSupplierId(bean.getSupplierId3());
						invitationSupplierBean.setSupplierPhone(bean.getSupplierPhone3());
						invitationSupplierBean.setIsMat("1");//0物资，1非物资
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
	public void exportNonMatplan(List<NonMatplanRequest> list, HttpServletRequest request,
			HttpServletResponse response) {

		//获取模板
		InputStream ins = YycFileUtil.class.getResourceAsStream("/templates/exportNonMatplan.xlsx");
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
				NonMatplanRequest map = list.get(i);
				List<InvitationSupplierBean> listSupplier = map.getListInvitationSupplierBean();
				XSSFRow row = sheetAt.getRow(index + i);//获取行
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
				getcell(row,7).setCellValue(YycStringUtils.convertObjToStr(map.getServiceType()));//服务类型
				getcell(row,8).setCellValue(YycStringUtils.convertObjToStr(map.getDemandCompany()));//需求单位
				getcell(row,9).setCellValue(YycStringUtils.convertObjToStr(map.getPurchaseNum()));//采购申请号
				getcell(row,10).setCellValue(YycStringUtils.convertObjToStr(map.getFundSource()));//资金来源（文号）
				getcell(row,11).setCellValue(YycStringUtils.convertObjToStr(map.getApprovalNum()));//批准文号
				//getcell(row,7).setCellValue(YycStringUtils.convertObjToStr(map.getPurchaseStrategy()));//采购策略
				if(listSupplier.size()>0) {
					getcell(row,12).setCellValue(YycStringUtils.convertObjToStr(listSupplier.get(0).getSupplierName()));//推荐供应商1
				}
				if(listSupplier.size()>1) {
					getcell(row,13).setCellValue(YycStringUtils.convertObjToStr(listSupplier.get(1).getSupplierName()));//推荐供应商1
				}
				if(listSupplier.size()>2) {
					getcell(row,14).setCellValue(YycStringUtils.convertObjToStr(listSupplier.get(2).getSupplierName()));//推荐供应商1
				}
				if(listSupplier.size()>3) {
					getcell(row,15).setCellValue(YycStringUtils.convertObjToStr(listSupplier.get(3).getSupplierName()));//推荐供应商1
				}
				if(listSupplier.size()>4) {
					getcell(row,16).setCellValue(YycStringUtils.convertObjToStr(listSupplier.get(4).getSupplierName()));//推荐供应商1
				}
//				getcell(row,12).setCellValue(YycStringUtils.convertObjToStr(map));//推荐供应商2
//				getcell(row,13).setCellValue(YycStringUtils.convertObjToStr(map));//推荐供应商3
//				getcell(row,14).setCellValue(YycStringUtils.convertObjToStr(map));//推荐供应商4
//				getcell(row,15).setCellValue(YycStringUtils.convertObjToStr(map));//推荐供应商5
				getcell(row,17).setCellValue(YycStringUtils.convertObjToStr(map.getPricingWay()));//定价方式
				getcell(row,18).setCellValue(YycStringUtils.convertObjToStr(map.getPlanTotalPrice()));//计划总投资金额
				getcell(row,19).setCellValue(YycStringUtils.convertObjToStr(map.getIndividualNoTaxPrice()));//单项不含税金额
				getcell(row,20).setCellValue(YycStringUtils.convertObjToStr(map.getPurchaseTaxRate()));//采购方案税率
				getcell(row,21).setCellValue(YycStringUtils.convertObjToStr(map.getIndividualTaxRate()));//单项税率
				getcell(row,22).setCellValue(YycStringUtils.convertObjToStr(map.getIndividualTaxPrice()));//单项含税金额
				getcell(row,23).setCellValue(YycStringUtils.convertObjToStr(map.getProjectManagementResponsibilities()));//项目管理专责
				getcell(row,24).setCellValue(YycStringUtils.convertObjToStr(map.getTechnicalSpecification()));//技术规范书
				getcell(row,25).setCellValue(YycStringUtils.convertObjToStr(map.getBusinessDepartment()));//业务主管部门
				getcell(row,26).setCellValue(YycStringUtils.convertObjToStr(map.getWorkTimeLimit()));//工期
				index += i;
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
