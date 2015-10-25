package com.javasj.share.test;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javasj.share.entity.Navigation;
import com.javasj.share.entity.Source;
import com.javasj.share.service.SourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestSource {
	@Autowired 
	SourceService sou;
	@Test
	public void testNavAdd() {
		Source source=new Source();
		source.setAuthor("张三");
		source.setIntroduction("测试信息");
		source.setIsFree(1);
		source.setIsRecommendation(1);
		source.setPicture("asd");
		source.setPublicDate(new Date());
		source.setUpdateDate(new Date());
		source.setStatus(1);
		source.setSourceUrl("www.baidu.com");
		source.setSourceName("SSH");
		source.setSort(12);
		Navigation navigation=new Navigation();
		navigation.setId(22);
		source.setNavigation(navigation);
		sou.addSource(source);
	}
}
