package com.javasj.share.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Entity-资源
 * @author Wangxq
 *
 */
@Entity
@Table(name = "t_source")
public class Source {
	/**
	 * 资源编号
	 */
	private int id;
	/**
	 * 资源名称
	 */
	private String sourceName;
	/**
	 * 资源上传时间
	 */
	private Date publicDate;
	/**
	 * 资源作者或来源
	 */
	private String author;
	/**
	 * 资源更新时间
	 */
	private Date updateDate;
	/**
	 * 资源简介
	 */
	private String introduction;
	/**
	 * 排序
	 */
	private int sort;
	/**
	 * 是否推荐
	 * 0、正常1、推荐
	 */
	private int isRecommendation;
	/**
	 * 点击次数
	 */
	private long clickCount;
	/**
	 * 状态
	 * 1、正常2、关闭资源
	 */
	private int status;
	/**
	 * 资源图片
	 */
	private String picture;
	/**
	 * 资源地址
	 */
	private String sourceUrl;
	/**
	 * 资源是否免费
	 */
	private int isFree;
	/**
	 * 资源价格
	 */
	private double sourcePrice;
	/**
	 * 下载次数
	 */
	private long dowloadCount;
	/**
	 * 导航信息
	 */
	private Navigation navigation;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="source_name", nullable = false)
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	@Column(name="public_date", nullable = false)
	public Date getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name="update_date", nullable = false)
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Column(columnDefinition="longtext")
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Column(name="sort", nullable = false)
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	@Column(name="is_recommendation", nullable = false)
	public int getIsRecommendation() {
		return isRecommendation;
	}
	public void setIsRecommendation(int isRecommendation) {
		this.isRecommendation = isRecommendation;
	}
	@Column(name="click_count", nullable = false)
	public long getClickCount() {
		return clickCount;
	}
	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}
	@Column(name="status", nullable = false)
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	@Column(name="source_url", nullable = false)
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	@Column(name="is_free", nullable = false)
	public int getIsFree() {
		return isFree;
	}
	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}
	@Column(name="source_price")
	public double getSourcePrice() {
		return sourcePrice;
	}
	public void setSourcePrice(double sourcePrice) {
		this.sourcePrice = sourcePrice;
	}
	@Column(name="dowload_count")
	public long getDowloadCount() {
		return dowloadCount;
	}
	public void setDowloadCount(long dowloadCount) {
		this.dowloadCount = dowloadCount;
	}
	@ManyToOne
	@JoinColumn(name="navgation_id",nullable=false)
	public Navigation getNavigation() {
		return navigation;
	}
	public void setNavigation(Navigation navigation) {
		this.navigation = navigation;
	}
	@Override
	public String toString() {
		return "Source [id=" + id + ", sourceName=" + sourceName
				+ ", publicDate=" + publicDate + ", author=" + author
				+ ", updateDate=" + updateDate + ", introduction="
				+ introduction + ", sort=" + sort + ", isRecommendation="
				+ isRecommendation + ", clickCount=" + clickCount + ", status="
				+ status + ", picture=" + picture + ", sourceUrl=" + sourceUrl
				+ ", isFree=" + isFree + ", sourcePrice=" + sourcePrice
				+ ", dowloadCount=" + dowloadCount + ", navigation="
				+ navigation.getId() + "]";
	}
	
}
