<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	String extRoot = basePath + "/resources/ext4"; 	
	request.setAttribute("basePath", basePath);
	request.setAttribute("extRoot", extRoot);
%>
	
