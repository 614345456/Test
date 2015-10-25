package com.javasj.share.basic.model;

/**
 * 公共参数
 * 
 * @author Wangxq
 * 
 */
public final class CommonAttributes {

	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** share.xml文件路径 */
	public static final String SHARE_XML_PATH = "/share.xml";

	/** shopxx.properties文件路径 */
	public static final String SHOPXX_PROPERTIES_PATH = "/shopxx.properties";

	/**
	 * 不可实例化
	 */
	private CommonAttributes() {
	}

}