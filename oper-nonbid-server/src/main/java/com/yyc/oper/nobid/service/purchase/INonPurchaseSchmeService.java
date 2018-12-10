package com.yyc.oper.nobid.service.purchase;

import java.util.List;

import com.yyc.oper.nobid.file.FileinfoBean;
import com.yyc.oper.nobid.purchase.PurchaseSchemeResponse;

public interface INonPurchaseSchmeService {


	public boolean updateState(List<String> list, String string);

	public boolean deleteMergeState(List<String> list);

	public FileinfoBean selectByPrimaryKey(String id);

	public int insertFile(List<FileinfoBean> fileinfo);

	public List<FileinfoBean> selectFileList(String purchaseSchmeId);

	public PurchaseSchemeResponse getDetail(String purchaseId);

}
