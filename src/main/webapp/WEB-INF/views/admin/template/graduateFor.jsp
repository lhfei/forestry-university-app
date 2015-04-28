<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/tabs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${globalTitle }</title>
	
	<style type="text/css">
		.list {
		    padding-left: 16px;
		    padding-bottom: 10px;
		}
		.list li{
		    list-style: square;
		    padding: 2px;
		}
		
		pre {
		    font-size: 11px; 
		}	
	</style>
	
	<script type="text/javascript">
		Ext.require('Ext.tab.*');
	
		Ext.onReady(function(){
		    var tabs = Ext.widget('tabpanel', {
		        renderTo: 'tabs1',
		        autoWidth: true,
		        activeTab: 0,
		        defaults :{
		            bodyPadding: 10
		        },
		        items: [{
		            contentEl:'script', 
		            title: '研究生-基本信息',
		            closable: false
		        }]
		    });
		    
		    var tabs2 = Ext.widget('tabpanel', {
		    	renderTo: 'tabs2',
		        activeTab: 0,
		        autoWidth: true,
		        plain: false,
		        defaults :{
		            autoScroll: true,
		            bodyPadding: 10
		        },
		        items: [{
		            contentEl:'script2', 
		            title: '研究生-论文模板',
		            closable: false
		        }]
		        
		    });
		    
		});

	</script>
	
</head>
<body>
    <!-- container for the existing markup tabs -->
    <div id="tabs1">
        <div id="script" class="x-hide-display">
            <p>
            	<ul class="list">
            		<li> <a href="${basePath}/resources/template/excel/研究生-基本信息.xlsx"><h3>研究生-基本信息模板下载</h3></a> </li>
            	</ul>
            </p>
        </div>
    </div>
    <br>
    
	<div id="tabs2">
        <div id="script2" class="x-hide-display">
            <p>
            	<ul class="list">
            		<li> <a href="${basePath}/resources/template/excel/研究生-论文模板.xlsx"><h3>研究生-论文模板模板下载</h3></a> </li>
            	</ul>
            </p>
        </div>
    </div>
    <br>
    
</body>
</html>