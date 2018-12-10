package com.yyc.oper.nobid.service.supplier.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyc.oper.nobid.mapper.SupplierColatitudeMapper;
import com.yyc.oper.nobid.service.supplier.ISupplierColatitudeService;
import com.yyc.oper.nobid.supplier.SupplierColatitudeBean;

@Service
public class SupplierColatitudeServiceImpl implements ISupplierColatitudeService {
	@Autowired
	private SupplierColatitudeMapper supplierColatitudeMapper;
	
	public int addColatitudeList(List<SupplierColatitudeBean> colatitudelist) {
		return supplierColatitudeMapper.addColatitudeList(colatitudelist);
	}


}
