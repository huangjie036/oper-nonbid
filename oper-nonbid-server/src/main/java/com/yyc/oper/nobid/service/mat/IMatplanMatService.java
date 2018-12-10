package com.yyc.oper.nobid.service.mat;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.mat.MatplanMatBean;

public interface IMatplanMatService {

	int deleteByPrimaryKey(String id);
	
	int insert(List<MatplanMatBean> list);

    int insertSelective(MatplanMatBean record);

    PageInfo<MatplanMatBean> selectByPrimaryKey(MatplanMatBean record);

    int updateByPrimaryKeySelective(MatplanMatBean record);

}
