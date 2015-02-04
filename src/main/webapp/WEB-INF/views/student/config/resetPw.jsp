<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/tabs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${globalTitle }</title>
	
	<script type="text/javascript" src="${basePath }/resources/modules/student/config/resetPw.js"></script>

	<style type="text/css">
		/* Styling of global error indicator */
		.form-error-state {
			font-size: 11px;
			padding-left: 20px;
			height: 16px;
			line-height: 18px;
			background-repeat: no-repeat;
			background-position: 0 0;
			cursor: default;
		}
		
		/* Error details tooltip */
		.errors-tip .error {
			font-style: italic;
		}
		
		.centered {
			position: fixed;
			top: 20%;
			left: 30%;
			margin-top: -10px;
			margin-left: -20px;
		}
</style>
    	
</head>
<body>
	
	<div id="centered" class="centered"></div>
	
	<input type="hidden" id="_userId" value="<c:out value="${id }"></c:out>" />
	
	
</body>
</html>