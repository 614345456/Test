<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>导航管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" href="js/jquery-easyui-1.3.3/themes/default/easyui.css" type="text/css"></link>
	<link rel="stylesheet" href="js/jquery-easyui-1.3.3/themes/icon.css" type="text/css"></link>
	<link href="<%=basePath %>css/styles.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
	$(function() {
		var datas;
		loadData();
		function loadData(){
			$.ajax({
				url : "navigation/listnavigation?parentId=-1",
				type : "POST",
				dataType : 'json',
				async : false,
				success : function(data) {
					datas=data;
				}
			});
		}
		$('#navigation_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/navigation/pagernavigation?parentId=-1',
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			checkOnSelect : true,
			selectOnCheck : false,
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				checkbox : true
			}, {
				field : 'navigationName',
				align:"left",
				title : '导航名称',
				width : 200,
				formatter:function(value,row,index){
					return value+'<span style="color:red;">（编号'+row.id+'）</span>'
				}
			} ] ],
			
			columns : [ [{
				field : 'parentId',
				title : '父类名称',
				align:"center",
				width : 100,
				formatter:function(value,row,index){
					if(value==0)return '一级导航';
					else
						if(datas==null){
							return''
						}else{
							for (var i = 0; i <datas.rows.length; i++) {
						        var r = datas.rows[i];
						        if(r.id==value){
						        	return r.navigationName;
						        }
							} 
						}
				}
			},{
				field : 'isHot',
				title : '是否热门',
				align:"center",
				width : 50,
				sortable : true,
				formatter:function(value){
					if(value==1)return '<span style="color:red;">热门</span>'
					else return '正常';
				}
			},{
				field : 'sort',
				title : '排序编号',
				align:"center",
				width : 50,
				sortable : true
			},{
				field : 'status',
				title : '状态',
				align:"center",
				width : 50,
				sortable : true,
				formatter:function(value){
					if(value==1)return '正常';
					else return '<span style="color:red;">关闭</span>';
				}
			}] ],
			toolbar : [ {
				text : '增加一级导航',
				iconCls : 'icon-add',
				handler : function() {
					$("#title").html("添加一级导航");
					$("#parentId").attr("disabled","false");
					$(".addtip").fadeIn(500);
				}
			}, '-', {
				text : '增加导航',
				iconCls : 'icon-add',
				handler : function() {
					loadParent();
					$("#title").html("添加导航");
					$("#parentId").removeAttr("disabled");
					$(".addtip").fadeIn(500);
				}
			}, '-',{
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					update();
				}
			}, '-' ]
			
		});
		
		function showMessagerDialog(message){
			$.messager.show({
				title : '提示',
				msg : message,
				showType:'fade',
				style:{
					 right:'',
					 bottom:''
				}
			});
		}
		function reload(){
			$('#navigation_datagrid').datagrid('unselectAll');
			$('#navigation_datagrid').datagrid('load');
		}
		function remove() {
			var rows = $('#navigation_datagrid').datagrid('getChecked');
			var ids = [];
			if (rows.length > 0) {
				$.messager.confirm('确认', '您是否要删除当前选中这<font color="red">'+rows.length+'</font>条数据？', function(r) {
					if (r) {
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/navigation/delnavigation',
							data : {
								ids : ids.join(',')
							},
							type : "POST",
							success : function(r) {
								if(r.status){
									loadData();
									reload();
									showMessagerDialog(r.Message);
								}else{
								 	$.messager.confirm('提示信息', '确认吗?'+r.Message, function(r){
								 		if(r){
								 			$.ajax({
												url : '${pageContext.request.contextPath}/navigation/delnavigation',
												data : {
													ids : ids.join(','),
													delAll:true,
												},
												dataType : 'json',
												success : function(r) {
													loadData();
													reload();
													showMessagerDialog(r.Message);
												}
												});
								 		}
									});
								}
							}
						});
					}
				});
			} else {
				showMessagerDialog("请勾选要删除的记录");
			}
		}
		function update(){
			var selectedRows=$("#navigation_datagrid").datagrid('getChecked');
			if(selectedRows.length!=1){
				$.messager.alert("系统提示","请选择一条要编辑的数据！");
				return;
			}
			var row=selectedRows[0];
			$("#navigationinfo").dialog("open").dialog("setTitle","请修改<font color='red'>"+row.navigationName+"</font>的信息");
			$("#navigationinfo_form").form("load",row);
			//url="${pageContext.request.contextPath}/navigation/loadnavigaton?id="+row.id;
		}
		
		$("#navigation_cal").click(function(){
			$("#navigationinfo").dialog("close");
		});
		
		$("#navigation_sub").click(function(){
			$.ajax({
                cache: true,
                type: "POST",
                url:"navigation/updatenavigation",
                data:$('#navigationinfo_form').serialize(),
                async: false,
                error: function(request) {
					$("#navigationinfo").dialog("close");
                	showMessagerDialog("更新失败");
                	$(".addtip").fadeOut(500);
                },
                success: function(data) {
                	$("#navigationinfo").dialog("close");
                	if(data.status){
                		showMessagerDialog("更新成功");
                	}else{
                		showMessagerDialog("更新失败");
                	}
                    loadData();
                	$('#navigation_datagrid').datagrid('load');
                }
            });
		});
		
		$(".tiptop a").click(function(){
			$(".addtip").fadeOut(500);
			reset();
		});
	  
		$(".cancel").click(function(){
			$(".addtip").fadeOut(500);
			reset();
		});
		$(".sure").click(function(){
			$.ajax({
                cache: true,
                type: "POST",
                url:"navigation/addnavigation",
                data:$('#nagigation').serialize(),
                async: false,
                error: function(request) {
                	showMessagerDialog("添加失败！<br/>请检查有效数据添加是否正确");
                	$(".addtip").fadeOut(500);
                },
                success: function(data) {
                	if(data.addResult){
                		if(data.staticResult){
                			showMessagerDialog("添加成功,静态化成功");
                		}else{
                			showMessagerDialog("添加成功,静态化失败");
                		}
                	}else{
                		showMessagerDialog("添加失败");
                	}
                	$(".addtip").fadeOut(500);
                	reset();
                    loadData();
                	$('#navigation_datagrid').datagrid('load');
                }
            });
		});
		function loadParent(){
				$('#parentId').html('');
				$('#parentId').append("<option value=0 selected='selected'>一级导航</option>");
			$.ajax({
				url : "navigation/listnavigation",
				data:"parentId=0",
				type : "POST",
				dataType : 'json',
				async : false,
				success : function(data) {
					if (data.rows.length > 0) {
						for (var i = 0; i <data.rows.length; i++) {
					        var rows = data.rows[i];
				    		var option="<option value="+rows.id+">"+rows.navigationName+"</option>";
				    		$('#parentId').append(option);
						} 
					}
				}
			});
		}
		function reset(){
			$("#nagigation")[0].reset();
		}
	});
