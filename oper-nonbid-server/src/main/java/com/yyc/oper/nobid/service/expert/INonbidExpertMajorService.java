package com.yyc.oper.nobid.service.expert;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.expert.NonbidExpertMajorBean;

public interface INonbidExpertMajorService {

	int deleteByPrimaryKey(String id);

    int insertSelective(NonbidExpertMajorBean record);

    PageInfo<NonbidExpertMajorBean> selectByPrimaryKey(NonbidExpertMajorBean record);

    int updateByPrimaryKeySelective(NonbidExpertMajorBean record);

}
