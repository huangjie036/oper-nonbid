package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.major.MajorBean;
/**
 * 
 * Description:专业类数据操作dao
 * @author hlg
 * @date 2018年9月11日
 */
@Mapper
public interface MajorMapper {
    int deleteByPrimaryKey(String majorId);

    int insert(MajorBean record);

    int insertSelective(MajorBean record);

    List<MajorBean> selectByPrimaryKey(MajorBean record);

    int updateByPrimaryKeySelective(MajorBean record);

    int updateByPrimaryKey(MajorBean record);

	List<MajorBean> getMajorList();

	/**
	 * 根据专业名称查询
	 * @param majorName
	 * @return
	 */
	List<MajorBean> getMajorByMajorName(String majorName);

	/**
	 * 逻辑删除专业
	 * @param map
	 * @return 
	 * @return
	 */
	 void deleteById(String majorId);

	 /**
	  * 根据ID查询该节点下是否还有子节点
	  * @param map
	  * @return
	  */
	int getIsChildNode(String majorId);

	/**
	 * 条件查询专业数据
	 * @param majorBean
	 * @return
	 */
	//MajorBean seletMajorBycondition(MajorBean majorBean);
}