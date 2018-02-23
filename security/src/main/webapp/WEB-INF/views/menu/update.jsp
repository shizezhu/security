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
	<jsp:param value="awesome-bootstrap-checkbox" name="plugin"/>
</jsp:include>
<title>小石权限系统 -修改菜单</title>
<script type="text/javascript">
	$(function() {
		$("select").chosen();
		$.myAjaxForm("form", {
			url : "${pageContext.request.contextPath}/menu/smUpdate",
			success : function(result) {
				if (result.success) {
					parent.$.myLayer.alertSuccess(result.msg);
					var jstree = parent.$('#tree').jstree(true);
					jstree.close_all();
					jstree.deselect_all(true);
					var rootNode = jstree.get_node("0");
					jstree.refresh_node(rootNode);
					setTimeout(function() {
						var parentNode = jstree.get_node(result.data.parentMenu == null ? "0" : result.data.parentMenu.id);
						jstree.select_node(parentNode, false, false);
						jstree.open_node(parentNode);
						$.myLayer.closeSelf();
					}, 500);
				} else {
					parent.$.myLayer.alertError(result.code, result.msg);
				}
			},
			methods : [
						{
							name : "url",
							method : function(v, e, p) {
								var type = $("form input:checked[type='radio']").val();
								if (type == "2" || type == "3") {
									return v && /^(\/[A-Za-z0-9_-]+)+$/.test(v);
								} else {
									return true;
								}
							},
							message : errorIocn + "请输入正确的URI，例：/user/save"
						},{
							name : "authIden",
							method : function(v, e, p) {
								var type = $("form input:checked[type='radio']").val();
								if (type == "2" || type == "3") {
									return v;
								} else {
									return true;
								}
							},
							message : errorIocn + "请输入授权标识"
						},
						{
							name : "sort",
							method : function(v, e, p) {
								var type = $("form input:checked[type='radio']").val();
								if (type == "1" || type == "2") {
									return v;
								} else {
									return true;
								}
							},
							message : errorIocn + "请输入排序"
						}],
			rules : {
				name : {
					required : true
				},
				url : {
					url : true
				},
				authIden : {
					authIden : true
				},
				sort : {
					sort : true
				},
				projectId : {
					required : true
				}
			},
			messages : {
				name : {
					required : errorIocn + "请输入名称"
				},
				projectId : {
					required : errorIocn + "请选择项目"
				}
			}
		});
		
		$("form input[type='radio']").click(function() {
			var type = $("form input:checked[type='radio']").val();
			switch (type) {
			case "1":
				$("#url").parent().parent().hide();
				$("#authIden").parent().parent().hide();
				$("#icon").parent().parent().show();
				$("#sort").parent().parent().show();
				break;
			case "2":
				$("#url").parent().parent().show();
				$("#authIden").parent().parent().show();
				$("#icon").parent().parent().hide();
				$("#sort").parent().parent().show();
				break;
			case "3":
				$("#url").parent().parent().show();
				$("#authIden").parent().parent().show();
				$("#icon").parent().parent().hide();
				$("#sort").parent().parent().hide();
			break;
			default:
				break;
			}
		});
	});
	
	function menutree() {
		$.myLayer.open("选择父级菜单", "300px", "80%", "${pageContext.request.contextPath}/menu/menutree", function(index, layero) {
			var selectedNode = window[layero.find('iframe')[0]['name']].getSelected();
			layer.close(index);
			if (!selectedNode || selectedNode.length == 0) {
				return;
			}
			$("#parentId").val(selectedNode[0].id);
			$("#parentName").val(selectedNode[0].text);
		});
	}
	
	function toicon() {
		$.myLayer.open("选择图标", "300px", "98%", "${pageContext.request.contextPath}/menu/icon", function(index, layero) {
			$("#icon").val(layer.getChildFrame('body', index).find(".selected i").attr("class"));
			layer.close(index);
		});
	}
</script>
</head>
<body>
	<div class="wrapper animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form class="form-horizontal m-t">
							<input type="hidden" name="id" value="${menu.id }"/>
							<div class="form-group">
								<label class="col-sm-3 control-label">类型：</label>
								<div class="col-sm-8">
									<div class="radio radio-primary radio-inline" <c:if test="${menu != null && menu.type != 1 && isParent == 1}">style="display: none;"</c:if> >
                                        <input type="radio" id="type_directory" value="1" name="type" <c:if test="${menu == null || menu.type == 1}">checked="checked"</c:if> >
                                        <label style="color: #1c84c6;" for="type_directory">目录</label>
                                    </div>
                                    <div class="radio radio-success radio-inline"<c:if test="${menu.type != 2 && isParent == 1}">style="display: none;"</c:if> >
                                        <input type="radio" id="type_menu" value="2" name="type" <c:if test="${menu.type == 2}">checked="checked"</c:if> >
                                        <label style="color: #1ab394;" for="type_menu">页面</label>
                                    </div>
                                    <div class="radio radio-warning radio-inline"<c:if test="${menu.type != 3 && isParent == 1}">style="display: none;"</c:if> >
                                        <input type="radio" id="type_button" value="3" name="type" <c:if test="${menu.type == 3}">checked="checked"</c:if> >
                                        <label style="color: #f8ac59;" for="type_button">按钮</label>
                                    </div>
                            	</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">名称：</label>
								<div class="col-sm-8">
									<input name="name" type="text" class="form-control" value="${menu.name }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">父级：</label>
								<div class="col-sm-8">
									<input name="parentId" id="parentId" type="hidden" value="${menu.parentMenu == null ? '0' : menu.parentMenu.id}"/>
									<input name="parentName" id="parentName" type="text" 
									class="form-control" value="${menu.parentMenu == null ? '顶级' : menu.parentMenu.name}" 
									readonly="readonly" style="cursor: pointer;" onclick="menutree();">
								</div>
							</div>
							<div class="form-group" <c:if test="${menu == null || menu.type == 1}">style="display: none;"</c:if> >
								<label class="col-sm-3 control-label">URI：</label>
								<div class="col-sm-8">
									<input name="url" id="url" type="text" class="form-control" value="${menu.url }">
								</div>
							</div>
							<div class="form-group" <c:if test="${menu == null || menu.type == 1}">style="display: none;"</c:if> >
								<label class="col-sm-3 control-label">授权标识：</label>
								<div class="col-sm-8">
									<input name="authIden" id="authIden" type="text" class="form-control" value="${menu.authIden }" placeholder="例如:user:save,多个用,隔开">
								</div>
							</div>
							<div class="form-group" <c:if test="${menu != null && menu.type != 1}">style="display: none;"</c:if> >
								<label class="col-sm-3 control-label">图标：</label>
								<div class="col-sm-8">
									<input name="icon" id="icon" type="text" class="form-control" 
									value="${menu.icon }" readonly="readonly" style="cursor: pointer;" 
									onclick="toicon();">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">项目：</label>
								<div class="col-sm-8">
									<select class="chosen-select" name="projectId" style="width: 95%;"
										tabindex="2">
										<c:forEach items="${projectList }" var="project">
											<option ${menu.project.id == project.id ? 'selected' : '' } value="${project.id }"> ${project.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group" <c:if test="${menu.type == 3}">style="display: none;"</c:if> >
								<label class="col-sm-3 control-label">排序：</label>
								<div class="col-sm-8">
									<input name="sort" id="sort" type="number" class="form-control" value="${menu.sort }">
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
