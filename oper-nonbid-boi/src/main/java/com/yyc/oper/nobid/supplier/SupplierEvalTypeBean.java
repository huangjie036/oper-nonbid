package com.yyc.oper.nobid.supplier;

import java.io.Serializable;
import java.util.Date;

import com.yyc.oper.nobid.base.BasePageBean;

/**
 * Description:  评价分类bean
 * @author hlg
 * @date 2018年9月19日
 */
public class SupplierEvalTypeBean extends BasePageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
	private String supplierEvalTypeId;

    private String evalTypeName;//分类名称

    private String orderNum;//序号

    private String states;//状态

    private String questionLinkId;//问题发生id

    private String createBy;//创建人

    private Date createTime;//创建时间

    private String opeBy;//操作人

    private Date opeTime;//操作时间

    private String del;//删除标记

    public String getSupplierEvalTypeId() {
        return supplierEvalTypeId;
    }

    public void setSupplierEvalTypeId(String supplierEvalTypeId) {
        this.supplierEvalTypeId = supplierEvalTypeId == null ? null : supplierEvalTypeId.trim();
    }

    public String getEvalTypeName() {
        return evalTypeName;
    }

    public void setEvalTypeName(String evalTypeName) {
        this.evalTypeName = evalTypeName == null ? null : evalTypeName.trim();
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

    public String getQuestionLinkId() {
        return questionLinkId;
    }

    public void setQuestionLinkId(String questionLinkId) {
        this.questionLinkId = questionLinkId == null ? null : questionLinkId.trim();
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