package com.yyc.oper.nobid.expert;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

/**
 * Description:考试评价
 * @author hlg
 * @date 2018年9月15日
 */
public class ExpertExamEvalBean extends BasePageBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String workDepartment; //工作部门

    private String idNum; //身份证号码

    private String expertName; //专家姓名 

    private String majorExamResults; //专业考试成绩

    private String comprehensiveExamResults; //综合考试成绩 

    private String evalOpinion; //评价意见	

    private String reportDepartment;//提报部门

    private String examDate;//考试时间

    private String examPlace; //考试地点

    private String evalCountTime; //评价统计时间

    private String evalCountBy; //评价统计人

    private String remark; //备注

    private String createBy; //创建人

    private String createTime; //创建时间

    private String opeBy; //操作人

    private String opeTime; //操作时间

    private String del; //删除标记  0：有效    1：删除

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWorkDepartment() {
        return workDepartment;
    }

    public void setWorkDepartment(String workDepartment) {
        this.workDepartment = workDepartment == null ? null : workDepartment.trim();
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum == null ? null : idNum.trim();
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName == null ? null : expertName.trim();
    }

    public String getMajorExamResults() {
        return majorExamResults;
    }

    public void setMajorExamResults(String majorExamResults) {
        this.majorExamResults = majorExamResults == null ? null : majorExamResults.trim();
    }

    public String getComprehensiveExamResults() {
        return comprehensiveExamResults;
    }

    public void setComprehensiveExamResults(String comprehensiveExamResults) {
        this.comprehensiveExamResults = comprehensiveExamResults == null ? null : comprehensiveExamResults.trim();
    }

    public String getEvalOpinion() {
        return evalOpinion;
    }

    public void setEvalOpinion(String evalOpinion) {
        this.evalOpinion = evalOpinion == null ? null : evalOpinion.trim();
    }

    public String getReportDepartment() {
        return reportDepartment;
    }

    public void setReportDepartment(String reportDepartment) {
        this.reportDepartment = reportDepartment == null ? null : reportDepartment.trim();
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate == null ? null : examDate.trim();
    }

    public String getExamPlace() {
        return examPlace;
    }

    public void setExamPlace(String examPlace) {
        this.examPlace = examPlace == null ? null : examPlace.trim();
    }

    public String getEvalCountTime() {
        return evalCountTime;
    }

    public void setEvalCountTime(String evalCountTime) {
        this.evalCountTime = evalCountTime == null ? null : evalCountTime.trim();
    }

    public String getEvalCountBy() {
        return evalCountBy;
    }

    public void setEvalCountBy(String evalCountBy) {
        this.evalCountBy = evalCountBy == null ? null : evalCountBy.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        this.createTime = createTime;
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

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}