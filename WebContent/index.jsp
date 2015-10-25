<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="js/editor/themes/default/default.css" />
	<link rel="stylesheet" href="js/editor/plugins/code/prettify.css" />
	<script charset="utf-8" src="js/editor/kindeditor.js"></script>
	<script charset="utf-8" src="js/editor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="js/editor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="info"]', {
				cssPath : '../plugins/code/prettify.css',
				uploadJson : '<%=basePath%>file_upload',//上传
                fileManagerJson : "<%=basePath%>file_manager",//文件管理
				allowFileManager : true,
				afterCreate : function() {
					this.sync();
				}
			});
			prettyPrint();
		});
	</script>
  </head>
	<body>
		<a href="header.html">导航栏</a>
		<a href="navigationManage.jsp">导航管理</a>
		<form name="example" method="post" action="a.jsp">
		<textarea name="info" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
		<br />
		<input type="submit" name="button" value="提交内容" /> (提交快捷键: Ctrl + Enter)
	</form>
	</body>
</html>
