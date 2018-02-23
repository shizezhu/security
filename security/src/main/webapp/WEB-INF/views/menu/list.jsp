<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>小石权限系统 -菜单管理</title>
<jsp:include page="../comm/comm.jsp">
	<jsp:param value="layer" name="plugin"/>
	<jsp:param value="bootstrap-table" name="plugin"/>
	<jsp:param value="jstree" name="plugin"/>
</jsp:include>
<style type="text/css">
.jstree-open>.jstree-anchor>.fa-folder:before {
	content: "\f07c";
}
.jstree-default .jstree-icon.none {
	width: 0;
}
.panel-body > div {
	overflow: auto;
}
.form-group input {
	width : 10px;
}
</style>
<script type="text/javascript">
	var pId = "-1";
	$(function() {
		$("#tree").css("height", $("body").height() - 122);
		$(".form-group input").css("width", $($(".row")[0]).width() / 12 * 3 - 200);
		$('#tree').jstree({
			'core' : {
				'data' : {
					"url" : "${pageContext.request.contextPath}/menu/getTreeNode",
					"data" : function(node) {
						if (node.id == "#") {
							return {"id" : "0", "root" : "true", "rootOpened" : "true", "children" : "true", "rootName" : "顶级"}
						}
						return {"id" : node.id, "children" : "true"};
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
			"plugins" : [ "types", "search" ]
		}).on('changed.jstree', function (e, data) {
			pId = data.selected[0] || -1;
			$('#list').bootstrapTable("refresh");
		});
		
		var tree_search_to = false;
		$('#tree_search').keyup(function () {
		    if (tree_search_to) {
		      clearTimeout(tree_search_to);
		    }
		    tree_search_to = setTimeout(function () {
		        $('#tree').jstree(true).search($('#tree_search').val());
		    }, 250);
		});
		
		$('#list').bootstrapTable({
			url : "${pageContext.request.contextPath}/menu/getByPage",
			method : "get",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			height : $(window).height() - 40,
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
    	params.parentId = pId;
		return params;
    }
	
	function save() {
		var id = "", selected = $('#tree').jstree(true).get_selected();
		if (selected && selected.length > 0) {
			id = selected[0];
		}
		$.myLayer.open("添加菜单", "500px", "85%", "${pageContext.request.contextPath}/menu/save?id=" + id);
	}
	
	function update() {
		var jstree = $('#tree').jstree(true);
		var id = $.myBootstrapTable.getId("#list");
		if (!id) {
			var selected = jstree.get_selected();
			if (selected && selected.length > 0) {
				id = selected[0];
			}
			if (!id) {
				$.myLayer.alertError("请选择一条信息");
				return;
			}
		}
		$.myLayer.open("修改菜单", "500px", "80%", "${pageContext.request.contextPath}/menu/update?id=" + id + "&isParent=" + (jstree.is_parent(jstree.get_node(id)) ? 1 : 0));
	}
	
	function del() {
		var jstree = $("#tree").jstree(true);
		var id = $.myBootstrapTable.getId("#list");
		if (!id) {
			var selected = jstree.get_selected();
			if (selected && selected.length > 0) {
				id = selected[0];
			}
			if (!id) {
				$.myLayer.alertError("请选择一条信息");
				return;
			}
		}
		$.myLayer.confirm("删除操作", (jstree.is_parent(jstree.get_node(id)) ? "您选择的是父节点,会将该节点下的所有子节点删除," : "") + "您确定要删除这条记录吗?", function() {
			$.myAjax({
				url : "${pageContext.request.contextPath}/menu/smDel",
				data : { "id" : id },
				success : function(result) {
					if (result.success) {
						$.myLayer.alertSuccess(result.msg);
						var parentNode = null;
						if (id * 1 == 0) {
							parentNode = jstree.get_node(id);
						} else {
							parentNode = jstree.get_node(jstree.get_parent(jstree.get_node(id)));
						}
						jstree.deselect_all(true);
						jstree.refresh_node(parentNode);
						jstree.select_node(parentNode, false, false);
					} else {
						$.myLayer.alertError(result.code, result.msg);
					}
				}
			});
		}, 3);
	}
	
	function typeFormat(value, row, index) {
		if (value == 1) {
			return "<span class='label label-success'>目录</span>";
		} else if (value == 2) {
			return "<span class='label label-primary'>页面</span>";
		} else if (value == 3) {
			return "<span class='label label-warning'>按钮</span>";
		} else {
			return "-";
		}
	}
	
	function parentMenuFormat(value, row, index) {
		if (!value) {
			return "顶级";
		}
		return value.name;
	}
	
	function iconFormat(value, row, index) {
		if (!value) {
			return "-";
		}
		return "<i class='" + value + "'></i>"
	}
	
	function projectFormat(value, row, index) {
		if (!value) {
			return "-";
		}
		return value.name;
	}
</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-3">
				<div class="tabs-container">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#user_tab">菜单树</a></li>
					</ul>
					<div class="tab-content ">
						<div class="form-inline" style="position: absolute; right: 15px; top: 2px;">
                            <div class="form-group">
                                <input id="tree_search" type="text" placeholder="搜索" class="form-control">
                            </div>
                        </div>
						<div class="tab-pane active">
							<div class="panel-body">
								<div id="tree"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-9">
				<div class="ibox-content">
					<div class="row row-lg">
						<div class="col-sm-12">
							<div class="example-wrap">
								<div class="example">
									<div class="btn-group hidden-xs" id="exampleToolbar"
										role="group">
										<shiro:hasPermission name="menu:save">
											<button type="button" class="btn btn-outline btn-default"
											onclick="save();">
											<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
											<span>新增</span>
										</button>
										</shiro:hasPermission>
										<shiro:hasPermission name="menu:update">
											<button type="button" class="btn btn-outline btn-default"
												onclick="update();">
												<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
												<span>修改</span>
											</button>
										</shiro:hasPermission>
										<shiro:hasPermission name="menu:del">
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
												<th data-field="name">名称</th>
												<th data-field="type" data-formatter="typeFormat" data-sortable="true">类型</th>
												<th data-field="parentMenu" data-sort-name="parent_id" data-formatter="parentMenuFormat" data-sortable="true">父级</th>
												<th data-field="url">URI</th>
												<th data-field="authIden">授权标识</th>
												<th data-field="icon" data-formatter="iconFormat">图标</th>
												<th data-field="project" data-formatter="projectFormat">所属系统</th>
												<th data-field="sort" data-sortable="true">排序</th>
												<th data-field="addTime" data-sort-name="add_time" data-formatter="$.myBootstrapTable.formatter.dateTime" data-sortable="true" data-visible="false">添加时间</th>
												<th data-field="modifyTime" data-sort-name="modify_time" data-formatter="$.myBootstrapTable.formatter.dateTime" data-sortable="true" data-visible="false">更新时间</th>
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
	</div>
</body>
</html>
