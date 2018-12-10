package com.yyc.oper.nobid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.qualification.QuestionLinkBean;
/**
 * Description:问题发生环节 mapper
 * @author hlg
 * @date 2018年9月18日
 */
@Mapper
public interface QuestionLinkMapper {
    int deleteByPrimaryKey(String questionLinkId);

    int insert(QuestionLinkBean record);

    int insertSelective(QuestionLinkBean record);

    QuestionLinkBean selectByPrimaryKey(String questionLinkId);

    int updateByPrimaryKeySelective(QuestionLinkBean record);

    int updateByPrimaryKey(QuestionLinkBean record);

	List<Map<String,Object>> selectQuestionMaps();

	int selectQuestionByName(String questionLinkName);

	void deleteQuestion(String questionLinkId);
}