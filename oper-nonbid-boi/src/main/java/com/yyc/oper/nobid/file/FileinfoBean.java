package com.yyc.oper.nobid.file;

import java.io.Serializable;
import java.util.Date;

import com.yyc.oper.nobid.base.BasePageBean;

public class FileinfoBean extends BasePageBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private String id;//id

    private String businessId;//操作的业务id

    private String functionName;//功能名称

    private String originalName;//原文件名

    private String newName;//新文件名称

    private String size;//文件大小

    private String path;//文件路径

    private Integer downloadcount;//下载数量

    private String extension;//扩展名

    private String matplanId;//计划id

    private String purchaseSchmeId;//方案id

    private String orgId;//组织id

    private String createBy;//生成人

    private Date createTime;//生成时间

    private String opeBy;//操作人
    
    private String del;//删除标记

    private Date opeTime;//操作时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName == null ? null : functionName.trim();
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName == null ? null : originalName.trim();
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName == null ? null : newName.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getDownloadcount() {
        return downloadcount;
    }

    public void setDownloadcount(Integer downloadcount) {
        this.downloadcount = downloadcount;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension == null ? null : extension.trim();
    }

    public String getMatplanId() {
        return matplanId;
    }

    public void setMatplanId(String matplanId) {
        this.matplanId = matplanId == null ? null : matplanId.trim();
    }

    public String getPurchaseSchmeId() {
        return purchaseSchmeId;
    }

    public void setPurchaseSchmeId(String purchaseSchmeId) {
        this.purchaseSchmeId = purchaseSchmeId == null ? null : purchaseSchmeId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
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
		this.del = del;
	}
    
}