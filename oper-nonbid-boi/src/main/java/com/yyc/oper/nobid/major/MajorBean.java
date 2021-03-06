package com.yyc.oper.nobid.major;

import java.io.Serializable;
import java.util.List;

/**
 * Description:  专业信息类 
 * @author hlg
 * @date 2018年9月11日
 */
public class MajorBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private String majorId; //专业id

    private String majorName; //专业名称

    private String remark; //备注

    private String orderNo; //序号

    private String parentId; //父级id

    private String path; //路径

    private String createBy; //创建人

    private String createTime; //创建时间

    private String opeBy; //操作人

    private String opeTime; //操作时间

    private String del; //删除标记
    
    private List<MajorBean> children; //子节点

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
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

	public List<MajorBean> getChildren() {
		return children;
	}

	public void setChildren(List<MajorBean> children) {
		this.children = children;
	}

    
    
}