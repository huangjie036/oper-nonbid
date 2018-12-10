package com.yyc.oper.nobid.expert;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

/**
 * Description: 年度评价  
 * @author hlg
 * @date 2018年9月15日
 */
public class ExpertSumEvalBean extends BasePageBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String name;//姓名

    private String attendance;//出勤次数

    private String goodNum;//良好次数

    private String competentNum;//称职次数

    private String basicCompetentNum;//基本称职次数

    private String incompetentNum;//不称职次数

    private String educationNum;//教育培训次数

    private String comprehensiveEvalResult;//综合评价结果

    private String evalCountBy;//评价统计人

    private String evalCountTime;//评价统计时间

    private String year;//年度

    private String remark;//备注

    private String del;//删除标记

    private String createBy;//创建人

    private String createTime;//创建时间

    private String opeBy;//操作人

    private String opeTime;//操作时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance == null ? null : attendance.trim();
    }

    public String getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum == null ? null : goodNum.trim();
    }

    public String getCompetentNum() {
        return competentNum;
    }

    public void setCompetentNum(String competentNum) {
        this.competentNum = competentNum == null ? null : competentNum.trim();
    }

    public String getBasicCompetentNum() {
        return basicCompetentNum;
    }

    public void setBasicCompetentNum(String basicCompetentNum) {
        this.basicCompetentNum = basicCompetentNum == null ? null : basicCompetentNum.trim();
    }

    public String getIncompetentNum() {
        return incompetentNum;
    }

    public void setIncompetentNum(String incompetentNum) {
        this.incompetentNum = incompetentNum == null ? null : incompetentNum.trim();
    }

    public String getEducationNum() {
        return educationNum;
    }

    public void setEducationNum(String educationNum) {
        this.educationNum = educationNum == null ? null : educationNum.trim();
    }

    public String getComprehensiveEvalResult() {
        return comprehensiveEvalResult;
    }

    public void setComprehensiveEvalResult(String comprehensiveEvalResult) {
        this.comprehensiveEvalResult = comprehensiveEvalResult == null ? null : comprehensiveEvalResult.trim();
    }

    public String getEvalCountBy() {
        return evalCountBy;
    }

    public void setEvalCountBy(String evalCountBy) {
        this.evalCountBy = evalCountBy == null ? null : evalCountBy.trim();
    }

    public String getEvalCountTime() {
        return evalCountTime;
    }

    public void setEvalCountTime(String evalCountTime) {
        this.evalCountTime = evalCountTime == null ? null : evalCountTime.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del == null ? null : del.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getOpeBy() {
        return opeBy;
    }

    public void setOpeBy(String opeBy) {
        this.opeBy = opeBy == null ? null : opeBy.trim();
    }

    public String getOpeTime() {
        return opeTime;
    }

    public void setOpeTime(String opeTime) {
        this.opeTime = opeTime == null ? null : opeTime.trim();
    }
}