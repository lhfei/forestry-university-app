<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	String extRoot = basePath + "/resources/ext4"; 	
	request.setAttribute("basePath", basePath);
	request.setAttribute("extRoot", extRoot);
%>

	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

	<!-- GC -->
	<script type="text/javascript" src="${extRoot}/shared/include-ext.js?theme=neptune"></script>
    <script type="text/javascript" src="${extRoot}/locale/ext-lang-zh_CN.js"></script>
	

	<!-- Common Styles for the examples -->
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/btn-icon.css">
	
