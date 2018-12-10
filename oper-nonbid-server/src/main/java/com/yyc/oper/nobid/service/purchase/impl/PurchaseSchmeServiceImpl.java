package com.yyc.oper.nobid.service.purchase.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sgcc.uap.rest.support.WrappedResult;
import com.yyc.brace.base.principal.CurrentPrincipalHolder;
import com.yyc.brace.util.UUIDUtils;
import com.yyc.brace.util.entity.FileInfoVO;
import com.yyc.oper.nobid.expert.ExpertBean;
import com.yyc.oper.nobid.file.FileinfoBean;
import com.yyc.oper.nobid.mapper.ExpertExtractResultMapper;
import com.yyc.oper.nobid.mapper.FileInfoCrudMapper;
import com.yyc.oper.nobid.mapper.InvitationSupplierMapper;
import com.yyc.oper.nobid.mapper.MatplanMapper;
import com.yyc.oper.nobid.mapper.PurchaseSchmeMapper;
import com.yyc.oper.nobid.mapper.SupplierExtractResultMapper;
import com.yyc.oper.nobid.mat.MatplanBean;
import com.yyc.oper.nobid.mat.MatplanMatBean;
import com.yyc.oper.nobid.purchase.PurchaseSchemeRequest;
import com.yyc.oper.nobid.purchase.PurchaseSchemeResponse;
import com.yyc.oper.nobid.service.purchase.IPurchaseSchmeService;
import com.yyc.oper.nobid.supplier.InvitationSupplierBean;
import com.yyc.oper.nobid.supplier.SupplierBean;
import com.yyc.oper.nobid.supplier.SupplierExtractResultBean;

@Service
public class PurchaseSchmeServiceImpl implements IPurchaseSchmeService {

	@Autowired
	private PurchaseSchmeMapper purchaseSchmeMapper;

	@Autowired
	private MatplanMapper matplanMapper;
	
	@Autowired
	private SupplierExtractResultMapper supplierExtractResultMapper;
	
	@Autowired
	private InvitationSupplierMapper invitationSupplierMapper;
	
	@Autowired
	private FileInfoCrudMapper fileInfoCrudMapper;
	
	@Autowired
	private ExpertExtractResultMapper expertExtractResultMapper;

