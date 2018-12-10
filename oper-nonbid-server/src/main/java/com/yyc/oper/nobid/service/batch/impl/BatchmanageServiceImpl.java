package com.yyc.oper.nobid.service.batch.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.yyc.brace.base.principal.CurrentPrincipalHolder;
import com.yyc.oper.nobid.batch.BatchmanageBean;
import com.yyc.oper.nobid.mapper.BatchmanageMapper;
import com.yyc.oper.nobid.service.batch.IBatchmanageService;
import com.yyc.oper.nobid.util.GenerateUUID;

@Service
public class BatchmanageServiceImpl implements IBatchmanageService {

	@Autowired
	private BatchmanageMapper batchmanageMapper;

	@Override
	public int deleteByPrimaryKey(String batchId) {
		return batchmanageMapper.deleteByPrimaryKey(batchId);
	}

	@Override
	public int insertSelective(BatchmanageBean record) {

//		record.setCreateBy(CurrentPrincipalHolder.getUid());// 创建人 只有新增才有创建人
//		Date currentDate = new Date();
//		record.setCreateTime(currentDate);// 创建时间  只有新增才有 
//		record.setOperBy(CurrentPrincipalHolder.getUid());//新增的时候 操作人也是创建人 ，修改或删除其他操作 为操作人。
//		record.setOperTime(currentDate);//新增操作时间 和新增时间一致    修改或者删除 以及其他 操作  时间为操作时间
//		record.setOrgId(CurrentPrincipalHolder.getAttribute("orgId").toString());//组织机构
		record.setBatchId(GenerateUUID.getUUID());
		if (record.getBeginTime().before(record.getEndTime())) {// 开始时间小于结束时间
			BatchmanageBean temp = new BatchmanageBean();
			temp.setBatchNum(record.getBatchNum());
			temp.setCreateBy(CurrentPrincipalHolder.getUid());// 创建人 只有新增才有创建人
			Date currentDate = new Date();
			temp.setCreateTime(currentDate);// 创建时间  只有新增才有 
			temp.setOperBy(CurrentPrincipalHolder.getUid());//新增的时候 操作人也是创建人 ，修改或删除其他操作 为操作人。
			temp.setOperTime(currentDate);//新增操作时间 和新增时间一致    修改或者删除 以及其他 操作  时间为操作时间
			List<BatchmanageBean> listBatchmanageBean = batchmanageMapper.selectByPrimaryKey(temp);
			if (listBatchmanageBean.size() == 0) {
				return batchmanageMapper.insertSelective(record);
			}
			return -2;
		}
		return -1;
	}

	@Override
	public PageInfo<BatchmanageBean> selectByPrimaryKey(BatchmanageBean record) {
		
		List<BatchmanageBean> mmNonbidExpertList = batchmanageMapper.selectByPrimaryKey(record);
		PageInfo<BatchmanageBean> result = new PageInfo<BatchmanageBean>(mmNonbidExpertList);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(BatchmanageBean record) {
		Date currentDate = new Date();
		record.setOperBy(CurrentPrincipalHolder.getUid());//新增的时候 操作人也是创建人 ，修改或删除其他操作 为操作人。
		record.setOperTime(currentDate);//新增操作时间 和新增时间一致    修改或者删除 以及其他 操作  时间为操作时间
		return batchmanageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BatchmanageBean record) {
		int result = 0;
		for (int i = 0; i < record.getBatchIds().length; i++) {
			BatchmanageBean temp = new BatchmanageBean();
			temp.setBatchId(record.getBatchIds()[i]);
			temp.setState(record.getState());
			Date currentDate = new Date();
			temp.setOperBy(CurrentPrincipalHolder.getUid());//新增的时候 操作人也是创建人 ，修改或删除其他操作 为操作人。
			temp.setOperTime(currentDate);//新增操作时间 和新增时间一致    修改或者删除 以及其他 操作  时间为操作时间
			result += batchmanageMapper.updateByPrimaryKeySelective(temp);
		}
		return result == 0 ? -1 : result;
	}

	@Override
	public int updateByBatchIds(String[] batchIds) {
		int result = 0;
		BatchmanageBean record = new BatchmanageBean();
		record.setDel("1");
		for (int i = 0; i < batchIds.length; i++) {
			record.setBatchId(batchIds[i]);
			Date currentDate = new Date();
			record.setOperBy(CurrentPrincipalHolder.getUid());//新增的时候 操作人也是创建人 ，修改或删除其他操作 为操作人。
			record.setOperTime(currentDate);//新增操作时间 和新增时间一致    修改或者删除 以及其他 操作  时间为操作时间
			result += batchmanageMapper.updateByPrimaryKeySelective(record);
		}
		return result == 0 ? -1 : result;
	}

	@Override
	public List<BatchmanageBean> findListByState(BatchmanageBean record) {
		List<BatchmanageBean> list = batchmanageMapper.selectByPrimaryKey(record);
		return list;
	}
}
