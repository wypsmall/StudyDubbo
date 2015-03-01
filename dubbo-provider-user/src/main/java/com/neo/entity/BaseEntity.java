package com.neo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @描述: 基础实体类，包含各实体公用属性 .
 * @作者: Donglx .
 * @创建时间: 2015-02-24,下午8:53:52 .
 * @版本: 1.0 .
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 主键ID **/
	private Long id;
	
	/** 版本号 **/
	private Integer version = 0;
	
	/** 创建时间 **/
	private Date createTime;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
