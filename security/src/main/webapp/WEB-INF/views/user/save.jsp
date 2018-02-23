<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../comm/comm.jsp">
	<jsp:param value="layer" name="plugin"/>
	<jsp:param value="iCheck" name="plugin"/>
	<jsp:param value="chosen" name="plugin"/>
	<jsp:param value="validate" name="plugin"/>
	<jsp:param value="jquery-form" name="plugin"/>
</jsp:include>
<title>小石权限系统 -新增用户</title>
<script type="text/javascript">
	$(function() {
		$("select").chosen();
		$('.i-checks').iCheck({
			checkboxClass : 'icheckbox_square-green',
			radioClass : 'iradio_square-green',
		});

		$.myAjaxForm("form", {
			url : "${pageContext.request.contextPath}/user/smSave",
			success : function(result) {
				if (result.success) {
					parent.$.myLayer.alertSuccess(result.msg);
					parent.$.myBootstrapTable.save("#list", result.data);
					$("form").myResetForm();
				} else {
					parent.$.myLayer.alertError(result.code, result.msg);
				}
			},
			methods : [
					{
						name : "username_reg",
						method : function(v, e, p) {
							return /^[\w-]{2,18}$/.test(v);
						},
						message : errorIocn + "用户名必须为2-18位，支持字母、数字、“_”、”-“"
					},
					{
						name : "realname_reg",
						method : function(v, e, p) {
							return /^[\u4E00-\u9FA5\w-]{2,10}$/.test(v);
						},
						message : errorIocn + "真实姓名必须为2-10位,支持中文、字母、数字、“_”、”-“"
					},
					{
						name : "phone_reg",
						method : function(v, e, p) {
							return /^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\d{8}$/.test(v);
						},
						message : errorIocn + "请输入正确的手机号"
					},
					{
						name : "email_reg",
						method : function(v, e, p) {
							return /^([a-z0-9A-Z]+[-|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/.test(v);
						},
						message : errorIocn + "请输入正确的邮箱"
					} ],
			rules : {
				username : {
					required : true,
					username_reg : true
				},
				realname : {
					required : true,
					realname_reg : true
				},
				phone : {
					required : true,
					phone_reg : true
				},
				email : {
					required : true,
					email_reg : true
				}
			},
			messages : {
				username : {
					required : errorIocn + "请输入用户名"
				},
				realname : {
					required : errorIocn + "请输入真实姓名"
				},
				phone : {
					required : errorIocn + "请输入手机号"
				},
				email : {
					required : errorIocn + "请输入邮箱"
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
							<div class="form-group">
								<label class="col-sm-3 control-label">用户名：</label>
								<div class="col-sm-8">
									<input name="username" type="text"
										class="form-control" value="">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">真实姓名：</label>
								<div class="col-sm-8">
									<input name="realname" type="text"
										class="form-control" value="">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">手机号：</label>
								<div class="col-sm-8">
									<input name="phone" type="text" class="form-control"
										value="">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">邮箱：</label>
								<div class="col-sm-8">
									<input name="email" type="text" class="form-control"
										value="">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">状态：</label>
								<div class="col-sm-8">
									<select class="chosen-select" name="status" style="width: 95%;"
										tabindex="2">
										<option value="1">有效</option>
										<option value="0">无效</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">角色：</label>
								<div class="col-sm-8">
									<c:forEach items="${roleList }" var="role">
										<label class="checkbox-inline i-checks">
											<input type="checkbox" name="role" value="${role.id }">${role.name}
										</label>
									</c:forEach>
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
