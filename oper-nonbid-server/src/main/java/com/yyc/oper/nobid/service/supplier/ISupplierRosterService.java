package com.yyc.oper.nobid.service.supplier;

import java.util.List;

import com.yyc.oper.nobid.supplier.SupplierBean;
import com.yyc.oper.nobid.supplier.SupplierRosterBean;

public interface ISupplierRosterService {

	int save(SupplierRosterBean record);



	int saveBatch(List<SupplierRosterBean> supplierRosterBeanList);



	int removeByPrimaryKey(String supplierRosterId);



	int removeBySupplierId(String supplierId);



	int removeByRosterType(String rosterType);



	int modifyByPrimaryKey(SupplierRosterBean record);



	SupplierRosterBean getByPrimaryKey(String supplierRosterId);



	List<SupplierRosterBean> getBySupplierId(String supplierId);



	List<SupplierRosterBean> getByRosterType(String rosterType);



	/**
	 * 
	 * 根据名单类型获取供应商主键列表
	 *
	 * @param rosterType
	 *            供应商名单类型
	 * @return
	 */
	List<String> getSupplierIdByRosterType(String rosterType);



	List<SupplierBean> getByParam(SupplierRosterBean record);

}
