package com.yyc.oper.nobid.util;

import com.github.pagehelper.PageInfo;

public class PageInfoCopyUtil{
	
	public static PageInfo pageInfoCopy(PageInfo source, PageInfo target) {
		target.setPageNum(source.getPageNum());
		target.setPageSize(source.getPageSize());
		target.setSize(source.getSize());
		target.setStartRow(source.getStartRow());
		target.setEndRow(source.getEndRow());
		target.setTotal(source.getTotal());
		target.setPages(source.getPages());
		target.setPrePage(source.getPrePage());
		target.setNextPage(source.getNextPage());
		target.setIsFirstPage(source.isIsFirstPage());
		target.setIsLastPage(source.isIsLastPage());
		target.setHasPreviousPage(source.isHasPreviousPage());
		target.setHasNextPage(source.isHasNextPage());
		target.setNavigatePages(source.getNavigatePages());
		target.setNavigateFirstPage(source.getNavigateFirstPage());
		target.setNavigateLastPage(source.getNavigateLastPage());
		target.setNavigatepageNums(source.getNavigatepageNums());
		return target;
	}
}
