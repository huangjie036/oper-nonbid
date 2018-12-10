package com.yyc.oper.nobid.service.supplier.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.mapper.SupplierEvalMapper;
import com.yyc.oper.nobid.service.supplier.ISupplierEvalService;
import com.yyc.oper.nobid.supplier.SupplierEvalBean;
import com.yyc.oper.nobid.util.GenerateUUID;

@Service
public class SupplierEvalServiceImpl implements ISupplierEvalService{
	
	@Autowired
	private SupplierEvalMapper supplierEvalMapper;
	
	@Override
	public int deleteByPrimaryKey(String supplierEvalId) {
		return supplierEvalMapper.deleteByPrimaryKey(supplierEvalId);
	}
	
	@Override
	public int insertSelective(SupplierEvalBean record) {
		record.setSupplierEvalId(GenerateUUID.getUUID());
		return supplierEvalMapper.insertSelective(record);
	}
	
	@Override
	public PageInfo<SupplierEvalBean> selectByPrimaryKey(SupplierEvalBean record) {
		
        List<SupplierEvalBean> mmNonbidExpertList = supplierEvalMapper.selectByPrimaryKey(record);
        PageInfo<SupplierEvalBean> result = new PageInfo<SupplierEvalBean>(mmNonbidExpertList);
        return result;
	}
	
	@Override
	public int updateByPrimaryKeySelective(SupplierEvalBean record) {
		return supplierEvalMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SupplierEvalBean> selectProjectBySupplier(SupplierEvalBean record) {
		// TODO Auto-generated method stub
		return supplierEvalMapper.selectProjectBySupplier(record);
	}

	@Override
	public int updateByPrimaryKey(String[] supplierEvalIds) {
		int result = 0;
		SupplierEvalBean record = new SupplierEvalBean();
		for(int i=0;i<supplierEvalIds.length;i++) {
			if(StringUtils.isNotBlank(supplierEvalIds[i])) {
				record.setSupplierEvalId(supplierEvalIds[i]);
				record.setDel("1");
				result = result + supplierEvalMapper.updateByPrimaryKeySelective(record);
			}
		}
		return result==0 ? -1:result;
	}
}
