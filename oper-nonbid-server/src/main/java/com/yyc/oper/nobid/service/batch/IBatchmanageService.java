package com.yyc.oper.nobid.service.batch;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.batch.BatchmanageBean;

public interface IBatchmanageService {

	int deleteByPrimaryKey(String batchId);

    int insertSelective(BatchmanageBean record);

    PageInfo<BatchmanageBean> selectByPrimaryKey(BatchmanageBean record);

    int updateByPrimaryKeySelective(BatchmanageBean record);
    
    int updateByPrimaryKey(BatchmanageBean record);
    
    int updateByBatchIds(String[] batchIds);
    
    List<BatchmanageBean> findListByState(BatchmanageBean record);

}
