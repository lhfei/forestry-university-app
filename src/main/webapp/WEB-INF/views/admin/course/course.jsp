<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/tabs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${globalTitle }</title>
	
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/layout-browser.css">
	<%-- <script type="text/javascript" src="${extRoot}/shared/options-toolbar.js"></script> --%>
	<script type="text/javascript" src="${basePath}/resources/modules/admin/course/app.js"></script>

</head>
<body style="background-color: #73A0CC;">
	
		<div id="div_academicYear" class="code x-hide-display">
			<span>学年:</span> 
			<select id="s_academicYear" name="academicYear">
				<option value="-1">全部学年</option>
				<c:forEach var="item" varStatus="index" items="${XN }">
					<%-- <option value='<c:out value="${item.code}"></c:out>'> --%>
					<option value='<c:out value="${item.code}"></c:out>'>
						<c:out value="${item.label}"></c:out></option>
				</c:forEach>
			</select>
		</div>
		
		<div id="div_semester" class="code x-hide-display">
			<span>学期:</span> 
			<select id="s_semester" name="semester">
				<option value="-1">全部学期</option>
				<c:forEach var="item" varStatus="index" items="${XQ }">
					<%-- <option value='<c:out value="${item.code}"></c:out>'> --%>
					<option value='<c:out value="${item.code}"></c:out>'>
					<c:out value="${item.label}"></c:out></option>
				</c:forEach>
			</select> 
		</div>
	
</body>
</html>