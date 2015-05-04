<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/tabs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${globalTitle }</title>
	
	<script type="text/javascript" src="${basePath }/resources/modules/admin/thesis/app.js"></script>
	
</head>
<body>
		
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
		
		<div id="div_className" class="code x-hide-display">
			<span>班级:</span> 
			<select id="s_className" name="className" >
				<option value="-1" selected>全部班级</option>
				<c:forEach var="item" varStatus="index" items="${CLASS }">
					<option value='<c:out value="${item.label}"></c:out>'>
					<c:out value="${item.label}"></c:out></option>
				</c:forEach>				
			</select>
		</div>

		<div id="div_thesisType" class="code x-hide-display">
			<span>论文类型:</span> 
			<select id="s_thesisType" name="thesisType" >
				<option value="-1" selected>全部状态</option>
				<option value="0">设计</option>
				<option value="1">论文</option>
			</select>
		</div>
		
		<div id="div_origin" class="code x-hide-display">
			<span>论文来源:</span> 
			<select id="s_origin" name="origin" >
				<option value="-1" selected>全部状态</option>
				<option value="1">科研</option>
				<option value="2">生产</option>
				<option value="3">模拟</option>
				<option value="0">其它</option>
			</select>
		</div>
		
		<!-- ========== Upload Form =========== -->
		<div id="div_thesisType2" class="code x-hide-display">
			<span>论文类型:</span> 
			<select id="combo_thesisType" name="thesisType" >
				<option value="-1" selected> -- 请选择 -- </option>
				<option value="0">设计</option>
				<option value="1">论文</option>
			</select>
		</div>
		
		<div id="div_origin2" class="code x-hide-display">
			<span>论文来源:</span> 
			<select id="combo_origin" name="origin" >
				<option value="-1" selected> -- 请选择 -- </option>
				<option value="1">科研</option>
				<option value="2">生产</option>
				<option value="3">模拟</option>
				<option value="0">其它</option>
			</select>
		</div>
		
		<div id="div_className" class="code x-hide-display">
			<span>指导老师:</span> 
			<select id="combo_teacher" name="teacherName" >
				<option value="-1" selected> -- 请选择 -- </option>
				<c:forEach var="item" varStatus="index" items="${ZTJS }">
					<option value='<c:out value="${item.teacherId}"></c:out>'>
						<c:out value="${item.teacherName}"></c:out>
					</option>
				</c:forEach>				
			</select>
		</div>
		<!-- ========== Upload Form =========== -->
		
		<div id="div_degree" class="code x-hide-display">
			<span>学科阶段:</span> 
			<select id="s_degree" name="degree" >
				<option value="-1" selected>全部状态</option>
				<option value="0">本科</option>
				<option value="1">研究生</option>
			</select>
		</div>	
		
		<div id="div_status" class="code x-hide-display">
			<span>论文状态:</span> 
			<select id="s_status" name="status" >
				<option value="-1" selected>全部状态</option>
				<option value="0">未提交</option>
				<option value="1">待审核</option>
				<option value="2">已审核</option>
				<option value="3">未通过</option>
			</select>
		</div>
</body>
</html>