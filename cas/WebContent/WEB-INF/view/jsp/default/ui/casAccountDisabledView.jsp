<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="includes/top.jsp" />
  <div id="msg" class="errors">
    <h2><spring:message code="screen.accountdisabled.heading" /></h2>
    <p><spring:message code="screen.accountdisabled.message" /><a href="javascript:history.go(-1);">返回登陆</a></p>
  </div>
<jsp:directive.include file="includes/bottom.jsp" />
