package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.supplier.SupplierBean;
import com.yyc.oper.nobid.supplier.SupplierRosterBean;

/**
 * 
 * 供应商的黑白灰名单维护
 * 
 * @author: WangWei
 * @version: 1.0, 2018年9月18日
 */
@Mapper
public interface SupplierRosterMapper {

	int insert(SupplierRosterBean record);



	int insertBatch(List<SupplierRosterBean> SupplierRosterBeanList);



	int deleteByPrimaryKey(String supplierRosterId);



	int deleteBySupplierId(String supplierId);



	int deleteByRosterType(String rosterType);



	int updateByPrimaryKey(SupplierRosterBean record);



	SupplierRosterBean selectByPrimaryKey(String supplierRosterId);



	List<SupplierRosterBean> selectBySupplierId(String supplierId);



	List<SupplierRosterBean> selectByRosterType(String rosterType);



	List<SupplierBean> selectByParam(SupplierRosterBean record);



	/**
	 * 
	 * 根据名单类型获取供应商主键列表
	 *
	 * @param rosterType
	 *            供应商名单类型
	 * @return
	 */
	List<String> selectSupplierIdByRosterType(String rosterType);

}