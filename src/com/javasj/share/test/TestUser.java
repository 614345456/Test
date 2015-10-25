package com.javasj.share.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javasj.share.entity.Navigation;
import com.javasj.share.service.NavigationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestUser {
	@Autowired 
	NavigationService nav;
	@Test
	public void testNavAdd() {
		
		Navigation t=new Navigation();
		t.setNavigationName("Springmvc毕业设计");
		t.setParentId(7);
		t.setIsHot(0);
		t.setSort(72);
		t.setStatus(1);
		nav.addNavigation(t);
	}
	@Test
	public void testNavList() {
		List<Navigation> navList=nav.navigationPagers(1).getDatas();
		System.out.println(navList.size());
		for(Navigation navigation:navList){
			System.out.println(navigation.getNavigationName());
		}
	}
}
