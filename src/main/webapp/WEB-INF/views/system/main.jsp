<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../commons/tabs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${globalTitle }</title>
	
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/layout-browser.css">
	<%-- <script type="text/javascript" src="${extRoot}/shared/options-toolbar.js"></script> --%>
	<script type="text/javascript" src="${basePath}/resources/modules/system/app.js"></script>

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
	
	<!-- Start page content -->
    <div id="start-div" class="x-hide-display" style="width: 100%; ">
        <div style="float:left;" ><img src="${basePath }/resources/images/icon/layout-icon.gif" /></div>
        <div style="margin-left:100px;">
            <h2>Welcome!</h2>
            <p>There are many sample layouts to choose from that should give you a good head start in building your own
            application layout.  Just like the combination examples, you can mix and match most layouts as
            needed, so don't be afraid to experiment!</p>
            <p>Select a layout from the tree to the left to begin.</p>
        </div>
    </div>
    
     <div id="script" class="x-hide-display">
            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed metus nibh, sodales a, porta at, vulputate
            eget, dui. Pellentesque ut nisl. Maecenas tortor turpis, interdum non, sodales non, iaculis ac, lacus.
            <br/><br/>
            Vestibulum auctor, tortor quis iaculis malesuada, libero lectus bibendum purus, sit amet tincidunt quam turpis
            vel lacus. In pellentesque nisl non sem. Suspendisse nunc sem, pretium eget, cursus a, fringilla vel, urna.</p>
        </div>
</body>
</html>