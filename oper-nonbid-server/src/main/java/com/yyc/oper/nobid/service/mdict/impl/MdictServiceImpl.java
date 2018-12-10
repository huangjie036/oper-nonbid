package com.yyc.oper.nobid.service.mdict.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyc.brace.base.principal.CurrentPrincipalHolder;
import com.yyc.oper.nobid.mapper.MdictMapper;
import com.yyc.oper.nobid.mdict.MdictBean;
import com.yyc.oper.nobid.service.mdict.IMdictService;
import com.yyc.oper.nobid.util.GenerateUUID;

/**
 * 
 * Description:数据字典维护 service 实现   
 * @author hlg
 * @date 2018年9月26日
 */
@Service
public class MdictServiceImpl implements IMdictService {

	
	private static final Logger log = LoggerFactory.getLogger(MdictServiceImpl.class);

	@Autowired
	private MdictMapper mdictMapper;

	@Override
	public PageInfo<MdictBean> getMdicts(MdictBean mdictBean) {
		PageHelper.startPage(mdictBean.getPageNum(), mdictBean.getPageSize());
		List<MdictBean> mdictBeans = mdictMapper.selectMadicts();
		PageInfo<MdictBean> pageInfo = new PageInfo<>(mdictBeans);
		return pageInfo;
	}

	@Override
	public void addMdict(MdictBean mdictBean) {
		String uid = CurrentPrincipalHolder.getUid();
		if(null == uid || "".equals(uid)) {
			throw new RuntimeException("未获取操作用户信息,新增失败");
		}
		mdictBean.setId(GenerateUUID.getUUID());
		mdictBean.setCreateBy(CurrentPrincipalHolder.getUid());
		mdictBean.setUpdateBy(CurrentPrincipalHolder.getUid());
		mdictBean.setUpdateDate(new Date());
		
		mdictMapper.insertSelective(mdictBean);
	}

	@Override
	public void deleteMdict(List<String> list) {
		for (String id : list) {
			mdictMapper.deleteMdictById(id);
		}
	}

	@Override
	public void updateMaict(MdictBean mdictBean) {
		String uid = CurrentPrincipalHolder.getUid();
		if(null == uid || "".equals(uid)) {
			throw new RuntimeException("未获取操作用户信息,编辑失败");
		}
		mdictBean.setUpdateBy(CurrentPrincipalHolder.getUid());
		mdictBean.setUpdateDate(new Date());
		mdictMapper.updateByPrimaryKeySelective(mdictBean);
		
	}

	@Override
	public List<Map<String, Object>> findMdictsByType(String keyword) {
		return mdictMapper.selectMdictsByType(keyword);
	}
	
}
