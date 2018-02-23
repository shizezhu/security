<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>小石权限系统 -菜单树</title>
<jsp:include page="../comm/comm.jsp">
	<jsp:param value="layer" name="plugin"/>
	<jsp:param value="bootstrap-table" name="plugin"/>
	<jsp:param value="jstree" name="plugin"/>
</jsp:include>
<script type="text/javascript">

	$(function() {
		$("#tree").css("height", $("body").height() - 5);
		$('#tree').jstree({
			'core' : {
				'data' : {
					"url" : "${pageContext.request.contextPath}/menu/getTreeNode",
					"data" : function(node) {
						if (node.id == "#") {
							return {"id" : "0", "root" : "true", "rootOpened" : "true", "rootName" : "顶级"};
						}
						return { "id" : node.id };
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
		});
	});
	
	function getSelected() {
		return $('#tree').jstree(true).get_selected(true);
	}
</script>
</head>
<body>
	<div class="wrapper animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<div id="tree"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
