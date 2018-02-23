<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../comm/comm.jsp">
	<jsp:param value="layer" name="plugin"/>
	<jsp:param value="chosen" name="plugin"/>
	<jsp:param value="validate" name="plugin"/>
	<jsp:param value="jquery-form" name="plugin"/>
	<jsp:param value="jstree" name="plugin"/>
</jsp:include>
<title>小石权限系统 -修改项目</title>
<script type="text/javascript">
	$(function() {
		$("select").chosen();
		$.myAjaxForm("form", {
			url : "${pageContext.request.contextPath}/project/smUpdate",
			success : function(result) {
				if (result.success) {
					parent.$.myLayer.alertSuccess(result.msg);
					parent.$.myBootstrapTable.updateById("#list", result.data);
					$.myLayer.closeSelf();
				} else {
					parent.$.myLayer.alertError(result.code, result.msg);
				}
			},
			methods : [
					{
						name : "name_reg",
						method : function(v, e, p) {
							return /^[\u4E00-\u9FA5\w-]{1,15}$/.test(v);
						},
						message : errorIocn + "名称必须为1-15,支持中文、字母、数字、“_”、”-“"
					},{
						name : "projectUrl_reg",
						method : function(v, e, p) {
							return /^https?:\/\/[A-Za-z0-9]+.[A-Za-z0-9]+.[A-Za-z0-9]+(.[A-Za-z0-9]+)?[:0-9]?(\/[A-Za-z0-9_-]+)*$/.test(v);
						},
						message : errorIocn + "URL格式不正确，例：http://api.istorm.com.cn:8080/projectname"
					},{
						name : "reloadAuthUrl_reg",
						method : function(v, e, p) {
							return /^(\/[A-Za-z0-9_-]+)+$/.test(v);
						},
						message : errorIocn + "URI格式不正确，例：/hessian/reloadauth"
					}],
			rules : {
				name : {
					required : true,
					name_reg : true
				},
				projectUrl : {
					required : true,
					projectUrl_reg : true
				},
				reloadAuthUrl : {
					required : true,
					reloadAuthUrl_reg : true
				}
			},
			messages : {
				name : {
					required : errorIocn + "请输入名称"
				},
				projectUrl : {
					required : errorIocn + "请输入项目URL"
				},
				reloadAuthUrl : {
					required : errorIocn + "请输入刷新权限URI"
				}
			}
		});
	});
</script>
</head>
<body>
	<div class="wrapper animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form class="form-horizontal m-t">
							<input name="id" type="hidden" value="${project.id }">
							<div class="form-group">
								<label class="col-sm-3 control-label">名称：</label>
								<div class="col-sm-8">
									<input name="name" type="text"
										class="form-control" value="${project.name }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">URL：</label>
								<div class="col-sm-8">
									<input name="projectUrl" type="text"
										class="form-control" value="${project.projectUrl }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">刷新权限URI：</label>
								<div class="col-sm-8">
									<input name="reloadAuthUrl" type="text"
										class="form-control" value="${project.reloadAuthUrl }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">状态：</label>
								<div class="col-sm-8">
									<select name="status" style="width: 95%;"
										tabindex="2">
										<option ${project.status == true ? 'selected' : ''} value="1">有效</option>
										<option ${project.status == false ? 'selected' : ''} value="0">无效</option>
									</select>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
