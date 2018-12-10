package com.yyc.oper.nobid.service.purchase;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.sgcc.uap.rest.support.WrappedResult;
import com.yyc.oper.nobid.file.FileinfoBean;
import com.yyc.oper.nobid.purchase.PurchaseSchemeRequest;
import com.yyc.oper.nobid.purchase.PurchaseSchemeResponse;

public interface IPurchaseSchmeService {

	/**
	 * 采购方案详情查询
	 * 
	 * @param purchaseId
	 *            采购方案的主键编号
	 * @return
	 */
	public PurchaseSchemeResponse getDetail(String purchaseId);



	/**
	 * 
	 * 修改采购方案状态
	 *
	 * @param purchaseId
	 *            采购方案的主键编号
	 * @param state
	 *            状态
	 * @return
	 */
	public boolean updateState(List<String> purchaseIdList, String state);
	
	/**
	 * 
	 * 查询采购方案列表
	 *
	 * @param record
	 *            采购方案列表查询参数
	 * @return
	 */
	public PageInfo<PurchaseSchemeResponse> selectByPurchaseSchemeRequest(PurchaseSchemeRequest record);
	
	/**
	 * 
	 * 附件上传
	 *
	 * @param file
	 *            文件对象
	 * @return
	 */
	public WrappedResult uploadDocument(MultipartFile file);



	public int insertFile(List<FileinfoBean> fileinfo);



	public FileinfoBean selectByPrimaryKey(String id);



	public List<FileinfoBean> selectFileList(String purchaseSchmeId);



	public boolean deleteMergeState(List<String> list);
	

	/**
	 * 
	 * 分页查询附件
	 *
	 * @param record
	 * @return
	 */
	public PageInfo<FileinfoBean> selectFileListByFileinfoBean(FileinfoBean record);
	
}
