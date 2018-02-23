<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>小石权限系统 -登录</title>
    <jsp:include page="./comm/comm.jsp">
 		<jsp:param value="layer" name="plugin"/>
	</jsp:include>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
    <style type="text/css">
    	.layui-layer-content {
    		color : black;
    	}
    </style>
    <script type="text/javascript">
	    $(function() {
	    	if (window.top !== window.self) {
		        window.top.location = window.location;
		    }
	    	$("#sm").click(function() {
	    		var username = $("#username").val();
	    		var password = $("#password").val();
	    		if (username == null || username == "") {
	    			$.myLayer.tips("请输入用户名", "#username", [2, "red"], false, true, 10000);
	    			return;
	    		}
	    		if (password == null || password == "") {
	    			$.myLayer.tips("请输入密码", "#password", [2, "red"], false, true, 10000);
	    			return;
	    		}
	    		$.myAjax({
		    		url : "${pageContext.request.contextPath}/user/smLogin",
		    		data : {"username" : username, "password" : password},
		    		type : "post",
		    		success : function(result) {
		    			if (result.success) {
	    					window.top.location.href = "${pageContext.request.contextPath}/index";
	    				} else {
	    					$.myLayer.alertError(result.code, result.msg);
	    				}
		    		}
		    	});
	    	});
	    	
	    	$("body").keydown(function(event) {
    		    if (event.keyCode == "13") {
    		    	$("#sm").click();
    		    }
    		});  
	    });
    </script>
</head>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <!-- <h1>[ 小石  ]</h1> -->
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎使用 <strong>小石权限系统</strong></h4>
                    <!-- <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                    </ul> -->
                    <!-- <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong> -->
                </div>
            </div>
            <div class="col-sm-5">
                <form class="form" id="form" action="${pageContext.request.contextPath}/user/smLogin" method="post">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到小石权限系统</p>
                    <input type="text" class="form-control uname" placeholder="请输入用户名" name="username" id="username"/>
                    <input type="password" class="form-control pword m-b" placeholder="请输入密码" name="password" id="password"/>
                    <!-- <a href="">忘记密码了？</a> -->
                    <input class="btn btn-success btn-block" value="登录" type="button" id="sm"/>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2017 All Rights Reserved. 小石权限系统
            </div>
        </div>
    </div>
</body>

</html>
