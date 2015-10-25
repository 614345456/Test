package com.javasj.share.service;

import java.util.List;

import com.javasj.share.basic.model.Pager;
import com.javasj.share.entity.Source;

public interface SourceService {
	void addSource(Source source);
	void delSource(int id);
	void updateSource(Source source);
	Source loadSorce(int id);
	List<Source> sourceList(Source source);
	Pager<Source> sourcePager(Source sorce);
}
