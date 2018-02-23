<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>小石权限系统 -项目管理</title>
<jsp:include page="../comm/comm.jsp">
	<jsp:param value="layer" name="plugin"/>
	<jsp:param value="bootstrap-table" name="plugin"/>
</jsp:include>
<script type="text/javascript">
	$(function() {
		$('#list').bootstrapTable({
			url : "${pageContext.request.contextPath}/project/getByPage",
			method : "get",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			height : $(window).height() - 40,
			pageSize : 20,
			pageList : [ 20, 30, 50, 100, 'All' ],
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
	
	function save() {
		$.myLayer.open("添加项目", "500px", "85%", "${pageContext.request.contextPath}/project/save");
	}
	
	function update() {
		var id = $.myBootstrapTable.getId("#list");
		if (!id) {
			$.myLayer.alertError("请选择一条信息");
			return;
		}
		$.myLayer.open("修改项目", "500px", "85%", "${pageContext.request.contextPath}/project/update?id=" + id);
	}
	
	function del() {
		var id = $.myBootstrapTable.getId("#list");
		if (!id) {
			$.myLayer.alertError("请选择一条信息");
			return;
		}
		$.myLayer.confirm("删除操作", "您确定要删除这条记录吗?", function() {
			$.myAjax({
				url : "${pageContext.request.contextPath}/project/smDel",
				data : { "id" : id },
				success : function(result) {
					if (result.success) {
						$.myLayer.alertSuccess(result.msg);
						$.myBootstrapTable.delById("#list", id);
					} else {
						$.myLayer.alertError(result.code, result.msg);
					}
				}
			});
		}, 3);
	}
</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<div class="example-wrap">
							<div class="example">
								<div class="btn-group hidden-xs" id="exampleToolbar"
									role="group">
									<shiro:hasPermission name="project:save">
										<button type="button" class="btn btn-outline btn-default"
										onclick="save();">
										<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
										<span>新增</span>
									</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="project:update">
										<button type="button" class="btn btn-outline btn-default"
											onclick="update();">
											<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
											<span>修改</span>
										</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="project:del">
										<button type="button" class="btn btn-outline btn-default"
											onclick="del();">
											<i class="glyphicon glyphicon-remove" aria-hidden="true"></i>
											<span>删除</span>
										</button>
									</shiro:hasPermission>
								</div>
								<table id="list">
									<thead>
										<tr>
											<th data-field="state" data-radio="true"></th>
											<th data-formatter="$.myBootstrapTable.formatter.sequence">序号</th>
											<th data-field="id" data-sortable="true" data-visible="false">id</th>
											<th data-field="name" data-sortable="true">名称</th>
											<th data-field="projectUrl" data-sort-name="project_url">项目地址</th>
											<th data-field="reloadAuthUrl" data-sort-name="reload_auth_url">刷新权限地址</th>
											<th data-field="status" data-sortable="true" data-formatter="$.myBootstrapTable.formatter.status">状态</th>
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
