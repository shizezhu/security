<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>小石权限系统 -日志管理</title>
<jsp:include page="../comm/comm.jsp">
	<jsp:param value="layer" name="plugin"/>
	<jsp:param value="chosen" name="plugin"/>
	<jsp:param value="bootstrap-table" name="plugin"/>
</jsp:include>
<style type="text/css">
	.ibox-title .btn {
		margin-bottom: 0;
	}
	.ibox-title .form-group {
		margin-right: 10px;
	}
</style>
<script type="text/javascript">
	var projectId = null;
	var type = null;
	$(function() {
		$("select").chosen();
		initQueryParams();
		$('#list').bootstrapTable({
			url : "${pageContext.request.contextPath}/log/getByPage",
			method : "post",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			height : $(window).height() - 92,
			pageSize : 20,
			pageList : [ 20, 30, 50, 100, 'All' ],
			queryParams : queryParams,
			clickToSelect : true,
			singleSelect: true,
			cache : false,
			search : true,
			pagination : true,
			showRefresh : true,
			showToggle : true,
			showColumns : true,
			uniqueId : "id",
			sortName : "add_time",
			sortOrder : "desc",
			sidePagination : "server",
			iconSize : 'outline',
			toolbar : '#exampleToolbar'
		});
	});
	
	function queryParams(params) {
    	params.projectId = projectId;
    	params.type = type;
		return params;
    }
	
	function initQueryParams() {
		projectId = $("#projectId").val();
		type = $("#type").val();
    }
	
	function select() {
    	initQueryParams();
		$('#list').bootstrapTable("refresh");
    }
	
	function projectFormatter(value, row, index) {
		if (value == null) {
			return "-"
		}
		return value.name;
	}
	
	function userFormatter(value, row, index) {
		if (value == null) {
			return "-"
		}
		return value.username;
	}
	
	function typeFormatter(value, row, index) {
		if (value == 1) {
			return "登陆";
		}
		if (value == 2) {
			return "增";
		}
		if (value == 3) {
			return "改";
		}
		if (value == 4) {
			return "删";
		}
		if (value == 5) {
			return "查";
		}
		return "-";
	}
</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
	            <form class="form-inline">
					<div class="form-group">
						<label>系统:</label> <select id="projectId" name="projectId"
							style="width: 200px" tabindex="2">
							<option value="">-请选择-</option>
							<c:forEach items="${projectList }" var="project">
								<option value="${project.id }">${project.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>操作类型:</label> <select id="type" name="type"
							style="width: 150px" tabindex="2">
							<option value="">-请选择-</option>
							<option value="1">登陆</option>
							<option value="2">增</option>
							<option value="3">改</option>
							<option value="4">删</option>
							<option value="5">查</option>
						</select>
					</div>
					<button class="btn btn-white" type="button" onclick="select();">搜索</button>
				</form>
			</div>
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<div class="example-wrap">
							<div class="example">
								<div class="btn-group hidden-xs" id="exampleToolbar"
									role="group">
								</div>
								<table id="list">
									<thead>
										<tr>
											<th data-field="state" data-radio="true"></th>
											<th data-formatter="$.myBootstrapTable.formatter.sequence">序号</th>
											<th data-field="id" data-sortable="true" data-visible="false">id</th>
											<th data-field="project" data-formatter="projectFormatter" data-sort-name="project_id" data-sortable="true">项目</th>
											<th data-field="user" data-formatter="userFormatter" data-sort-name="user_id" data-sortable="true">用户</th>
											<th data-field="userIp">用户IP</th>
											<th data-field="name">模块名称</th>
											<th data-field="descr">操作描述</th>
											<th data-field="type" data-formatter="typeFormatter" data-sortable="true">操作类型</th>
											<th data-field="content" data-formatter="typeFormatter" data-visible="false">内容</th>
											<th data-field="addTime" data-sort-name="add_time" data-formatter="$.myBootstrapTable.formatter.dateTime" data-sortable="true">添加时间</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
