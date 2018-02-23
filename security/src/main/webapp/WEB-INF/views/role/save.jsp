<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../comm/comm.jsp">
	<jsp:param value="layer" name="plugin"/>
	<jsp:param value="validate" name="plugin"/>
	<jsp:param value="jquery-form" name="plugin"/>
	<jsp:param value="jstree" name="plugin"/>
</jsp:include>
<title>小石权限系统 -新增角色</title>
<style type="text/css">
.jstree-open>.jstree-anchor>.fa-folder:before {
	content: "\f07c";
}
.jstree-default .jstree-icon.none {
	width: 0;
}
</style>
<script type="text/javascript">
	$(function() {
		$.myAjaxForm("form", {
			url : "${pageContext.request.contextPath}/role/smSave",
			data : {"menu" :  function() {
				var menuIdArray = $('#tree').jstree(true).get_checked(false);
				if (!menuIdArray || menuIdArray.length == 0) {
					return "";
				}
				var ri = $.inArray("0", menuIdArray);
				if (ri != -1) {
					menuIdArray.splice(ri, 1);
				}
				return menuIdArray.join(",");
			}},
			success : function(result) {
				if (result.success) {
					parent.$.myLayer.alertSuccess(result.msg);
					parent.$.myBootstrapTable.save("#list", result.data);
					$("form").resetForm();
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
						message : errorIocn + "名称必须为1-15位,支持中文、字母、数字、“_”、”-“"
					}],
			rules : {
				name : {
					required : true,
					name_reg : true
				}
			},
			messages : {
				name : {
					required : errorIocn + "请输入名称"
				}
			}
		});
		
		$('#tree').jstree({
			'core' : {
				'data' : {
					"url" : "${pageContext.request.contextPath}/menu/getTreeNode",
					"data" : function(node) {
						if (node.id == "#") {
							return {"id" : "0", "root" : "true", "rootName" : "所有权限", "rootOpened" : "true", "children" : "true", "childrenOpened" : "true"}
						}
						return {"id" : node.id, "isChildren" : "true", "childrenOpened" : "true"};
					},
					"dataType" : "json",
					"type" : "POST"
				}
			},
			"types" : {
				"default" : {
					"icon" : false
			    },
			    "1" : {
			    	"icon" : "fa fa-folder-o"
			    },
			    "2" : {
			    	"icon" : "fa fa-desktop"
			    },
			    "3" : {
			    	"icon" : "fa fa-hand-pointer-o"
				}
			},
			"checkbox" : {
				keep_selected_style : false,
				three_state : false,
				tie_selection : false,
				cascade : 'undetermined'
            },
			"plugins" : [ "types", "checkbox", "search" ]
		}).on("check_node.jstree", function(e, obj) {
			var jstree = $('#tree').jstree(true);
			var parents = obj.node.parents;
			var ri = $.inArray("0", parents);
			if (ri != -1) {
				parents.splice(ri, 1);
			}
			jstree.check_node(parents, null, true);
			jstree.check_node(obj.node.children_d, null, true);
			if (obj.node.id != "0") {
				var root_node = jstree.get_node("0");
				var all_children = root_node.children_d;
				var checked = jstree.get_checked();
				if (all_children.length <= checked.length) {
					jstree.check_node(root_node, null, true);
				}
			}
		}).on("uncheck_node.jstree", function(e, obj) {
			var jstree = $('#tree').jstree(true);
			jstree.uncheck_node(obj.node.children_d, null, true);
			if (obj.node.id != "0") {
				var root_node = jstree.get_node("0");
				var all_children = root_node.children_d;
				var checked = jstree.get_checked();
				var ri = $.inArray("0", checked);
				if (ri != -1) {
					checked.splice(ri, 1);
				}
				if (all_children.length > checked.length) {
					jstree.uncheck_node(root_node, null, true);
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
								<label class="col-sm-3 control-label">名称：</label>
								<div class="col-sm-8">
									<input name="name" type="text"
										class="form-control" value="">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">备注：</label>
								<div class="col-sm-8">
									<input name="descr" type="text"
										class="form-control" value="">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">菜单权限：</label>
								<div class="col-sm-8">
									<div id="tree" style="min-height: 300px;"></div>
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
