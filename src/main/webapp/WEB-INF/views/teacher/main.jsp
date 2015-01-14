<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../commons/tabs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${globalTitle }</title>
	
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/layout-browser.css">
	<script type="text/javascript" src="${extRoot}/shared/options-toolbar.js"></script>
	<script type="text/javascript" src="${basePath}/resources/modules/teacher/app.js"></script>

</head>
<body style="background-color: #73A0CC;">
	<div id="header_user_info" class="x-hide-display" style="width: 100%;padding: 5px 10px 0 0; text-align: right">
		当前登录: <c:out value="${userName }"></c:out>
	</div>
	
	<div id="header_title_img" class="x-hide-display">
		<img alt="" src="${basePath }/resources/images/head-mini.gif">
	</div>
	
	<div class="x-hide-display" id="south" style="width: 100%;padding: 5px 10px 0 0; text-align: right">
		Copyright 2015 Beijing Forestry University All Rights Reserved.
	</div>
	
	<input id="_userName" type="hidden" value='<c:out value="${userName }"></c:out>' />
</body>
</html>