<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8" />
<!-- Viewport metatags -->
<meta name="HandheldFriendly" content="true" />
<meta name="MobileOptimized" content="320" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dandelion.css"  media="screen" />
<title>500错误页面</title>
</head>
<body>
	<!-- Main Wrapper. Set this to 'fixed' for fixed layout and 'fluid' for fluid layout' -->
	<div id="da-wrapper" class="fluid">
        <!-- Content -->
        <div id="da-content">
            <!-- Container -->
            <div class="da-container clearfix">
            	<div id="da-error-wrapper">
                   	<div id="da-error-pin"></div>
                    <div id="da-error-code">
                    	error <span>500</span>
					</div>
                	<h1 class="da-error-heading">很抱歉！我们的服务遇到了一些问题</h1>
                    <p><a href="${pageContext.request.contextPath}/index" target="_top">返回首页</a></p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
