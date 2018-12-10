package com.yyc.oper.nobid.service.supplier.impl;

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
import com.yyc.oper.nobid.mapper.SupplierEvalTypeMapper;
import com.yyc.oper.nobid.service.supplier.ISupplierEvalTypeService;
import com.yyc.oper.nobid.supplier.SupplierEvalTypeBean;
import com.yyc.oper.nobid.util.GenerateUUID;

/**
 * 评价分类实现类
 * Description:   
 * @author hlg
 * @date 2018年9月19日
 */
@Service
public class SupplierEvalTypeServiceImpl implements ISupplierEvalTypeService {
	
	private static final Logger log = LoggerFactory.getLogger(SupplierEvalTypeServiceImpl.class);

	@Autowired
	private SupplierEvalTypeMapper typeMapper;
	
	@Override
	public PageInfo<SupplierEvalTypeBean> getPageInfo(SupplierEvalTypeBean evalTypeBean) {
		
		List<SupplierEvalTypeBean> typeBeans =  typeMapper.selectTypePage(evalTypeBean);
		return new PageInfo<>(typeBeans);
	}

	@Override
	public void addType(SupplierEvalTypeBean evalTypeBean) throws Exception {
		String uid = CurrentPrincipalHolder.getUid();
		if(null == uid || "".equals(uid)) {
			throw new RuntimeException("未获取操作用户信息,新增失败");
		}
		evalTypeBean.setSupplierEvalTypeId(GenerateUUID.getUUID());
		evalTypeBean.setCreateBy(uid);
		evalTypeBean.setOpeBy(uid);
		evalTypeBean.setOpeTime(new Date());
		typeMapper.insert(evalTypeBean);
	}

	@Override
	public void editType(SupplierEvalTypeBean evalTypeBean) throws Exception {
		String uid = CurrentPrincipalHolder.getUid();
		if(null == uid || "".equals(uid)) {
			throw new RuntimeException("未获取操作用户信息,编辑失败");
		}
		evalTypeBean.setOpeBy(uid);
		evalTypeBean.setOpeTime(new Date());
		typeMapper.updateByPrimaryKeySelective(evalTypeBean);
	}

	@Override
	public void deleteType(List<String> ids) throws Exception {
		for (String id : ids) {
			typeMapper.deleteType(id);
		}
	}

	@Override
	public List<Map<String, Object>> selectTypeByLinkId(String questionLinkId) {
		List<Map<String, Object>> map = typeMapper.selectTypeByLinkId(questionLinkId);
		return map;
	}

}
