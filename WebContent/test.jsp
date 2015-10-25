<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="js/editor/themes/default/default.css" />
	<link rel="stylesheet" href="js/editor/plugins/code/prettify.css" />
	<script charset="utf-8" src="js/editor/kindeditor.js"></script>
	<script charset="utf-8" src="js/editor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="js/editor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="introduction"]', {
				cssPath : 'editor/plugins/code/prettify.css',
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

	<form action="source/addsource" method="post" enctype="multipart/form-data"> 
	<ul class="forminfo">
    	<li><label>资源名称<b>*</b></label><input name="sourceName" type="text"/></li>
    	<li><label>资源链接<b>*</b></label><input name="sourceUrl" type="text"/></li>
    	<li><label>作者</label><input name="author" type="text"/></li>
    	<li><label>封面</label>
			<input type='file'  id="headpic" name="file" />  
    		<div id="localImag"><img id="img_prev" src="images/defaultimg.jpg" alt="资源头像" /></div>
    	</li>
    	<li><label>是否免费<b>*</b></label>是<input name="isFree" type="radio" value=1 />否<input name="isFree" type="radio" value=0 /></li>
    	<li><label>状态<b>*</b></label>开<input name="status" type="radio" value=1 />关<input name="status" type="radio" value=2 /></li>
    	<li><label>是否推荐</label>是<input name="isRecommendation" type="radio" value=1 />否<input name="isRecommendation" type="radio" value=0 /></li>
   		<li><label>序号<b>*</b></label><input name="sort" type="text"/></li>
    	<li><label>说明</label>
			<textarea name="introduction" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
		</li>
		<li>
			<select id="navid"></select>
			<select id="ltypeid" name="navigationId"></select>
		</li>
    </ul>
    <input type="submit" value="提交">
    	
    </form>
    <script type="text/javascript">
    $(function(){
    	$("#headpic").change(function() {
    		readURL(this);
    	});
    });
    function readURL(input) {  
        if (input.files && input.files[0]) {  
            var reader = new FileReader();  
            reader.onload = function (e) { $('#img_prev').attr('src', e.target.result).width(500).height(500); };  
            reader.readAsDataURL(input.files[0]);  
        } else {  
            //IE下，使用滤镜  
            var docObj = document.getElementByIdx_x('headpic');  
            docObj.select();  
            //解决IE9下document.selection拒绝访问的错误  
            docObj.blur();  
            var imgSrc = document.selection.createRange().text;  
            var localImagId = document.getElementByIdx_x("localImag");  
            $('#localImag').width(500).height(500); //必须设置初始大小  
            //图片异常的捕捉，防止用户修改后缀来伪造图片  
            try {  
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;  
            } catch (e) {  
                alert("您上传的图片格式不正确，请重新选择!"); return false;  
            }  
            $('#img_prev').hide();  
            document.selection.empty();  
        }  
    }  
</script>
<script type="text/javascript">
	$.ajax({
		url : "navigation/listnavigation",
		data:"parentId=0",
		type : "POST",
		dataType : 'json',
		async : false,
		success : function(data) {
			var header = "<option value='-1'>---请选择---</option>";
			$('#navid').append(header);
			$('#ltypeid').hide();
			if (data.rows.length > 0) {
				for (var i = 0; i <data.rows.length; i++) {
			        var rows = data.rows[i];
		    		var option="<option value="+rows.id+">"+rows.navigationName+"</option>";
		    		$('#navid').append(option);
				} 
			}
		}
	});
	/**
	 * 级联读取一级分类的二级栏目
	 */
	$('#navid').change(function() {
		var navid = $('#navid').val();
		// navid=0表示顶级分类，navid=-1表示请选择，也就是当都不是这两个条件时执行一级栏目对应的下级栏目的搜索
		// 这里再读取二级分类内容
		if (navid != "0" && navid != "-1") {
			$.post("navigation/listnavigation", {
				"parentId" : navid
			}, function(data) {
				$('#ltypeid').html("<option value='-1'>---请选择---</option>");
				if (data.rows.length > 0) {
					for (var i = 0; i <data.rows.length; i++) {
				        var rows = data.rows[i];
			    		var option="<option value="+rows.id+">"+rows.navigationName+"</option>";
			    		$('#ltypeid').append(option);
					} 
				}
			});
		}
	});
	
	/**
	 * 验证分类选择
	 */
	$("#navid").change(function() {
		var navid = $('#navid').val();
		var ltypeid = $('#ltypeid').val();
		if (navid == '-1') {
			$('#ltypeid').hide();
		} else {
			$('#ltypeid').show();
		}
	});
	</script>
</body>
</html>