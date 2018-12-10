package com.yyc.oper.nobid.service.expert.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.expert.NonbidExpertMajorBean;
import com.yyc.oper.nobid.mapper.NonbidExpertMajorMapper;
import com.yyc.oper.nobid.service.expert.INonbidExpertMajorService;
import com.yyc.oper.nobid.util.GenerateUUID;

@Service
public class NonbidExpertMajorServiceImpl implements INonbidExpertMajorService{
	
	@Autowired
	private NonbidExpertMajorMapper nonbidExpertMajorMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return nonbidExpertMajorMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insertSelective(NonbidExpertMajorBean record) {
		record.setId(GenerateUUID.getUUID());
		return nonbidExpertMajorMapper.insertSelective(record);
	}
	
	@Override
	public PageInfo<NonbidExpertMajorBean> selectByPrimaryKey(NonbidExpertMajorBean record) {
		
        List<NonbidExpertMajorBean> mmNonbidExpertList = nonbidExpertMajorMapper.selectByPrimaryKey(record);
        PageInfo<NonbidExpertMajorBean> result = new PageInfo<NonbidExpertMajorBean>(mmNonbidExpertList);
        return result;
	}
	
	@Override
	public int updateByPrimaryKeySelective(NonbidExpertMajorBean record) {
		return nonbidExpertMajorMapper.updateByPrimaryKeySelective(record);
	}
}
