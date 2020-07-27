<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="color: #beeef0">
	<br/><br/>
	<a href = "<%=request.getContextPath()%>/movie/toMovieShow" target = "right"><p style="color:#beeef0 ">电影展示</p></a><br/><br/><br/><br/>
	<c:if test="${user.level == 0}">
		<a href = "<%=request.getContextPath()%>/userOrder/toShow" target = "right"><p style="color:#beeef0 ">我的影集</p></a><br/>
	</c:if>
</body>
</html>