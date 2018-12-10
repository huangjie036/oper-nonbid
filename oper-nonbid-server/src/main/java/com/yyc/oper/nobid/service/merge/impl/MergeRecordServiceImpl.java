package com.yyc.oper.nobid.service.merge.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.mapper.MergeRecordMapper;
import com.yyc.oper.nobid.merge.MergeRecordBean;
import com.yyc.oper.nobid.service.merge.IMergeRecordService;

@Service
public class MergeRecordServiceImpl implements IMergeRecordService{
	
	@Autowired
	private MergeRecordMapper mergeRecordMapper;
	
	@Override
	public int deleteByPrimaryKey(String batchId) {
		return mergeRecordMapper.deleteByPrimaryKey(batchId);
	}
	
	@Override
	public int insertSelective(MergeRecordBean record) {
		return mergeRecordMapper.insertSelective(record);
	}
	
	@Override
	public PageInfo<MergeRecordBean> selectByPrimaryKey(MergeRecordBean record) {
		
        List<MergeRecordBean> mmNonbidExpertList = mergeRecordMapper.selectByPrimaryKey(record);
        PageInfo<MergeRecordBean> result = new PageInfo<MergeRecordBean>(mmNonbidExpertList);
        return result;
	}
	
	@Override
	public int updateByPrimaryKeySelective(MergeRecordBean record) {
		return mergeRecordMapper.updateByPrimaryKeySelective(record);
	}
/**
	@Override
	public int updateByPrimaryKey(String[] mergeIds, String bagNum, String qualification) {
		int result = -1;
		MergeRecordBean record = new MergeRecordBean();
		for(int i=0;i<mergeIds.length;i++) {
			record.seti
			mergeRecordMapper.insert(record)
		}
		return 0;
	}
	**/
}
