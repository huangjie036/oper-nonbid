package com.yyc.oper.nobid.expert;

/**
 * Description:年度评价枚举类   
 * @author hlg
 * @date 2018年9月17日
 */
public enum ExpertSumEvalEnum {

	ONE(1, "setName", "姓名"), 
	TWO(2, "setAttendance", "出勤次数"), 
	THREE(3, "setGoodNum", "良好次数"),
	FOUR(4,"setCompetentNum", "称职次数"),
	five(5,"setBasicCompetentNum", "基本称职次数"),
	SIX(6,"setIncompetentNum", "不称职次数"),
	SEVEN(7,"setEducationNum", "教育培训次数"),
	EIGHT(8,"setComprehensiveEvalResult", "综合评价结果"),
	NINE(9,"setEvalCountBy", "评价统计人"),
	TEN(10,"setEvalCountTime", "评价统计时间"),
	ELEVEN(11,"setYear", "年度"),
	TWELVE(12,"setRemark", "备注");

	private int index;//考试评价ID
	
	private String setMethod; //对应方法
	
	private String headerName; //表头

	private ExpertSumEvalEnum(int index, String setMethod, String headerName) {
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
