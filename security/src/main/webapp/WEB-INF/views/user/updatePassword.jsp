<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>小石权限系统 -修改密码</title>
<script type="text/javascript">
	$(function() {
		$.myAjaxForm("form", {
			url : "${pageContext.request.contextPath}/user/smUpdatePassword",
			success : function(result) {
				if (result.success) {
					parent.layer.alert(result.msg, {
						icon: 6,
		                shadeClose: false,
		                closeBtn: 0,
		                title: "成功",
		                yes : function() {
							window.top.location.href = '${pageContext.request.contextPath}/logout';
		                }
					});
				} else {
					parent.$.myLayer.alertError(result.code, result.msg);
				}
			},
			methods : [
					{
						name : "newPassword_reg",
						method : function(v, e, p) {
							return /^[A-Za-z0-9*]{6,18}$/.test(v);
						},
						message : errorIocn + "新密码必须为6-18位，支持字母、数字、“*”"
					}],
			rules : {
				oldPassword : {
					required : true
				},
				newPassword : {
					required : true,
					newPassword_reg : true
				}
			},
			messages : {
				oldPassword : {
					required : errorIocn + "请输入原密码"
				},
				newPassword : {
					required : errorIocn + "请输入新密码"
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
						<form class="form-horizontal m-t" id="form">
							<div class="form-group">
								<label class="col-sm-3 control-label">原密码：</label>
								<div class="col-sm-8">
									<input name="oldPassword" type="password"
										class="form-control" value="">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">新密码：</label>
								<div class="col-sm-8">
									<input name="newPassword" type="password"
										class="form-control" value="">
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