	@Override
	public PurchaseSchemeResponse getDetail(String purchaseId) {
		/**try {
			if (StringUtils.isBlank(purchaseId)) {
				return null;
			}
			PurchaseSchemeBean detail = purchaseSchmeMapper.selectDetail(purchaseId);
			if (detail == null) {
				return null;
			}
			String mergeId = detail.getMergeId();

			detail.setSupplierState("未抽取");
			detail.setExportState("未抽取");

			List<MatplanBean> matplanList = purchaseSchmeMapper.selectMatplanBean(mergeId);
			if (CollectionUtils.isNotEmpty(matplanList)) {
				detail.setMatplanBeanList(matplanList);
			}

			List<NonMatplanBean> nonMatplanList = purchaseSchmeMapper.selectNonMatplanBean(mergeId);
			if (CollectionUtils.isNotEmpty(nonMatplanList)) {
				detail.setNonMatplanBeanList(nonMatplanList);
			}**/

			/** 获取抽取的供应商结果基本信息 **/
			/**List<ExtractSupplier> extractSuppilerBean = purchaseSchmeMapper.selectExtractSuppilerBean(mergeId);
			if (CollectionUtils.isNotEmpty(extractSuppilerBean)
					&& StringUtils.isNotBlank(extractSuppilerBean.get(0).getExtractMethod())) {
				if (StringUtils.equals(extractSuppilerBean.get(0).getExtractMethod(), "001")) {
					detail.setSupplierExtractMethod("导入抽取");
				}
				if (StringUtils.equals(extractSuppilerBean.get(0).getExtractMethod(), "002")) {
					detail.setSupplierExtractMethod("随机抽取");
				}
				if (StringUtils.equals(extractSuppilerBean.get(0).getExtractMethod(), "003")) {
					detail.setSupplierExtractMethod("手工抽取");
				}

				if (detail.getSupplierRequiredNum() == 0) {
					detail.setSupplierRequiredNum(extractSuppilerBean.get(0).getExtractNum());
				}
			}**/

			/** 获取抽取的供应商的列表信息 **/
		/**List<SupplierBean> supplierList = purchaseSchmeMapper.selectSupplierBean(mergeId);
			if (CollectionUtils.isNotEmpty(supplierList)) {
				detail.setExtactedSupplierList(supplierList);
				detail.setSupplierState("已抽取");
			}**/

			/** 获取抽取的专家结果基本信息 **/
		/**List<Map<String, String>> extractExpertResult = purchaseSchmeMapper.selectExtractExpertBean(mergeId);
			if (CollectionUtils.isNotEmpty(extractExpertResult)) {
				Map<String, String> result = extractExpertResult.get(0);
				if (result != null && !result.isEmpty()) {
					int exportRequiredNum = 0;
					if (result.containsKey("technical_expert_num")
							&& StringUtils.isNotBlank(result.get("technical_expert_num"))) {
						exportRequiredNum = exportRequiredNum + Integer.getInteger(result.get("technical_expert_num"));
					}
					if (result.containsKey("business_expert_num")
							&& StringUtils.isNotBlank(result.get("business_expert_num"))) {
						exportRequiredNum = exportRequiredNum + Integer.getInteger(result.get("business_expert_num"));
					}
					detail.setExportRequiredNum(exportRequiredNum);

					if (result.containsKey("ope_way") && StringUtils.isNotBlank(result.get("ope_way"))) {
						if (StringUtils.equals(result.get("ope_way"), "001")) {
							detail.setExportExtractMethod("随机抽取");
						}
						if (StringUtils.equals(result.get("ope_way"), "002")) {
							detail.setExportExtractMethod("手工抽取");
						}
					}

				}
			}**/

			/** 获取抽取的专家的列表信息 **/
		/**List<ExpertBean> expertList = purchaseSchmeMapper.selectExpertBean(mergeId);
			if (CollectionUtils.isNotEmpty(expertList)) {
				detail.setExtactedExpertBeanList(expertList);
				detail.setExportState("已抽取");
			}
			return detail;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} **/
		PurchaseSchemeResponse purchaseSchemeResponse = new PurchaseSchemeResponse();
		PurchaseSchemeRequest purchaseSchemeRequest = new PurchaseSchemeRequest();
		purchaseSchemeRequest.setMergeId(purchaseId);
		List<PurchaseSchemeResponse> list = purchaseSchmeMapper.selectByPurchaseSchemeRequest(purchaseSchemeRequest);
		if(list != null && list.size()>0) {
			PurchaseSchemeResponse temp = list.get(0);
			BeanUtils.copyProperties(temp, purchaseSchemeResponse);
			//物料
			MatplanBean temp1 = new MatplanBean();
			temp1.setMergeId(temp.getMergeId());
			List<MatplanBean> list1 = matplanMapper.selectMatplanAndMat(temp1);
			List<MatplanMatBean> listMatplanMat = new ArrayList<MatplanMatBean>();
			for(int i=0;i<list1.size();i++) {
				List<MatplanMatBean> listTemp = list1.get(i).getListMatplanMatBean();
				for(int j=0;j<listTemp.size();j++) {
					listMatplanMat.add(listTemp.get(j));
				}
			}
			purchaseSchemeResponse.setMatplanMatBeanList(listMatplanMat);
			//抽取供应商
			List<SupplierBean> listSupplierBean = new ArrayList<SupplierBean>();
			SupplierExtractResultBean temp2 = new SupplierExtractResultBean();
			temp2.setMergeId(temp.getMergeId());
			List<SupplierExtractResultBean> list2 = supplierExtractResultMapper.selectSupplierExtractByMergeId(temp2);
			if(null != list2 && list2.size()>0) {
				listSupplierBean.addAll(list2.get(0).getListSupplierBean());
			}
			//推荐邀请供应商
			List<MatplanBean> list3 = matplanMapper.selectByPrimaryKey(temp1);
			String[] matplanIds = new String[list3.size()];
			for(int i=0;i<list3.size();i++) {
				matplanIds[i] = list3.get(i).getMatplanId();
			}
			InvitationSupplierBean invitationSupplierBean = new InvitationSupplierBean();
			invitationSupplierBean.setMatplanIds(matplanIds);
			List<InvitationSupplierBean> list4 = invitationSupplierMapper.selectByPrimaryKey(invitationSupplierBean);
			for(int i=0; i<list4.size(); i++) {
				SupplierBean tempSu = list4.get(i).getSupplierBean();
				boolean bool = true;
				for(int j=0;j<listSupplierBean.size();j++) {
					if(tempSu.getSupplierId().equals(listSupplierBean.get(j).getSupplierId())) {
						bool = false;
						break;
					}
				}
				if(bool) {
					listSupplierBean.add(tempSu);
				}
			}
			purchaseSchemeResponse.setExtactedSupplierList(listSupplierBean);
			//抽取专家集合
			List<ExpertBean> expertList = expertExtractResultMapper.selectExpertListByMergerId(purchaseId);
			if(null != expertList && expertList.size()>0) {
				List<ExpertBean> tempList = new ArrayList<>();
				for(int i=0;i<expertList.size();i++) {
					if(expertList.get(i) != null && StringUtils.isNotBlank(expertList.get(i).getExpertCode())) {
						tempList.add(expertList.get(i));
					}
				}
				if(tempList.size()>0) {
					purchaseSchemeResponse.setExtactedExpertBeanList(tempList);
				}
			}
		}
		return purchaseSchemeResponse;
	}



