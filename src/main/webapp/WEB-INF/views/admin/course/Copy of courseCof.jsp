<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/tabs.jsp"%>
<%@ include file="../../commons/easyui.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${globalTitle }</title>
	
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/layout-browser.css">
	<script type="text/javascript" src="${extRoot}/locale/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${basePath}/resources/modules/admin/courseCofig/app.js"></script>

</head>
<body>
	<!--  -->
	<div id="combo_course" class="x-hide-display">
		<select class="easyui-combogrid" style="width:250px" data-options="
				panelWidth: 500,
				multiple: true,
				idField: 'itemid',
				textField: 'productname',
				url: '../resources/annex/datagrid_data1.json',
				method: 'get',
				columns: [[
					{field:'ck',checkbox:true},
					{field:'itemid',title:'Item ID',width:80},
					{field:'productname',title:'Product',width:120},
					{field:'listprice',title:'List Price',width:80,align:'right'},
					{field:'unitcost',title:'Unit Cost',width:80,align:'right'},
					{field:'attr1',title:'Attribute',width:200},
					{field:'status',title:'Status',width:60,align:'center'}
				]],
				fitColumns: true
			">
		</select>
	</div>
	
</body>
</html>