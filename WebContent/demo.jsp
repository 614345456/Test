<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
	<link rel="stylesheet" href="js/editor/themes/default/default.css" />
	<link rel="stylesheet" href="js/editor/plugins/code/prettify.css" />
	<script charset="utf-8" src="js/editor/kindeditor.js"></script>
	<script charset="utf-8" src="js/editor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="js/editor/plugins/code/prettify.js"></script>
</head>
<body>
	<%=htmlData%>
</body>
</html>
