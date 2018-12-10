package com.yyc.oper.nobid.expert;

import java.io.Serializable;
import java.util.Date;

import com.yyc.oper.nobid.base.BasePageBean;
/**
 * 
 * Description:履责评价   
 * @author hlg
 * @date 2018年9月13日
 */
public class ExpertResponsibilityEvalBean extends BasePageBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String projectNum; //项目编号

    private String projectName;//项目名称

    private String groupId;//组别

    private String role;//角色

    private String name;//姓名

    private String businessAbility;//业务能力

    private String workAttitude;//工作态度

    private String honestDiscipline;//廉洁纪律

    private String evalConclusion;//评价总结

    private String evalCountBy;//评价统计人

    private String remark;//备注

    private String evalStartTime;//评价开始时间

    private String evalEndTime;//评价结束时间

    private String evalCountTime;//评价统计时间

    private String del;//删除标记

    private String createBy;//创建人

    private Date createTime;//创建时间

    private String opeBy;//操作人

    private String opeTime;//操作时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum == null ? null : projectNum.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBusinessAbility() {
        return businessAbility;
    }

    public void setBusinessAbility(String businessAbility) {
        this.businessAbility = businessAbility == null ? null : businessAbility.trim();
    }

    public String getWorkAttitude() {
        return workAttitude;
    }

    public void setWorkAttitude(String workAttitude) {
        this.workAttitude = workAttitude == null ? null : workAttitude.trim();
    }

    public String getHonestDiscipline() {
        return honestDiscipline;
    }

    public void setHonestDiscipline(String honestDiscipline) {
        this.honestDiscipline = honestDiscipline == null ? null : honestDiscipline.trim();
    }

    public String getEvalConclusion() {
        return evalConclusion;
    }

    public void setEvalConclusion(String evalConclusion) {
        this.evalConclusion = evalConclusion == null ? null : evalConclusion.trim();
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

    public String getEvalStartTime() {
        return evalStartTime;
    }

    public void setEvalStartTime(String evalStartTime) {
        this.evalStartTime = evalStartTime == null ? null : evalStartTime.trim();
    }

    public String getEvalEndTime() {
        return evalEndTime;
    }

    public void setEvalEndTime(String evalEndTime) {
        this.evalEndTime = evalEndTime == null ? null : evalEndTime.trim();
    }

    public String getEvalCountTime() {
        return evalCountTime;
    }

    public void setEvalCountTime(String evalCountTime) {
        this.evalCountTime = evalCountTime == null ? null : evalCountTime.trim();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
}