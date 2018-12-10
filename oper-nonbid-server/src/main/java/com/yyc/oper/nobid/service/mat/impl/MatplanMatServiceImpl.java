package com.yyc.oper.nobid.service.mat.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.mapper.MatplanMatMapper;
import com.yyc.oper.nobid.mat.MatplanMatBean;
import com.yyc.oper.nobid.service.mat.IMatplanMatService;
import com.yyc.oper.nobid.util.GenerateUUID;

@Service
public class MatplanMatServiceImpl implements IMatplanMatService{
	
	@Autowired
	private MatplanMatMapper matplanMatMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return matplanMatMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(List<MatplanMatBean> list) {
		return matplanMatMapper.insert(list);
	}
	
	@Override
	public int insertSelective(MatplanMatBean record) {
		record.setId(GenerateUUID.getUUID());
		return matplanMatMapper.insertSelective(record);
	}
	
	@Override
	public PageInfo<MatplanMatBean> selectByPrimaryKey(MatplanMatBean record) {
		//
        List<MatplanMatBean> mmNonbidExpertList = matplanMatMapper.selectByPrimaryKey(record);
        PageInfo<MatplanMatBean> result = new PageInfo<MatplanMatBean>(mmNonbidExpertList);
        return result;
	}
	
	@Override
	public int updateByPrimaryKeySelective(MatplanMatBean record) {
		return matplanMatMapper.updateByPrimaryKeySelective(record);
	}

}
