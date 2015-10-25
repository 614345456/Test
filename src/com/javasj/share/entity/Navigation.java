package com.javasj.share.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Entity-导航
 * 
 * @author Wangxq
 *
 */
@Entity
@Table(name = "t_navigation")
public class Navigation {
	/**
	 * 导航编号
	 */
	private int id;
	/**
	 * 导航父编号
	 * 为0时候为一级导航
	 */
	private int parentId;
	/**
	 * 导航名称
	 */
	private String navigationName;
	/**
	 * 排序
	 */
	private int sort;
	/**
	 * 状态
	 * 1、可用2、不可用
	 */
	private int status;
	/**
	 * 是否是热门导航
	 * 0、正常1、热门
	 */
	private int isHot;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="parent_id", nullable = false)
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	@Column(name="navigation_name", nullable = false)
	public String getNavigationName() {
		return navigationName;
	}
	public void setNavigationName(String navigationName) {
		this.navigationName = navigationName;
	}
	@Column(name="sort", nullable = false)
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	@Column(name="status", nullable = false)
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="is_hot", nullable = false)
	public int getIsHot() {
		return isHot;
	}
	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}
	
}
