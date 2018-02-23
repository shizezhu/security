<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>小石权限系统 -错误页</title>
<style type="text/css">
* {
	margin: 0px auto;
	padding: 0px;
	font-family: "宋体";
	color: #666;
	font-size: 12px;
}

img {
	border: 0px;
}

.w950 {
	width: 950px;
	margin: 0px auto;
}

.c_404 {
	background: #fff;
	height: 400px;
	margin-top: 25px;
}

.c_404 .baocu {
	background: url(${pageContext.request.contextPath}/img/error.gif) no-repeat;
	width: 500px;
	padding-left: 130px;
	height: 200px;
	float: left;
	margin-top: 120px;
	margin-left: 260px;
	display: inline;
}

.c_404 .baocu span {
	color: #ff6633;
	font-size: 30px;
	font-weight: bold;
	float: left;
	margin: 20px 0px 0px 20px;
	display: inline;
}

.c_404 .baocu  p {
	font-size: 18px;
	font-weight: bold;
	float: left;
	margin: 20px 0px 0px 20px;
	display: inline;
}

.c_404 .baocu  p a {
	font-size: 18px;
	font-weight: bold;
	color: #666;
	text-decoration: none;
}

.c_404 .baocu  p a:hover {
	font-size: 18px;
	font-weight: bold;
	color: #f00;
	text-decoration: underline;
}

.clear {
	clear: both;
}
</style>
</head>
<body style="background-color: #fff;">
	<div class="w950 c_404">
		<div class="baocu">
			<span>您没有权限访问,请联系管理员授权</span>
			<div class="clear"></div>
			<p>
				<a href="${pageContext.request.contextPath}/index" target="_top">返回首页</a>
			</p>
		</div>
	</div>
</body>
</html>