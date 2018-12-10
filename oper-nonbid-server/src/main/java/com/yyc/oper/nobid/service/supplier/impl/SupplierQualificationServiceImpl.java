package com.yyc.oper.nobid.service.supplier.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyc.oper.nobid.mapper.SupplierQualificationMapper;
import com.yyc.oper.nobid.service.supplier.ISupplierQualificationService;
import com.yyc.oper.nobid.supplier.SupplierQualificationBean;
@Service
public class SupplierQualificationServiceImpl implements ISupplierQualificationService {

	@Autowired
	private SupplierQualificationMapper supplierQualificationMapper;
	
	public int addQualificationList(List<SupplierQualificationBean> qualificationlist) {
		return supplierQualificationMapper.addQualificationList(qualificationlist);
	}
}
