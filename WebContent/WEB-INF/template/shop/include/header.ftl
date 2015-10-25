<html>
<head>
<meta http-equiv="content-type" content="charset=utf-8" />
<title>UP</title>

</head>
<body>
		<#list navigations as navigation>
		<#if (navigation.parentId=0)>
				<ul class="sjqy">
                    			<li>
						<a href="#">${navigation.navigationName}</a>
						
						<#list navigations as nav>
							<#if nav.parentId=navigation.id> 
							<ul>
								<li><a href="#">${nav.navigationName}</a></li>
							</ul>
							</#if>
						</#list>
					</li>
				</ul>
			</#if>
			
		</#list>
<!--头部结束--> 
</body>
</html>