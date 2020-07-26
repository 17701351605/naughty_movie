<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

$(function(){
setInterval("$('#dateTime').html(new Date().toLocaleString()+'星期'+'日一二三四五六'.charAt(new Date().getDay()));",1000);
	
})
</script>
</head>
<body>
	<br/>
	<%--<a href = "<%=request.getContextPath()%>/user/out">退出登录</a>--%>
	<marquee><h2 align="center">欢迎进入点金淘气影院</h2></marquee>
	<br/>
	<div id="dateTime" align="right"></div>

</body>
</html>