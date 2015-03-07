<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/tabs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${globalTitle }</title>
	
	<script type="text/javascript" src="${basePath }/resources/modules/student/hwork/app.js"></script>
	
</head>
<body>
		<%-- <span style="padding: 0 0 5px 0">作业状态:</span> <select id="s_status">
			<c:forEach var="item" varStatus="index" items="${statusList }">
				<option value='<c:out value="${item.code}"></c:out>'><c:out
						value="${item.text}"></c:out></option>
			</c:forEach>
		</select>  --%>
		
		<div id="div_academicYear" class="code x-hide-display">
			<span>学年:</span> 
			<select id="s_academicYear" name="academicYear" alt="学年">
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
		
		<div id="div_courseName" class="code x-hide-display">
			<span>课程名称:</span> 
			<select id="s_courseName" name="courseName" >
				<option value="-1" selected>全部课程</option>
				<c:forEach var="item" varStatus="index" items="${KC }">
					<%-- <option value='<c:out value="${item.id}"></c:out>'> --%>
					<option value='<c:out value="${item.courseName}"></c:out>'>
					<c:out value="${item.courseName}"></c:out></option>
				</c:forEach>
			</select> 
		</div>
		
		<div id="div_className" class="code x-hide-display" >
			<span>班级:</span> 
			<select id="s_className" name="className" >
				<option value="-1" selected>请选择课程</option>
			</select>
		</div>
</body>
</html>