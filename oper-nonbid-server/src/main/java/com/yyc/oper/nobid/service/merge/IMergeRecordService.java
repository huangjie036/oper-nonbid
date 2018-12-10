package com.yyc.oper.nobid.service.merge;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.merge.MergeRecordBean;

public interface IMergeRecordService {

	int deleteByPrimaryKey(String mergeId);

    int insertSelective(MergeRecordBean record);

    PageInfo<MergeRecordBean> selectByPrimaryKey(MergeRecordBean record);

    int updateByPrimaryKeySelective(MergeRecordBean record);
    
    //int updateByPrimaryKey(String[] mergeIds, String bagNum, String qualification);

}
