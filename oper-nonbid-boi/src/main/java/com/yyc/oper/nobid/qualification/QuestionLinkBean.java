package com.yyc.oper.nobid.qualification;

import java.io.Serializable;
import java.util.Date;
/**
 * Description:问题发生环节bean   
 * @author hlg
 * @date 2018年9月18日
 */
public class QuestionLinkBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private String questionLinkId;

    private String questionLinkName;

    private String orderNum;

    private String states;

    private String createBy;

    private Date createTime;

    private String opeBy;

    private Date opeTime;

    private String del;

    public String getQuestionLinkId() {
        return questionLinkId;
    }

    public void setQuestionLinkId(String questionLinkId) {
        this.questionLinkId = questionLinkId == null ? null : questionLinkId.trim();
    }

    public String getQuestionLinkName() {
        return questionLinkName;
    }

    public void setQuestionLinkName(String questionLinkName) {
        this.questionLinkName = questionLinkName == null ? null : questionLinkName.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states == null ? null : states.trim();
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

    public Date getOpeTime() {
        return opeTime;
    }

    public void setOpeTime(Date opeTime) {
        this.opeTime = opeTime;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del == null ? null : del.trim();
    }
}