</script>
  </head>
	<body>
		<table id="navigation_datagrid"  style="width:705px;height:400px"></table>
		<div class="addtip">
    		<div class="tiptop">
    			<span>提示信息</span>
    			<a></a>
    		</div>
    		<div class="tipinfo">
    			<form  method="post" id="nagigation">
		        <div class="tipright">
			        <p id="title"></p>
			        <p>
			        	<cite>	
			        		<span class="label label-required">
			        			请选择父类:<select id="parentId" name="parentId" >
			        					<option value=0 selected="selected">一级导航</option>
			        					</select> 
			        		</span>
			        	</cite>
			        </p> 
			        <p><cite>导航名称：<input type="text" name="navigationName" ></cite></p>
			        <p><cite>排序编号：<input type="text" name="sort" ></cite></p>	
			        <p>
			        	<cite>
				        	是否热门：
				        	<input type="radio" name="isHot" value=1 />是 
				        	<input type="radio" name="isHot" value=2 checked="checked"/>否
			        	</cite>
			        </p>
			        <p>
			        	<cite>
				        	状态：
				        	<input type="radio" name="status" value=1 checked="checked"/>启用
				        	<input type="radio" name="status" value=2 />关闭
			        	</cite>
			        </p>
		        </div>
		        </form>
			</div>
			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp;
				<input name="" type="button" class="cancel" value="取消" />
			</div>
    	</div>
    	
    	
    	<div id="navigationinfo" class="easyui-dialog" style="width: 350px;height: 200px;padding: 10px 20px"closed="true" >
		<form id="navigationinfo_form">
			<table>
				<tr>
					<th>导航名称:</th>
					<td>
						<input type="text" name="navigationName" />
						<input type="hidden" name="id" />
					</td>
				</tr>
				<tr>
					<th>状态:</th>
					<td>
						正常：<input type="radio" class="easyui-validatebox" name="status" value="1" />
						<font color="red">关闭：</font><input type="radio" name="status" value="2"/>
					</td>
				</tr>
				<tr>
					<th>父类编号:</th>
					<td><input type="text" class="easyui-validatebox" name="parentId" /></td>
				</tr>
				<tr>
					<th>是否热门:</th>
					<td>
						正常：<input type="radio" name="isHot" value="2" />
						<font color="red">热门：</font><input type="radio" name="isHot" value="1"/>
					</td>
				</tr>
				<tr>
					<th>序号:</th>
					<td><input type="text" class="easyui-validatebox" name="sort" /></td>
				</tr>
			</table>
			<div style="text-align:center;padding:5px">
				<a href="#" class="easyui-linkbutton" id="navigation_sub">更新</a>
				<a href="#" class="easyui-linkbutton" id="navigation_cal">取消</a>
			</div>
		</form>
</div>
	</body>
</html>