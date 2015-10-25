package com.javasj.share.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javasj.share.basic.controller.ImgController;
import com.javasj.share.entity.Navigation;
import com.javasj.share.entity.Source;
import com.javasj.share.service.SourceService;
import com.javasj.share.service.StaticService;
import com.javasj.share.util.JsonUtil;
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/source")
public class SourceController extends ImgController{
	@Resource(name = "sourceServiceImpl")
	private SourceService sourceService;
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;
	@RequestMapping(value = "/addsource", method = RequestMethod.POST)
	@ResponseBody
	public String addSource(@RequestParam MultipartFile file,Source soc,int navigationId,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		soc.setClickCount(0);
		soc.setPublicDate(new Date());
		soc.setUpdateDate(new Date());
		if(!file.isEmpty()){
			Map<String, Object> uploadMap=imgupload(file, response);
			if((boolean) uploadMap.get("status")){
				soc.setPicture(uploadMap.get("path").toString());
			}
			else{
				result.put("error", uploadMap.get("error").toString());
				soc.setPicture(request.getContextPath()+"/images/defaultimg.jpg");
			} 
		}else{
			soc.setPicture(request.getContextPath()+"/images/defaultimg.jpg");
		}
		Navigation navigation=new Navigation();
		navigation.setId(navigationId);
		soc.setNavigation(navigation);
		try {
			sourceService.addSource(soc);
			result.put("addResult", true);
		} catch (Exception e) {
			result.put("addResult", false);
		}
		return ajaxJson(JsonUtil.getJsonString4JavaPOJO(result), response);
	}
}
