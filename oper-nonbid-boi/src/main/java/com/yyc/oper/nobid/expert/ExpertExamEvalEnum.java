package com.yyc.oper.nobid.expert;

/**
 * 
 * Description:   
 * @author hlg
 * @date 2018年9月15日
 */
public enum ExpertExamEvalEnum {

	ONE(1, "setWorkDepartment", "工作部门"), 
	TWO(2, "setIdNum", "身份证号码"), 
	THREE(3, "setExpertName", "专家姓名"),
	FOUR(4,"setMajorExamResults", "专业考试成绩"),
	five(5,"setComprehensiveExamResults", "综合考试成绩 "),
	SIX(6,"setEvalOpinion", "评价意见"),
	SEVEN(7,"setReportDepartment", "提报部门"),
	eight(8,"setExamDate", "考试时间"),
	NINE(9,"setExamPlace", "考试地点"),
	TEN(10,"setEvalCountTime", "评价统计时间"),
	ELEVEN(11,"setEvalCountBy", "评价统计人"),
	TWELVE(12,"setRemark", "备注");

	private int index;//考试评价ID
	
	private String setMethod; //对应方法
	
	private String headerName; //表头

	private ExpertExamEvalEnum(int index, String setMethod, String headerName) {
		this.index = index;
		this.setMethod = setMethod;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getSetMethod() {
		return setMethod;
	}

	public void setSetMethod(String setMethod) {
		this.setMethod = setMethod;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	
	
}
