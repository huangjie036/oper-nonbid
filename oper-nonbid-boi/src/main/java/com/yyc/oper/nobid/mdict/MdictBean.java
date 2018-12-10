package com.yyc.oper.nobid.mdict;

import java.io.Serializable;
import java.util.Date;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * Description:数据字典维护   
 * @author hlg
 * @date 2018年9月26日
 */
@ApiModel(value="数据字典维护bean")/*对象描述*/
public class MdictBean  extends BasePageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(
			value="编号id",/*字段描述*/
			required = false/*是否必填:默认false*/
			)
	private String id;

	/**
	 * 父级ID
	 */
	@ApiModelProperty(value="父级ID-备用",required = false)
	private String parentId;

	/**
	 * 所有父级ID
	 */
	@ApiModelProperty(value = "所有父级ID-备用",required = false)
	private String parentIds;

	/**
	 * Key值
	 */
	@ApiModelProperty(value = "Key值",required = true)
	private String key;

	/**
	 * value值
	 */
	@ApiModelProperty(value = "value值",required = true)
	private String value;

	/**
	 * 数据类型
	 */
	@ApiModelProperty(value = "数据类型",required = true)
	private String keyword;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序",required = true)
	private Long sort;

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人",required = false)
	private String createBy;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间",dataType="Date",required = false)
	private Date createDate;

	/**
	 * 修改人
	 */
	@ApiModelProperty(value = "修改人",required = false)
	private String updateBy;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间",dataType="Date",required = false)
	private Date updateDate;

	/**
	 * 备注信息
	 */
	@ApiModelProperty(value = "备注信息",required = false)
	private String remarks;

	/**
	 * 删除标记 0:未删除  1:删除
	 */
	@ApiModelProperty(value = "删除标记 0:未删除  1:删除",required = false)
	private String del;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId == null ? null : parentId.trim();
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds == null ? null : parentIds.trim();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key == null ? null : key.trim();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value == null ? null : value.trim();
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword == null ? null : keyword.trim();
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del == null ? null : del.trim();
	}
}