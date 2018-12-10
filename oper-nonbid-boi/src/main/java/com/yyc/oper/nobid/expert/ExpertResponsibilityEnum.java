package com.yyc.oper.nobid.expert;

/**
 * Description:履责评价枚举类   
 * @author hlg
 * @date 2018年9月17日
 */
public enum ExpertResponsibilityEnum {
	ONE(1, "setProjectNum", "项目编号"), 
	TWO(2, "setProjectName", "项目名称"), 
	THREE(3, "setGroupId", "组别"),
	FOUR(4,"setRole", "角色"),
	five(5,"setName", "姓名 "),
	SIX(6,"setBusinessAbility", "业务能力"),
	SEVEN(7,"setWorkAttitude", "工作态度"),
	eight(8,"setHonestDiscipline", "廉洁纪律"),
	NINE(9,"setEvalConclusion", "评价总结"),
	TEN(10,"setEvalCountTime", "评价统计时间"),
	ELEVEN(11,"setEvalCountBy", "评价统计人"),
	TWELVE(12,"setRemark", "备注"),
	THIRTEEN(13,"setEvalStartTime", "评价开始时间"),
	FOURTEEN(14,"setEvalEndTime", "评价结束时间");

	private int index;//考试评价ID
	
	private String setMethod; //对应方法
	
	private String headerName; //表头

	private ExpertResponsibilityEnum(int index, String setMethod, String headerName) {
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
