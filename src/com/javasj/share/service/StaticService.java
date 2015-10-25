package com.javasj.share.service;

import java.util.Map;

import com.javasj.share.basic.model.Template;


/**
 * Service - 静态化
 * 
 * @author Wangxq
 * 
 */
public interface StaticService<T> {

	/**
	 * 生成静态
	 * 
	 * @param templatePath
	 *            模板文件路径
	 * @param staticPath
	 *            静态文件路径
	 * @param model
	 *            数据
	 * @return 生成数量
	 */
	int build(String templatePath, String staticPath, Map<String, Object> model);

	/**
	 * 生成静态
	 * 
	 * @param templatePath
	 *            模板文件路径
	 * @param staticPath
	 *            静态文件路径
	 * @return 生成数量
	 */
	int build(String templatePath, String staticPath);
	/**
	 * 生成静态
	 * 
	 * @param template
	 * 			模板
	 * @param staticPath
	 * 			静态文件路径
	 * @param model
	 * 			数据
	 * @return 生成数量
	 */
	int build(Template template, String staticPath, Map<String, Object> model);
}