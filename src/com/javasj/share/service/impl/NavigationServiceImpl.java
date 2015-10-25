package com.javasj.share.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.javasj.share.basic.model.Pager;
import com.javasj.share.dao.BaseDao;
import com.javasj.share.dao.impl.BaseDaoImpl;
import com.javasj.share.entity.Navigation;
import com.javasj.share.service.NavigationService;

@Service("navigationServiceImpl")
public class NavigationServiceImpl extends BaseDaoImpl<Navigation> implements NavigationService {
	@Resource(name="basedao")
	private BaseDao<Navigation> navdao;
	@Override
	public void addNavigation(Navigation nav) {
		navdao.add(nav);
	}
	@Override
	public List<Navigation> navigationList(int id) {
		String hql="from Navigation nav";
		if(id==-1){
			return this.list(hql);			
		}else{
			hql+=" where nav.parentId=?";
			return this.list(hql,id);
		}
	}
	@Override
	public Pager<Navigation> navigationPagers(int parentId) {
		String hql="from Navigation nav";
		if(parentId!=-1){
			hql+=" where nav.parentId =?";
			return this.find(hql,parentId);
		}else{
			return this.find(hql);
		}
	}

	@Override
	public int delNavigationById(String[] temp) {
		int ids[]=new int[temp.length];
		for(int i=0;i<temp.length;i++)ids[i]=Integer.parseInt(temp[i]);
		int status=0;
		try {
			for(int id:ids){
				String hql="from Navigation nav where nav.parentId=?";
				List<Navigation> navigations=this.list(hql,id);
				if(navigations.size()>0){
					status++;
				}
			}
			if(status>0){
				return HAD_NAVIGATION;
			}else{
				for(int id:ids)this.delete(id);
				return DEL_SUCCESS;
			} 
			
		} catch (Exception e) {
			return DEL_SYSTEM_ERROR;
		}
	}

	@Override
	public int delNavigationByparentId(String[] temp) {
		int parentIds[]=new int[temp.length];
		for(int i=0;i<temp.length;i++)parentIds[i]=Integer.parseInt(temp[i]);
		try {
			for(int parentId:parentIds){
				String hql=" from Navigation nav where nav.parentId=?";
				List<Navigation> navigations=this.list(hql,parentId);
				for(Navigation nav:navigations){
					this.delete(nav);
				}
			}
			return DEL_SUCCESS;
		} catch (Exception e) {
			return DEL_SYSTEM_ERROR;
		}
	}
	@Override
	public Navigation LoadNavigation(int id) {
		return this.load(id);
	}
	@Override
	public void updateNavigation(Navigation navigation) {
		this.update(navigation);
	}
}