	@Override
	public boolean updateState(List<String> purchaseIdList, String state) {
		try {
			if (CollectionUtils.isEmpty(purchaseIdList)) {
				return false;
			}
			if (StringUtils.isBlank(state)) {
				return false;
			}
			Map<String, Object> map = Maps.newHashMap();
			map.put("idList", purchaseIdList);
			map.put("state", state);
			int updateState = purchaseSchmeMapper.updateState(map);
			if (updateState < 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



	@Override
	public PageInfo<PurchaseSchemeResponse> selectByPurchaseSchemeRequest(PurchaseSchemeRequest record) {
		
        List<PurchaseSchemeResponse> list = purchaseSchmeMapper.selectByPurchaseSchemeRequest(record);
        for(PurchaseSchemeResponse purchaseSchemeResponse:list){
        	String mergeId = purchaseSchemeResponse.getMergeId();
        	int recommendSupplierNum = purchaseSchmeMapper.selectCountNum(mergeId);
        	purchaseSchemeResponse.setRecommendSupplierNum(recommendSupplierNum);
        	//根据合包id，获取邀请推荐供应商数组
        	MatplanBean matplanBean = new MatplanBean();
        	matplanBean.setMergeId(mergeId);
        	List<MatplanBean> listMatplanBean = matplanMapper.selectByPrimaryKey(matplanBean);
        	String[] matplanIds = new String[listMatplanBean.size()];
        	for(int i=0; i<listMatplanBean.size(); i++) {
        		matplanIds[i] = listMatplanBean.get(i).getMatplanId();
        	}
        	InvitationSupplierBean invitationSupplierBean = new InvitationSupplierBean();
        	invitationSupplierBean.setMatplanIds(matplanIds);
        	List<InvitationSupplierBean> listInvitationSupplierBean = invitationSupplierMapper.selectByPrimaryKey(invitationSupplierBean);
        	List<String> supplierIds = new ArrayList<>();
        	for(int i=0; i<listInvitationSupplierBean.size(); i++) {
    			InvitationSupplierBean temp2 = listInvitationSupplierBean.get(i);
    			boolean bool = true;
    			for(int j=0;j<supplierIds.size();j++) {
    				if(temp2.getSupplierId().equals(supplierIds.get(j))) {
    					bool = false;
    					break;
    				}
    			}
    			if(bool) {
    				supplierIds.add(temp2.getSupplierId());
    			}
    		}
        	purchaseSchemeResponse.setInvitationSupplierIds(toArrayByObject(supplierIds.toArray()));
        }
        PageInfo<PurchaseSchemeResponse> result = new PageInfo<PurchaseSchemeResponse>(list);
        return result;
	}
	
	public WrappedResult uploadDocument(MultipartFile file) {
		try {
			//List<String> typeList = Arrays.asList("doc,docx,xls,xlsx,txt,zip".split(","));
			//WrappedResult uploadFile = UploadUtil.uploadFile(file, "/Users/jayden/Desktop/test", typeList, 1024 * 50);
			String fileName = file.getOriginalFilename();
			String type = "xls";
			String savedFileName = UUIDUtils.getUUID() + "." + type;
			String savePathBuilder = "E:\\Users\\jayden\\Desktop\\test\\xls\\2018\\11\\14\\"+savedFileName;
			file.transferTo(new File(savePathBuilder));
			FileInfoVO vo = new FileInfoVO();
			vo.setId("FILE" + UUIDUtils.getUUID());
			vo.setOriginalName(fileName);
			vo.setNewName(savedFileName);
			vo.setSize(file.getSize());
			vo.setPath(savePathBuilder.toString());
			vo.setAddTime(new Timestamp(System.currentTimeMillis()));
			vo.setDownloadCount(0);
			vo.setExtension(type.toUpperCase());
			int saveFileInfo = saveFileInfoToDB(vo, file);
//			if (uploadFile.isSuccessful()) {
//				int saveFileInfo = saveFileInfoToDB(uploadFile, file);
//				if (saveFileInfo <= 0) {
//					return WrappedResult.failedWrappedResult("文件上传成功，但是文件的相关信息保存失败！");
//				}
//			}
			return WrappedResult.failedWrappedResult("文件上传并保存成功！");
		} catch (Exception e) {
			return WrappedResult.failedWrappedResult(e.getMessage());
		}
	}
	
	private int saveFileInfoToDB(FileInfoVO fileInfoVO, MultipartFile file) {
		try {
			//FileInfoVO resultValue = (FileInfoVO) uploadFile.getResultValue();
			return fileInfoCrudMapper.saveFileInfo(fileInfoVO);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public String[] toArrayByObject(Object[] param) {
		String[] array = new String[param.length];
		for(int i=0; i<param.length; i++) {
			array[i] = (String)param[i];
		}
		return array;
	}



	@Override
	public int insertFile(List<FileinfoBean> fileinfo) {
		for(FileinfoBean fileinfoBean : fileinfo) {
			fileinfoBean.setCreateBy(CurrentPrincipalHolder.getUid());// 创建人 只有新增才有创建人
			Date currentDate = new Date();
			fileinfoBean.setCreateTime(currentDate);// 创建时间  只有新增才有 
			fileinfoBean.setOpeBy(CurrentPrincipalHolder.getUid());//新增的时候 操作人也是创建人 ，修改或删除其他操作 为操作人。
			fileinfoBean.setOpeTime(currentDate);//新增操作时间 和新增时间一致    修改或者删除 以及其他 操作  时间为操作时间
		}
		return fileInfoCrudMapper.insertFile(fileinfo);
	}



	@Override
	public FileinfoBean selectByPrimaryKey(String id) {
		
		return fileInfoCrudMapper.selectByPrimaryKey(id);
	}



	@Override
	public List<FileinfoBean> selectFileList(String purchaseSchmeId) {
		List<FileinfoBean> filelist = fileInfoCrudMapper.selectFileList(purchaseSchmeId);
		return filelist;
	}



	@Override
	public boolean deleteMergeState(List<String> mergeIdList) {
		try {
			if (CollectionUtils.isEmpty(mergeIdList)) {
				return false;
			}
			Map<String, Object> map = Maps.newHashMap();
			map.put("idList", mergeIdList);

			int updateState = purchaseSchmeMapper.deleteMergeState(map);
			int updateMatplan = purchaseSchmeMapper.updateMatState(map);
			if (updateState < 0) {
				return false;
			}
			if (updateMatplan < 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



	@Override
	public PageInfo<FileinfoBean> selectFileListByFileinfoBean(FileinfoBean record) {
		List<FileinfoBean> list = fileInfoCrudMapper.selectFileListByFileinfoBean(record);
		return new PageInfo<FileinfoBean> (list);
	}

}
