<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.afterBooking.edit")} - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<style type="text/css">
	.specificationSelect {
		height: 100px;
		padding: 5px;
		overflow-y: scroll;
		border: 1px solid #cccccc;
	}
	
	.specificationSelect li {
		float: left;
		min-width: 150px;
		_width: 200px;
	}
</style>
<script type="text/javascript">
$().ready(function() {
      var $inputForm =$("#inputForm");
      var $areaId =$("#areaId");
	  $areaId.lSelect({
		url: "${base}/common/area.jhtml"
	   });
	// 表单验证
	$inputForm.validate({
		rules: {
			sn: "required",
			name: "required",
			phone: "required",
			areaId: "required",
			manager: "required",
			managerPhone: "required"
		}
	});
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo;修改服务网点
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
	<input type="hidden" name="entCode" class="text" value="macro" />
	<input type="hidden" name="id" class="text" value="${outlet.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>网点编号:
				</th>
				<td>
					<input type="text" id="sn" name="sn" class="text" maxlength="200" value="${outlet.sn}"/>
				</td>
			</tr>
			<tr>
				<th>
				   <span class="requiredField">*</span>网点名称:
				</th>
				<td>
				    <input type="text"  name="name" class="text" maxlength="200" value="${outlet.name}"/>
				</td>
			</tr>
			<tr>
				<th>
				   <span class="requiredField">*</span>网点类别:
				</th>
				<td>
				    <input type="text" id="unitType" name="unitType" class="text" maxlength="200" value="${outlet.unitType}"/>
				</td>
			</tr>
			<tr>
				<th>
				   <span class="requiredField">*</span>网点级别:
				</th>
				<td>
				    <input type="text" id="unitlevel" name="unitlevel" class="text" maxlength="200" value="${outlet.unitlevel}"/>
				</td>
			</tr>
			<tr>
				<th>
				   <span class="requiredField">*</span>中心编号:
				</th>
				<td>
				    <input type="text" id="centerSn" name="centerSn" class="text" maxlength="100" value="${outlet.centerSn}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>总机：
				</th>
				<td>
					<input type="text" name="phone" class="text" maxlength="200" value="${outlet.phone}"/>
				</td>
			</tr>
			<tr>
				<th>
					报装电话:
				</th>
				<td>
					<input type="text" name="installBookPhone" class="text" maxlength="11" value="${outlet.installBookPhone}" />
				</td>
			</tr>
			<tr>
				<th>
					报修电话:
				</th>
				<td>
					<input type="text" name="repairBookPhone" class="text" maxlength="11" value="${outlet.repairBookPhone}" />
				</td>
			</tr>
			<tr>
				<th>
					报装查询电话:
				</th>
				<td>
					<input type="text" name="installQueryPhone" class="text" maxlength="11" value="${outlet.installQueryPhone}" />
				</td>
			</tr>
			<tr>
				<th>
					保修查询电话:
				</th>
				<td>
					<input type="text" name="repairQueryPhone" class="text" maxlength="11" value="${outlet.repairQueryPhone}" />
				</td>
			</tr>
			<tr>
				<th>
					投诉电话:
				</th>
				<td>
					<input type="text" name="complaintPhone" class="text" maxlength="11" value="${outlet.complaintPhone}"  />
				</td>
			</tr>
			<tr>
				<th>
					传真:
				</th>
				<td>
					<input type="text" name="fax" class="text" maxlength="9" value="${outlet.fax}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>负责人:
				</th>
				<td>
					<input type="text" name="manager" class="text" maxlength="50" value="${outlet.manager}"/>
				</td>
			</tr>
			<tr>
				<th>
					负责人编号:
				</th>
				<td>
					<input type="text" name="managerSn" class="text" maxlength="200" value="${outlet.managerSn}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>负责人电话:
				</th>
				<td>
					<input type="text" name="managerPhone" class="text" maxlength="11" value="${outlet.managerPhone}"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>产品品类:
				</th>
				<td>
				[@product_category_root_list entcode="macro" useCache="false"]
				[#list productCategories as category]
                  <label>
						<input type="checkbox" name="productCategoryIds" value="${category.id}"
						  [#list outlet.productCategorys as productCategory]
						     [#if productCategory.id==category.id]checked="checked"[/#if]
						  [/#list]
						/>${category.name}
				  </label>
                [/#list]
				[/@product_category_root_list]	
				</td>
			</tr>
			<tr>
				<th>
					网点所在地:
				</th>
				<td>
				 <span class="fieldSet">
			    	<input type="hidden" id="areaId" name="areaId" value="${(outlet.area.id)!}"  treePath="${(outlet.area.treePath)!}" />
				 </span>
				</td>
			</tr>
			<tr>
				<th>
					详细地址:
				</th>
				<td>
					<input type="text" name="address" class="text" maxlength="200" value="${outlet.address}"/>
				</td>
			</tr>
			<tr>
				<th>
					服务区域:
				</th>
				<td>
					<input type="text" name="serviceArea" class="text" maxlength="200" value="${outlet.serviceArea}"/>
				</td>
			</tr>
			<tr>
				<th>
					邮编:
				</th>
				<td>
					<input type="text" name="zipCode" class="text" maxlength="6" value="${outlet.zipCode}"/>
				</td>
			</tr>
			<tr>
				<th>
					工作时间:
				</th>
				<td>
					<input type="text" name="workDate" class="text" maxlength="200" value="${outlet.workDate}" />
				</td>
			</tr>
			<tr>
				<th>
					网点介绍:
				</th>
				<td>
					<input type="text" name="introduction" class="text" value="${outlet.introduction}"/>
				</td>
			</tr>
			<tr>
				<th>
					状态:
				</th>
				<td>
					<input type="text" name="state" class="text" maxlength="200" value="${outlet.state}" />
				</td>
			</tr>
			<tr>
				<th>
					备注:
				</th>
				<td>
					<input type="text" name="remark" class="text" maxlength="200" value="${outlet.remark}" />
				</td>
			</tr>
			
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>