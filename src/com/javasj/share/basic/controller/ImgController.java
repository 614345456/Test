package com.javasj.share.basic.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/basic")
@Controller
public class ImgController extends BaseController{
	@Value("#{configProperties['img_maxupload_size']}")  
	private String[] templateLoaderPaths;
	
	 @RequestMapping(value = "/img")
	 public Map<String, Object> imgupload(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletResponse response) {
		 Map<String, Object> msg = new HashMap<String, Object>();
		 boolean status=false;
		 String path = request.getSession().getServletContext().getRealPath("upload")+"/";  
		 String saveUrl = request.getContextPath() + "/upload/";
         // 定义允许上传的文件扩展名
         HashMap<String, String> extMap = new HashMap<String, String>();
         extMap.put("image", "gif,jpg,jpeg,png,bmp");
         // 最大文件大小
         long maxSize = Long.parseLong(templateLoaderPaths[0]);
         if (file==null) {
        	 msg.put("error", "请选择文件。");
        	 msg.put("status", status);
             return msg;
   
         }
         // 检查目录
         File uploadDir = new File(path);
         if (!uploadDir.isDirectory()) {
             msg.put("error", "上传目录不存在。");
             msg.put("status", status);
             return msg;
         }
         // 检查目录写权限
         if (!uploadDir.canWrite()) {
             msg.put("error", "上传目录没有写权限。");
             msg.put("status", status);
             return msg;
         }
   
         String dirName = request.getParameter("dir");
         if (dirName == null) {
             dirName = "image";
         }
         if (!extMap.containsKey(dirName)) {
             msg.put("error", "目录名不正确。");
             msg.put("status", status);
             return msg;
         }
         // 创建文件夹
         path += dirName + "/";
         saveUrl += dirName + "/";
         File saveDirFile = new File(path);
         if (!saveDirFile.exists()) {
             saveDirFile.mkdirs();
         }
         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
         String ymd = sdf.format(new Date());
         path += ymd + "/";
         saveUrl += ymd + "/";
         File dirFile = new File(path);
         if (!dirFile.exists()) {
             dirFile.mkdirs();
         }
   
         // 检查文件大小
         if (file.getSize() > maxSize) {
        	 msg.put("error", "上传文件大小超过限制。");
        	 msg.put("status", status);
        	 return msg;
         }
         // 检查扩展名
         String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
         if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
        	 msg.put("error", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
        	 msg.put("status", status);
        	 return msg;
         }
         SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
         String newFileName =df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
         try {
        	 	File uploadedFile = new File(path, newFileName);
        	 	System.out.println(uploadedFile.getPath());
        	 	file.transferTo(uploadedFile); 
                msg.put("status", true);
                msg.put("path", saveUrl+newFileName);
                return msg;
         } catch (Exception e) {
        	 msg.put("error", "上传文件失败。");
        	 msg.put("status", status);
        	 return msg;
         }
	 }
}
