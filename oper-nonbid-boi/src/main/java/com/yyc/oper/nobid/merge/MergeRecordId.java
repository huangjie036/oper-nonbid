package com.yyc.oper.nobid.merge;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

public class MergeRecordId extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mergeId;//合包id

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}
	
	
}
