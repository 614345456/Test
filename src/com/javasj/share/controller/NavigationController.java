package com.javasj.share.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javasj.share.basic.controller.BaseController;
import com.javasj.share.basic.model.Pager;
import com.javasj.share.basic.model.SystemContext;
import com.javasj.share.basic.model.Template;
import com.javasj.share.entity.Navigation;
import com.javasj.share.service.NavigationService;
import com.javasj.share.service.StaticService;
import com.javasj.share.service.TemplateService;
import com.javasj.share.util.JsonUtil;

/**
 * Controller - 静态化
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/navigation")
public class NavigationController extends BaseController {
	@Resource(name = "navigationServiceImpl")
	private NavigationService navigationService;
	@Resource(name = "templateServiceImpl")
	private TemplateService templateService;
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;
	@Value("#{configProperties['template.loader_path']}")  
	private String[] templateLoaderPaths;
	/**
	 * 生成静态
	 */
	@SuppressWarnings("unchecked")
	public int build(String inpath) {
		SystemContext.setSort("sort");
		SystemContext.setOrder("asc");
		int buildCount = 0;
		List<Navigation> navigations=navigationService.navigationList(-1);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("navigations", navigations);
		Template template=templateService.get(inpath);
		buildCount += staticService.build(template, request.getSession().getServletContext().getRealPath(template.getStaticPath()), model);
		return buildCount;
	}
	@RequestMapping(value = "/addnavigation", method = RequestMethod.POST)
	@ResponseBody
	public String addNavigation(Navigation nav,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			navigationService.addNavigation(nav);
			result.put("addResult", true);
			result.put("staticResult", build("header")>0?true:false);
		} catch (Exception e) {
			result.put("addResult", false);
		}
		return ajaxJson(JsonUtil.getJsonString4JavaPOJO(result), response);
	}
	@RequestMapping(value = "/pagernavigation")
	@ResponseBody
	public String pagerNavigation(int parentId,String sort,String order,String page,String rows,HttpServletResponse response) {
		int index=page!=null&&!page.equals("")?Integer.parseInt(page):0;
		int size=rows!=null&&!rows.equals("")?Integer.parseInt(rows):0;
		if(sort!=null&&!sort.equals("")) SystemContext.setSort(sort);
		else SystemContext.removeSort();
		if(order!=null&&!order.equals(""))SystemContext.setOrder(order);
		else SystemContext.removeOrder();
		if(index!=0)SystemContext.setPageOffset(index);
		else SystemContext.removePageOffset();
		if(size!=0)SystemContext.setPageSize(size);
		else SystemContext.removePageSize();
		Map<String, Object> result = new HashMap<String, Object>();
		Pager<Navigation> listNavigation=navigationService.navigationPagers(parentId);
		result.put("rows", listNavigation.getDatas());
		result.put("page",1);
		result.put("total",listNavigation.getTotal());
		return ajaxJson(JsonUtil.getJsonString4JavaPOJO(result), response);
	}
	@RequestMapping(value = "/listnavigation")
	@ResponseBody
	public String listNavigation(int parentId,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Navigation> listNavigation=navigationService.navigationList(parentId);
		result.put("rows", listNavigation);
		result.put("page",1);
		result.put("total",listNavigation.size());
		return ajaxJson(JsonUtil.getJsonString4JavaPOJO(result), response);
	}
	@RequestMapping(value = "/delnavigation")
	@ResponseBody
	public String delNavigation(String ids,boolean delAll,HttpServletResponse response) {
		String[] id = null ;
		if(ids.length()>0){
			id=ids.split(",");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		String Message="";
		int delResult=navigationService.delNavigationById(id);
		if(delResult==NavigationService.HAD_NAVIGATION){
			//该导航下有二级导航
			//获取客户是否执行全部删除操作
			if(delAll){
				//执行全部删除操作
				//执行删除子导航操作
				if(navigationService.delNavigationByparentId(id)==NavigationService.DEL_SUCCESS){
					//如果删除成功
					Message+= "删除子导航成功";
					delResult=navigationService.delNavigationById(id);
					if(delResult==NavigationService.DEL_SUCCESS)Message+="删除该导航成功";result.put("status", true);
					Message+=build("header")>0?",静态化成功":",静态化失败";
				}
				else{
					Message+="删除子导航失败,未执行删除该导航";
					result.put("status", false);
				} 
			}else{
				//不执行删除操作提示用户信息，该导航下有子导航
				Message+="该导航下有子导航,是否继续删除？";
				result.put("status", false);
			}
		}else if(delResult==NavigationService.DEL_SUCCESS){
			//该导航下没有子导航执行删除
			Message+="删除该导航成功";
			Message+=build("header")>0?",静态化成功":",静态化失败";
			result.put("status", true);
		}
		result.put("Message",Message);
		return ajaxJson(JsonUtil.getJsonString4JavaPOJO(result), response);
	}
	@RequestMapping(value = "/loadnavigation")
	@ResponseBody
	public String loadNavigation(int id,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		Navigation navigation=navigationService.LoadNavigation(id);
		result.put("navigation",navigation);
		return ajaxJson(JsonUtil.getJsonString4JavaPOJO(result), response);
	}
	@RequestMapping(value = "/updatenavigation")
	@ResponseBody
	public String updateNavigation(Navigation nav,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			navigationService.updateNavigation(nav);
			if(build("header")>0){
				result.put("status",true);
			}
		} catch (Exception e) {
			result.put("status",false);
		}
		return ajaxJson(JsonUtil.getJsonString4JavaPOJO(result), response);
	}
}