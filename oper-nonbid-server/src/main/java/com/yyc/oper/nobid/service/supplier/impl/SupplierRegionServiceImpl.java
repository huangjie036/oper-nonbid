package com.yyc.oper.nobid.service.supplier.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyc.oper.nobid.mapper.SupplierRegionMapper;
import com.yyc.oper.nobid.service.supplier.ISupplierRegionService;
import com.yyc.oper.nobid.supplier.SupplierRegionBean;

@Service
public class SupplierRegionServiceImpl implements ISupplierRegionService {

	@Autowired
	private SupplierRegionMapper supplierRegionMapper;
	@Override
	public int addRegionList(List<SupplierRegionBean> regionlist) {
		// TODO Auto-generated method stub
		return supplierRegionMapper.addRegionList(regionlist);
	}

}
