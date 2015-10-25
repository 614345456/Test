package com.javasj.share.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javasj.share.basic.model.Pager;
import com.javasj.share.dao.impl.BaseDaoImpl;
import com.javasj.share.entity.Source;
import com.javasj.share.service.SourceService;
@Service("sourceServiceImpl")
public class SourceServiceImpl extends BaseDaoImpl<Source> implements SourceService {

	@Override
	public void addSource(Source source) {
		this.add(source);
	}

	@Override
	public void delSource(int id) {
		this.delete(id);
	}

	@Override
	public void updateSource(Source source) {
		this.add(source);
	}

	@Override
	public Source loadSorce(int id) {
		return this.load(id);
	}

	@Override
	public List<Source> sourceList(Source source) {
		return null;
	}

	@Override
	public Pager<Source> sourcePager(Source sorce) {
		// TODO Auto-generated method stub
		return null;
	}

}
