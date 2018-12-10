package com.yyc.oper.nobid.service.supplier.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyc.oper.nobid.mapper.SupplierRosterMapper;
import com.yyc.oper.nobid.service.supplier.ISupplierRosterService;
import com.yyc.oper.nobid.supplier.SupplierBean;
import com.yyc.oper.nobid.supplier.SupplierRosterBean;

@Service
public class SupplierRosterServiceImpl implements ISupplierRosterService {

	@Autowired
	private SupplierRosterMapper supplierRosterMapper;



	@Override
	public int save(SupplierRosterBean record) {
		return supplierRosterMapper.insert(record);
	}



	@Override
	public int saveBatch(List<SupplierRosterBean> supplierRosterBeanList) {
		return supplierRosterMapper.insertBatch(supplierRosterBeanList);
	}



	@Override
	public int removeByPrimaryKey(String supplierRosterId) {
		return supplierRosterMapper.deleteByPrimaryKey(supplierRosterId);
	}



	@Override
	public int removeBySupplierId(String supplierId) {
		return supplierRosterMapper.deleteBySupplierId(supplierId);
	}



	@Override
	public int removeByRosterType(String rosterType) {
		return supplierRosterMapper.deleteByRosterType(rosterType);
	}



	@Override
	public int modifyByPrimaryKey(SupplierRosterBean record) {
		return supplierRosterMapper.updateByPrimaryKey(record);
	}



	@Override
	public SupplierRosterBean getByPrimaryKey(String supplierRosterId) {
		return supplierRosterMapper.selectByPrimaryKey(supplierRosterId);
	}



	@Override
	public List<SupplierRosterBean> getBySupplierId(String supplierId) {
		return supplierRosterMapper.selectBySupplierId(supplierId);
	}



	@Override
	public List<SupplierRosterBean> getByRosterType(String rosterType) {
		return supplierRosterMapper.selectByRosterType(rosterType);
	}



	@Override
	public List<SupplierBean> getByParam(SupplierRosterBean record) {
		return supplierRosterMapper.selectByParam(record);
	}



	@Override
	public List<String> getSupplierIdByRosterType(String rosterType) {
		return supplierRosterMapper.selectSupplierIdByRosterType(rosterType);
	}

}
