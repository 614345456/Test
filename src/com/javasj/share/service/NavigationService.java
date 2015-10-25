package com.javasj.share.service;


import java.util.List;

import com.javasj.share.basic.model.Pager;
import com.javasj.share.entity.Navigation;

public interface NavigationService {
	public static final int DEL_SYSTEM_ERROR=-2;
	public static final int DEL_NAVIGATIONBYID_ERROR=-1;
	public static final int HAD_NAVIGATION=0;
	public static final int DEL_SUCCESS=1;
	/**
	 * 添加一个导航
	 * @param nav
	 */
	void addNavigation(Navigation nav);
	/**
	 * 查询全部导航列表
	 * @return
	 */
	List<Navigation> navigationList(int id);
	/**
	 * 根据父类编号查询导航列表
	 * @param parentId
	 * @return
	 */
	Pager<Navigation> navigationPagers(int parentId);
	/**
	 * 删除导航信息
	 * 根据编号查询该导航下是否含有二级导航
	 * @param id
	 * @return 有导航时候返回-1,删除成功返回0;
	 */
	int delNavigationById(String [] id);
	/**
	 * 根据父类编号删除导航
	 * @param parentId
	 * @return
	 */
	int delNavigationByparentId(String[] parentId);
	/**
	 * 根据id返回导航信息
	 * @param id
	 * @return
	 */
	Navigation LoadNavigation(int id);
	/**
	 * 更新导航
	 * @param navigation
	 */
	void updateNavigation(Navigation navigation);
}
