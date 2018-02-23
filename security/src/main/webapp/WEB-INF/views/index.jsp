<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>小石权限系统 -主页</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    
	<jsp:include page="./comm/comm.jsp">
		<jsp:param value="layer" name="plugin"/>
		<jsp:param value="metisMenu" name="plugin"/>
		<jsp:param value="slimscroll" name="plugin"/>
	</jsp:include>
    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/js/hplus.js?v=4.1.0"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/contabs.js"></script>
    
    <script type="text/javascript">
    	$(function() {
    		$.myLayer.msg("欢迎光临<shiro:principal property='realname'/>,现在是:" + new Date().toLocaleDateString() + new Date().toLocaleTimeString());
    	});
	    function updatePassword() {
			$.myLayer.open("修改密码", "500px", "400px", "${pageContext.request.contextPath}/user/updatePassword");
		}
    </script>
    
</head>
<body class="fixed-sidebar full-height-layout gray-bg fixed-nav" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element" style="text-align: center;">
                            <span><img alt="image" class="img-circle" src="${pageContext.request.contextPath}/img/logo.jpg" /></span>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                <span class="clear">
                               		<span class="block m-t-xs"><strong class="font-bold"><shiro:principal property='realname'/></strong><b class="caret"></b></span>
                                </span><shiro:hasRole name="aa"></shiro:hasRole>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="" href="javascript:;" onclick="updatePassword();">修改密码</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="${pageContext.request.contextPath}/logout">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">security
                        </div>
                    </li>
					<!-- 导航菜单 -->
                    <c:forEach items="${menus }" var="first_menu">
                    	<li>
							<a href="#">
								<i class="${first_menu.icon }"></i>
								<span class="nav-label">${first_menu.name }</span>
								<span class="fa arrow"></span>
							</a>
	                        <ul class="nav nav-second-level">
	                        	<c:forEach items="${first_menu.children }" var="second_menu">
	                        		<c:if test="${second_menu.type == 1 }">
	                        			<li>
			                                <a href="#">${second_menu.name } <span class="fa arrow"></span></a>
		                                	<ul class="nav nav-third-level">
				                                <c:forEach items="${second_menu.children }" var="third_menu">
					                            	<li><a class="J_menuItem" href="${third_menu.url }">${third_menu.name}</a></li>
				                                </c:forEach>
			                                </ul>
			                            </li>
	                        		</c:if>
	                        		<c:if test="${second_menu.type == 2 }">
	                        			<li><a class="J_menuItem" href="${second_menu.url }">${second_menu.name }</a></li>
	                        		</c:if>
	                        	</c:forEach>
	                        </ul>
	                        
	                    </li>
                    </c:forEach>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-fixed-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                    	<!-- <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#">
                    		<i class="fa fa-bars"></i>
                    	</a> -->
                    	<span style="margin-left:15px; font-size:36px;line-height:60px;color:#68cab7;font-weight:bold;">
                    		小石权限系统
                    	</span>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown hidden-xs">
                            <a class="right-sidebar-toggle" aria-expanded="false">
                                <i class="fa fa-tasks"></i>主题
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="index_v1.jsp">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="${pageContext.request.contextPath}/logout" class="roll-nav roll-right J_tabExit" target="_top"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${pageContext.request.contextPath}/homepage" frameborder="0" data-id="index_v1.jsp" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2017 <a href="#">SECURITY</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div id="right-sidebar">
            <div class="sidebar-container">

               <!--  <ul class="nav nav-tabs navs-3">
                    <li class="active">
                        <a data-toggle="tab" href="#tab-1">
                            <i class="fa fa-gear"></i> 主题
                        </a>
                    </li>
                    <li class=""><a data-toggle="tab" href="#tab-2">
                    	通知
                    </a>
                    </li>
                    <li><a data-toggle="tab" href="#tab-3">
                    	项目进度
                    </a>
                    </li>
                </ul> -->

                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> 主题设置</h3>
                            <small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                        </div>
                        <div class="skin-setttings">
                            <div class="title">主题设置</div>
                            <div class="setings-item">
                                <span>收起左侧菜单</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                                        <label class="onoffswitch-label" for="collapsemenu">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>固定顶部</span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                                        <label class="onoffswitch-label" for="fixednavbar">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>
                       				 固定宽度
                    			</span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                        <label class="onoffswitch-label" for="boxedlayout">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="title">皮肤选择</div>
                            <div class="setings-item default-skin nb">
                                <span class="skin-name ">
		                        	<a href="#" class="s-skin-0">
		                          		 默认皮肤
		                        	</a>
                   				</span>
                            </div>
                            <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
			                        <a href="#" class="s-skin-1">
			                           	 蓝色主题
			                        </a>
			                    </span>
                            </div>
                            <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
			                        <a href="#" class="s-skin-3">
			                        	黄色/紫色主题
			                        </a>
			                    </span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--右侧边栏结束-->
    </div>
</body>

</html>
