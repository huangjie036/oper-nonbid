package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yyc.brace.util.entity.FileInfoVO;
import com.yyc.oper.nobid.file.FileinfoBean;

@Mapper
public interface FileInfoCrudMapper {

	int saveFileInfo(FileInfoVO fileInfoVO);

	int insertFile(@Param("list") List<FileinfoBean> fileinfo);

	FileinfoBean selectByPrimaryKey(String id);

	List<FileinfoBean> selectFileList(String purchaseSchmeId);
	
	List<FileinfoBean> selectFileListByFileinfoBean(FileinfoBean FileinfoBean);

}
