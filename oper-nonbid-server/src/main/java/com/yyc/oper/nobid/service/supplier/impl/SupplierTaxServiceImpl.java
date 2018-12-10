package com.yyc.oper.nobid.service.supplier.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyc.oper.nobid.mapper.SupplierTaxMapper;
import com.yyc.oper.nobid.service.supplier.ISupplierTaxService;
import com.yyc.oper.nobid.supplier.SupplierTaxBean;
@Service
public class SupplierTaxServiceImpl implements ISupplierTaxService{

	@Autowired
	private SupplierTaxMapper supplierTaxMapper;
	
	@Override
	public int addTaxList(List<SupplierTaxBean> taxlist) {
		// TODO Auto-generated method stub
		return supplierTaxMapper.addTaxList(taxlist);
	}

}
