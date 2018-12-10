package com.yyc.oper.nobid.service.mdict;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.mdict.MdictBean;

/**
 * 
 * Description:数据字典 service接口   
 * @author hlg
 * @date 2018年9月26日
 */
public interface IMdictService {

	/**
	 * 分页查询所有字典数据
	 * @param mdictBean
	 * @return
	 */
	PageInfo<MdictBean> getMdicts(MdictBean mdictBean);

	/**
	 * 新增数据字典
	 * @param mdictBean
	 */
	void addMdict(MdictBean mdictBean);

	/**
	 * 根据IDS集合删除字典数据
	 * @param list
	 */
	void deleteMdict(List<String> list);

	/**
	 * 修改字典数据
	 * @param mdictBean
	 */
	void updateMaict(MdictBean mdictBean);

	/**
	 * 根据类型查询字典数据集合
	 * @param keyword
	 * @return
	 */
	List<Map<String, Object>> findMdictsByType(String keyword);

}
